package com.zhouhao2.newbuguploader.newuploader;

import java.io.File;
import java.util.Arrays;

import android.graphics.PixelFormat;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.zhouhao2.newbuguploader.R;
import com.zhouhao2.newbuguploader.mainflow.MainConstants;

/**
 * the activity to show the UI changes
 * 
 * @author zhouhao2
 * 
 */
public class BugReport2Ftp extends ActionBarActivity {

    public static final String TAG = BugReport2Ftp.class.getSimpleName();

    protected Context mContext;

    private long mLastBugreportTime = 0;

    // two ui widgets
    private Button mBtnUpload = null;
    private TextView mTvShowStatus = null;

    // handle in dump thread
    private Handler mainFlowControlerHandler = null;

    // handle in Main thread
    private Handler mUIHandler = null;

    //listenner for trigering poping window
    private KeySequenceListenner mKeySequenceListenner = new KeySequenceListenner();
    private MyWindowController mMyWindowController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.logupload_file_name);
        setContentView(R.layout.logupload_start_window);

        mMyWindowController = new MyWindowController(getApplicationContext(),R.layout.logupload_start_window);

/*        // init ui widgets
        mBtnUpload = (Button) findViewById(R.id.btn_upload);
        mTvShowStatus = (TextView) findViewById(R.id.tv_status_show);
        mContext = getApplicationContext();
        mUIHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                case MainConstants.FILE_DUMP:

                    mTvShowStatus.setText(R.string.start_dump);
                    mBtnUpload.setEnabled(false);
                    break;

                case MainConstants.FILE_DUMP_ERROR:

                    mTvShowStatus.setText(R.string.dump_error);
                    mBtnUpload.setEnabled(true);
                    break;
                case MainConstants.UPLOAD_ERROR:

                    mTvShowStatus.setText(R.string.upload_error);
                    mBtnUpload.setEnabled(true);
                    break;

                case MainConstants.FILE_ZIP_SUCCESS:

                    mTvShowStatus.setText(R.string.compress_ok);
                    break;
                case MainConstants.UPLOAD_SUCCESS:

                    mTvShowStatus.setText(R.string.upload_ok);
                    mBtnUpload.setEnabled(true);
                    break;
                case MainConstants.ZIP_ERROR:

                    mTvShowStatus.setText(R.string.compress_error);
                    mBtnUpload.setEnabled(true);
                    break;
                }
            }
        };
        // trigger the dump function
        mBtnUpload.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // get the serialNumber to avoid the first time null
                MainConstants.getUploadFileName(mContext);
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
        });*/
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

    public class KeySequenceListenner {

        private static final int KEYS_LENGTH = 8;
        private int[] mKeyHistory = new int[KEYS_LENGTH];
        private int mNextIndex = 0;
        private int[] TRIGER_SEQUENCE = new int[]{
                KeyEvent.KEYCODE_DPAD_UP,
                KeyEvent.KEYCODE_DPAD_UP,
                KeyEvent.KEYCODE_DPAD_DOWN,
                KeyEvent.KEYCODE_DPAD_DOWN,
                KeyEvent.KEYCODE_DPAD_LEFT,
                KeyEvent.KEYCODE_DPAD_LEFT,
                KeyEvent.KEYCODE_DPAD_RIGHT,
                KeyEvent.KEYCODE_DPAD_RIGHT
        };

        public KeySequenceListenner(){
            for (int i = 0; i < mKeyHistory.length; i++) {
                mKeyHistory[i]=0;
            }
        }

        public boolean shouldPopWindow(int keycode){
              pushKey(keycode);
              return checkKeys();
        }

        private void pushKey(int keycode){
            if(mNextIndex > KEYS_LENGTH-1){
                for (int i = 0; i < KEYS_LENGTH - 1 ; i++) {
                    mKeyHistory[i] = mKeyHistory[i+1];
                }
                mKeyHistory[ KEYS_LENGTH-1] = keycode;
            } else {
                    mKeyHistory[mNextIndex] = keycode;
                    mNextIndex ++ ;
            }
        }

        private boolean checkKeys(){
            boolean res = Arrays.equals(mKeyHistory,TRIGER_SEQUENCE);
            Log.i(TAG, "checkKeys: "+res);
            printArrays(mKeyHistory,"ZHOUHAO KEYS ");
            return res;
        }

        private void printArrays(int[] keys,String tag){
            for (int i = 0; i < keys.length; i++) {
                Log.i(TAG, tag+"  printArrays: "+keys[i]);
            }
            Log.i(TAG, "printArrays: index "+ mNextIndex);
        }
    }

    public class MyWindowController{

        private  WindowManager  mWindowManager = null;
        private  LayoutParams mLay = null;
        private  int mLayoutId = 0;
        private  View mMainView = null;
        private  static final int WIDTH = 756;
        private  static final int HEIGHT = 520;

        
        public MyWindowController(Context context,int layoutId){
            init(context,layoutId);
        }

        private void init(Context context,int layoutId){

            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

            mMainView = LayoutInflater.from(context).inflate(layoutId,null);

            //set custom width and height and alpha
           // mLay = new LayoutParams(LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,0,0,PixelFormat.TRANSPARENT);
            mLay = new LayoutParams();

            updateLayout(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, LayoutParams.FLAG_NOT_FOCUSABLE, Gravity.TOP,757,0);

            showWindow();
        }

        public void showWindow(){
            if(mWindowManager != null){
                if(mMainView != null && mLay != null ){
                    Log.i(TAG, "showWindow: ");
                    //set default parameters
                    mWindowManager.addView(mMainView,mLay);
                }
            }
        }

        public void updateLayout(int type,int flag,int gravity,int x,int y) {

            mLay.type = type;
            mLay.flags= flag;
            mLay.gravity = gravity;
            mLay.width = WIDTH;
            mLay.height = HEIGHT;
            mLay.format = PixelFormat.UNKNOWN;
            mLay.x = x;
            mLay.y = y;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mKeySequenceListenner.shouldPopWindow(keyCode)){
            mMyWindowController.showWindow();
        }
        return super.onKeyDown(keyCode, event);
    }
}
