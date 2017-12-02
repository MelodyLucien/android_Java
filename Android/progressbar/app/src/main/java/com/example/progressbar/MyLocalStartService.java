package com.example.progressbar;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ProgressBar;

/**
 * service to check if the process is interact with user
 * @author zhouhao2
 *
 */
public class MyLocalStartService extends Service {
    
    public static ProgressBar mProgressBar = null;
    public static View v = null;
    public static  WindowManager mWindowManager = null;
    public static LayoutParams lay;
    public static Display display=null;
    public static DisplayMetrics displaymetrics=new DisplayMetrics();
    
    public static final int ADD=0;
    public static final int REMOVE=1;
    
    public static final int DELAY=5000;
    
    public static Handler handler =new Handler(){

        public void handleMessage(Message msg) {

            switch (msg.what) {
            case ADD:
                if(this.hasMessages(REMOVE)){
                    removeMessages(REMOVE);
                }
                boolean isIncrease = (1 == msg.arg1 )? true:false;
                int step = msg.arg2;
                Log.i("zhouhao2", "add view!!");
                updateDate(LayoutParams.TYPE_SYSTEM_ERROR, LayoutParams.FLAG_NOT_FOCUSABLE, Gravity.LEFT| Gravity.TOP,100,300,isIncrease,step);
                sendEmptyMessageDelayed(REMOVE,2000l);
                break;

            case REMOVE:
                mWindowManager.removeView(v);
                Log.i("zhouhao2", "remove view!!");
                break;
                
            default:
                break;
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        init();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onCreate();
        return super.onStartCommand(intent, flags, startId);
    }
    
    
    private void init() {


        v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_main,null);

        mProgressBar= (ProgressBar) v.findViewById(R.id.progressBarhor);

        //set custom width and height and alpha
        lay = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,0,0, PixelFormat.TRANSPARENT);
        
        //set default parameters
        updateDate(LayoutParams.TYPE_SYSTEM_ERROR, LayoutParams.FLAG_NOT_TOUCH_MODAL, Gravity.LEFT| Gravity.TOP,100,300,false,0);

        
        mWindowManager =(WindowManager)getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(v, lay);
        display=mWindowManager.getDefaultDisplay();
        display.getMetrics(displaymetrics);
        Log.i("zhouhao2", "display :"+displaymetrics.toString());
    }

    public static void updateDate(int type,int flag,int gravity,int x,int y,boolean isIncrease,int step) {
        
        lay.type = type;
        lay.flags= flag;
        lay.gravity = gravity;
        lay.x = x;
        lay.y = y;

        if(step == 0){
            return ;
        }

        if(isIncrease){
            int progress = mProgressBar.getProgress();
            if(progress < 100 ) {
                progress=progress+step;
                mProgressBar.setProgress(progress);
            }
        }else if(!isIncrease){
            int progress = mProgressBar.getProgress();
            if(progress > 0) {
                progress=progress-step;
                mProgressBar.setProgress(progress);
            }
        }
    }
    
    
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static void notify(boolean isIncrease,int step){
        if(handler != null){
            handler.obtainMessage(ADD,isIncrease ? 1:0,step).sendToTarget();
        }
    }

}
