package com.hisense.vidaaassistant.log.newuploader;

import java.io.File;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hisense.vidaaassistant.log.R;
import com.hisense.vidaaassistant.log.mainflow.MainConstants;
import com.hisense.vidaaassistant.log.mainflow.MainFlowControlHandler;
import com.hisense.vidaaassistant.log.service.KeyMonitorService;

/**
 *
 * the activity to show the UI changes
 * @author zhouhao2
 * 
 */
public class BugReport2Ftp extends ActionBarActivity {

    public static final String TAG = BugReport2Ftp.class.getSimpleName();


    private static final String UPLOAD_OK = "上传成功";
    private static final String UPLOAD_FAIL = "上传失败";
    private static final String UPLOAD_WAITING = "请稍后…";

    private View parentView = null;
    private Context content = null;

    // to do from here
    public ImageView LogImg_collecting = null;
    public ImageView LogImg_compressing = null;
    public ImageView LogImg_uiploading = null;
    public ImageView LogImg_waiting     = null;

    public ProgressBar LogPd_collecting = null;
    public ProgressBar LogPd_compressing = null;
    public ProgressBar LogPd_uiploading = null;
    public ProgressBar LogPd_waiting = null;

    public TextView bigWaiting = null;
    public Drawable drawableWaiting = null;
    public Drawable drawableOK =  null;
    public Drawable drawableUnsafe =  null;
    public Drawable drawableWarning = null;

    public LinearLayout layoutFileName = null;
    public TextView tvLogFileName = null;

    public LinearLayout layoutRetry = null;
    public Button btnRetry = null;



    // handle in dump thread
    private Handler mainFlowControlerHandler = null;

    public void resetAll(){
        bigWaiting.setText(UPLOAD_WAITING);
        LogImg_collecting.setVisibility(View.INVISIBLE);
        LogImg_compressing.setVisibility(View.INVISIBLE);
        LogImg_uiploading.setVisibility(View.INVISIBLE);
        LogImg_waiting.setVisibility(View.INVISIBLE);

        LogPd_waiting.setVisibility(View.VISIBLE);
        LogPd_collecting.setVisibility(View.VISIBLE);
        LogPd_compressing.setVisibility(View.INVISIBLE);
        LogPd_uiploading.setVisibility(View.INVISIBLE);
        layoutRetry.setVisibility(View.INVISIBLE);
        layoutFileName.setVisibility(View.INVISIBLE);

        MainConstants.reset();
    }

