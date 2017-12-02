package com.hisense.vidaaassistant.log.upload;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


import com.hisense.vidaaassistant.log.mainflow.MainConstants;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpProgressMonitor;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SFtpUploader implements  IUploader{
    private SftpProgressMonitor mMoniter = null;
    private  Handler mMainFlowHandler = null;

    public SFtpUploader(Handler h){
        mMainFlowHandler = h;
    }

    @Override
    public void uploadFile(final String TAG,
            final String compressesFileNamePath, final Context context) {
        new Thread() {
            public void run() {

                //upload file
                try {

                    Map<String, String> sftpDetails = new HashMap<String, String>();
                    //set some parameters
                    sftpDetails.put(SFTPConstants.SFTP_REQ_HOST,
                            "kefulog.hismarttv.com");
                    sftpDetails
                            .put(SFTPConstants.SFTP_REQ_USERNAME, "sftpuser");
                    sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD,
                            "vod2007414516");
                    sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "1520");
                    String src = compressesFileNamePath;

                    String dst = "/com.hisense.tv.vod/"
                            + MainConstants.getUploadFileName(context); //get the uploaded file name
                    Log.i(TAG, "the upload filename is : " + dst);
                    SFTPChannel channel = new SFTPChannel();
                    ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
                    File file = new File(compressesFileNamePath);
                    if (!file.exists()) {
                        Log.i(TAG, compressesFileNamePath
                                + "  is not exists!!!");
                        return;
                    }
                    long size = file.length();
                    mMoniter = new SftpProgressMonitor() {

                        @Override
                        public void init(int arg0, String arg1, String arg2,
                                long arg3) {
                            Log.i(TAG, "upload init");
                        }

                        @Override
                        public void end() {
                            Log.i(TAG, "upload end");
                            mMainFlowHandler.sendEmptyMessage(MainConstants.UPLOAD_SUCCESS);
                        }

                        @Override
                        public boolean count(long arg0) {
                            Log.i(TAG, "upload count = " + arg0);
                            return true;
                        }
                    };
                    chSftp.put(src, dst, mMoniter, ChannelSftp.RESUME);
                    chSftp.quit();
                    channel.closeChannel();
                } catch (Exception e) {

                    Log.e(TAG, " upload file exception" + e.getMessage());
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = MainConstants.UPLOAD_ERROR;
                    mMainFlowHandler.sendMessage(msg);
                }
            }
        }.start();
    }
}
