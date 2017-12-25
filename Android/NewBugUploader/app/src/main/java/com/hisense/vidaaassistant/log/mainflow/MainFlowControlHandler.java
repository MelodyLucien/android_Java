package com.hisense.vidaaassistant.log.mainflow;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


import com.hisense.vidaaassistant.log.dump.ConCurCountDownWorker;
import com.hisense.vidaaassistant.log.upload.IUploader;
import com.hisense.vidaaassistant.log.upload.SFtpUploader;
import com.hisense.vidaaassistant.log.zip.ZipUtil;

import java.io.File;

/**
 * this class design to dump ,compress and upload ,delete the file
 * 
 * @author zhouhao2
 * 
 */
public class MainFlowControlHandler extends Handler {

    /**
     * a handler to communicate with main thread
     */
    private Handler mainhandler = null;
    private String TAG = null;
    private Context mContext = null;

    public MainFlowControlHandler(Looper looper, Handler mainhandler,
            String TAG, Context context) {
        super(looper);
        mContext = context;
        this.mainhandler = mainhandler;
        this.TAG = TAG;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
        case MainConstants.FILE_DUMP:
            Log.d(TAG, "dump log: FILE_DUMP");
            // concurrent work for dump file ,divide into twp steps
            ConCurCountDownWorker conCurCountDownWorker = new ConCurCountDownWorker(
                    MainConstants.createMkCmds(MainConstants.DUMP_CMDS_MK));
            // first do mkdir
            if (conCurCountDownWorker.run()) {
                conCurCountDownWorker
                        .resetWorkerList(MainConstants
                                .createMkCmds(MainConstants.DUMP_CMDS_CP_AND_DYNAMIC_CREATE));
                // second do copy
                if (conCurCountDownWorker.run()) {
                    Log.d(TAG, "dump log: FILE_DUMP successful!!!");
                    mainhandler
                            .sendEmptyMessage(MainConstants.FILE_DUMP_SUCCESSFUL);
                    sendEmptyMessage(MainConstants.FILE_ZIP);
                } else {
                    mainhandler.sendEmptyMessage(MainConstants.FILE_DUMP_ERROR);
                    Looper.myLooper().quit();
                }
            } else {
                mainhandler.sendEmptyMessage(MainConstants.FILE_DUMP_ERROR);
                Looper.myLooper().quit();
            }
            break;
        case MainConstants.FILE_ZIP:
            Log.d(TAG, "zip log: FILE_ZIP");
            if (!zipUsbFile()) {
                mainhandler.sendEmptyMessage(MainConstants.FILE_ZIP_ERROR);
                Looper.myLooper().quit();
                return;
            }
            mainhandler.sendEmptyMessage(MainConstants.FILE_ZIP_SUCCESS);
            sendEmptyMessage(MainConstants.FILE_UPLOAD);
            break;
        case MainConstants.FILE_UPLOAD:
            Log.d(TAG, "upload log: FILE_UPLOAD");
            upload();
            //sendEmptyMessage(MainConstants.UPLOAD_ERROR);
            break;
        case MainConstants.UPLOAD_SUCCESS:
            Log.d(TAG, "upload log: FILE_UPLOAD_SUCCESSFUL!!!");
            mainhandler.sendEmptyMessage(MainConstants.UPLOAD_SUCCESS);
            sendEmptyMessage(MainConstants.DELETE_ALL_FILES);
            break;
        case MainConstants.UPLOAD_ERROR:
            Log.d(TAG, "upload log: FILE_UPLOAD_ERROR!!!");
            mainhandler.sendEmptyMessage(MainConstants.UPLOAD_ERROR);
            sendEmptyMessage(MainConstants.DELETE_ALL_FILES);
            break;
        case MainConstants.DELETE_ALL_FILES:
            Log.d(TAG, "delete all files: FILE_DELETE");
            ConCurCountDownWorker deleteWorker = new ConCurCountDownWorker(
                    MainConstants
                            .createMkCmds(MainConstants.CMD_DELETE_ALL_STAGE_FILES));
            if (deleteWorker.run()) {
                Log.d(TAG, "delete all files: FILE_DELETE_SUCESSFUL !!");
            }
            Looper.myLooper().quit();
        }
    }

    /**
     * ensure the file is exists
     * 
     * @return true if exists ,false if create new one
     */
    public boolean createBugFile() {
        File file = new File(MainConstants.APP_HOME_BUG_DIR);
        if (!file.exists()) {
            file.mkdir();
            return false;
        }
        return true;
    }

    public boolean zipUsbFile() {
        /**
         * if create new on ,return
         */
        if (!createBugFile()) {
            return false;
        }
        ZipUtil zipUtil = new ZipUtil();
        String zipFileName = MainConstants.getUploadFileName(mContext);
        if(zipFileName != null && !zipFileName.equals("")){
            try {
                zipUtil.zip(MainConstants.COMPRESSED_FROM_PATH_DIR,
                        MainConstants.COMPRESSED_TO_PATH_DIR,
                        zipFileName);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                mainhandler.sendEmptyMessage(MainConstants.FILE_ZIP_ERROR);
                return false;
            }
        }
        return false;
    }

    public boolean upload() {
        Log.i(TAG, "uploadFile is : "+MainConstants.COMPRESSED_TO_PATH_DIR
                + MainConstants.getUploadFileName(mContext));
        //IUploader uploader = new HttpUploader(MainFlowControlHandler.this);
        IUploader uploader = new SFtpUploader(MainFlowControlHandler.this);
        uploader.uploadFile(TAG,MainConstants.COMPRESSED_TO_PATH_DIR
                + MainConstants.getUploadFileName(mContext), mContext);
        return true;
    }
}