    public void initFromParent(Context context) {
            drawableWaiting = ContextCompat.getDrawable(context,R.drawable.ic_dialog_loading);
            drawableOK =  ContextCompat.getDrawable(context,R.drawable.ic_dialog_safe);
            drawableUnsafe =  ContextCompat.getDrawable(context,R.drawable.ic_dialog_unsafe);
            drawableWarning =  ContextCompat.getDrawable(context,R.drawable.ic_unsafe);

            bigWaiting       = (TextView) findViewById(R.id.log_tv_wainting);

            LogImg_collecting = (ImageView) findViewById(R.id.log_ima_collecting);
            LogImg_compressing = (ImageView) findViewById(R.id.log_ima_compressing);
            LogImg_uiploading = (ImageView) findViewById(R.id.log_ima_uploading);
            LogImg_waiting   = (ImageView) findViewById(R.id.log_ima_waiting);

            LogPd_collecting = (ProgressBar) findViewById(R.id.log_pd_collecting);
            LogPd_compressing = (ProgressBar) findViewById(R.id.log_pd_compressing);
            LogPd_uiploading = (ProgressBar) findViewById(R.id.log_pd_uploading);
            LogPd_waiting   = (ProgressBar) findViewById(R.id.log_pd_waiting);

            layoutFileName = (LinearLayout)findViewById(R.id.ly_logfilename);
            tvLogFileName = (TextView)findViewById(R.id.log_filename);

            layoutRetry = (LinearLayout)findViewById(R.id.ly_logretry);
            btnRetry = (Button) findViewById(R.id.logupload_retry);
        
            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick resetall: ");
                    resetAll();
                    startMainFlow();
                }
            });
            resetAll();
    }

    public void startMainFlow(){
        // get the serialNumber to avoid the first time null
        MainConstants.getUploadFileName(getApplicationContext());
        HandlerThread workThread = new HandlerThread(TAG);
        workThread.start();
        mainFlowControlerHandler = new MainFlowControlHandler(
                workThread.getLooper(), mUIHandler, TAG,
                BugReport2Ftp.this);
        // check the usb file's exist
        createBugFile();
        mainFlowControlerHandler
                .sendEmptyMessage(MainConstants.FILE_DUMP);
        mUIHandler.sendEmptyMessage(MainConstants.FILE_DUMP);
    }

    public void setImaOK(ImageView img){
        if(img != null){
            img.setBackground(drawableOK);
        }
    }

    public void setDialogVisable(ProgressBar pd,boolean visible){
        if(pd != null){
            if(visible) {
                pd.setVisibility(View.VISIBLE);
            }else{
                pd.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setImaVisable(ImageView ima,boolean visible){
        if(ima != null){
            if(visible) {
                ima.setVisibility(View.VISIBLE);
            }else{
                ima.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setWaiting(ImageView img){
        if(img != null){
            img.setBackground(drawableWaiting);
        }
    }

    public void setWarning(ImageView img){
        if(img != null){
            img.setBackground(drawableWarning);
            img.setVisibility(View.VISIBLE);
        }
    }

    public void setBigWaiting(){
        if(bigWaiting != null){
            bigWaiting.setText(UPLOAD_WAITING);
            LogImg_waiting.setVisibility(View.INVISIBLE);
            LogPd_waiting.setVisibility(View.VISIBLE);
        }
    }

    public void setBigWaitingOK(){
        if(bigWaiting != null){
            bigWaiting.setText(UPLOAD_OK);
            LogImg_waiting.setBackground(drawableOK);
            LogImg_waiting.setVisibility(View.VISIBLE);
            LogPd_waiting.setVisibility(View.INVISIBLE);
        }
    }

    public void setBigWaitingFail(){
        if(bigWaiting != null){
            bigWaiting.setText(UPLOAD_FAIL);
            LogImg_waiting.setVisibility(View.VISIBLE);
            LogImg_waiting.setBackground(drawableUnsafe);
            LogPd_waiting.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Log.i(TAG, "onCreate: set window to type system alert !!");
        getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        getWindow().setGravity(Gravity.CENTER);
        setContentView(R.layout.logupload_start_window);
        ready2Go();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void ready2Go(){
        initFromParent(this);
        startMainFlow();
    }

    // handle in Main thread
    private Handler mUIHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MainConstants.FILE_DUMP:
                    setBigWaiting();
                    setDialogVisable(LogPd_collecting,true);
                    break;
                case MainConstants.FILE_DUMP_SUCCESSFUL:
                    setImaOK(LogImg_collecting);
                    setImaVisable(LogImg_collecting,true);
                    setDialogVisable(LogPd_collecting,false);

                    setDialogVisable(LogPd_compressing,true);
                    setImaVisable(LogImg_compressing,false);
                    break;
                case MainConstants.FILE_DUMP_ERROR:
                    setBigWaitingFail();
                    setWarning(LogImg_collecting);
                    setImaVisable(LogImg_collecting,true);
                    setDialogVisable(LogPd_collecting,false);
                    layoutRetry.setVisibility(View.VISIBLE);
                    break;

                case MainConstants.FILE_ZIP_SUCCESS:
                    setDialogVisable(LogPd_compressing,false);
                    setWaiting(LogImg_compressing);
                    setImaVisable(LogImg_compressing,true);
                    setDialogVisable(LogPd_compressing,false);
                    setImaOK(LogImg_compressing);
                    setDialogVisable(LogPd_uiploading,true);
                    break;
                case MainConstants.FILE_ZIP_ERROR:
                    setBigWaitingFail();
                    setDialogVisable(LogPd_compressing,false);
                    setImaVisable(LogImg_compressing,true);
                    setWarning(LogImg_compressing);
                    layoutRetry.setVisibility(View.VISIBLE);
                    break;
                case MainConstants.UPLOAD_SUCCESS:
                    setBigWaitingOK();
                    setImaOK(LogImg_uiploading);
                    setDialogVisable(LogPd_uiploading,false);
                    setImaVisable(LogImg_uiploading,true);
                    tvLogFileName.setText(MainConstants.getUploadFileName(getApplicationContext()));
                    layoutFileName.setVisibility(View.VISIBLE);
                    layoutRetry.setVisibility(View.INVISIBLE);
                    break;
                case MainConstants.UPLOAD_ERROR:
                    setBigWaitingFail();
                    setWarning(LogImg_uiploading);
                    setImaVisable(LogImg_uiploading,true);
                    setDialogVisable(LogPd_uiploading,false);
                    layoutFileName.setVisibility(View.INVISIBLE);
                    layoutRetry.setVisibility(View.VISIBLE);
                    break;


            }
        }
    };


    private void initKeyMOnitoringService() {
        KeyMonitorService.enableMySelf(getApplicationContext());
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

    @Override
    public void onBackPressed() {
        return ;
    }
}
