package com.example.windowdemo;

import android.app.ActivityManager;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

/**
 * service to check if the process is interact with user
 * @author zhouhao2
 *
 */
public class MyLocalStartService extends Service{
    
    Button demoBtn = null;
    WindowManager mWindowManager = null;
    LayoutParams lay;
    Display display=null;
    DisplayMetrics displaymetrics=new DisplayMetrics();
    
    public static final int ADD=0;
    public static final int REMOVE=1;
    
    public static final int DELAY=5000;
    
    Handler handler =new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                
            case ADD:
                Log.i("zhouhao2", "add view!!");
                updateDate(LayoutParams.TYPE_SYSTEM_ERROR,LayoutParams.FLAG_NOT_FOCUSABLE,Gravity.LEFT|Gravity.TOP,100,300);
                mWindowManager.addView(demoBtn, lay);

                break;
            case REMOVE:
               
                mWindowManager.removeView(demoBtn);
                Log.i("zhouhao2", "remove view!!");
                break;
                
            default:
                break;
            }
        }
    };

    private TimeChecker pc = null;
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
        
        if(pc == null){
            pc=new TimeChecker();
            Thread t1 = new Thread(pc);
            t1.start();
        }else{
           ;//do nothing
        }
        super.onCreate();
        return super.onStartCommand(intent, flags, startId);
    }
    
    
    private void init() {
        demoBtn = new Button(this);
        demoBtn.setText("i am demo");
        
        //set the button to be transparent
        demoBtn.setAlpha(0.5f);
        
        //set custom width and height and alpha
        lay = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,0,0,PixelFormat.TRANSPARENT);
        
        //set default parameters
        updateDate(LayoutParams.TYPE_SYSTEM_ERROR,LayoutParams.FLAG_NOT_TOUCH_MODAL,Gravity.LEFT|Gravity.TOP,100,300);
        
        demoBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Log.i("zhouhao2", "i am clicked ");
                lay.x= lay.x+50;
                lay.y = lay.y+50;
                mWindowManager.updateViewLayout(demoBtn, lay);
            }
        });
        
        demoBtn.setOnKeyListener(new OnKeyListener() {
            
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("zhouhao2", "i catch the keycode:"+keyCode+"  label:"+KeyEvent.keyCodeToString(event.getKeyCode()));
                return false;
            }
        });
        
        mWindowManager =(WindowManager)getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(demoBtn, lay);
        display=mWindowManager.getDefaultDisplay();
        display.getMetrics(displaymetrics);
        Log.i("zhouhao2", "display :"+displaymetrics.toString());
    }

    public void updateDate(int type,int flag,int gravity,int x,int y) {
        
        lay.type = type;
        lay.flags= flag;
        lay.gravity = gravity;
        lay.x = x;
        lay.y = y;
    }
    
    
    @Override
    public void onDestroy() {
        if(pc != null){
            pc.setStop(true);
        }
        super.onDestroy();
    }

    class TimeChecker implements Runnable{

        private boolean  isStop = false;

        
        public TimeChecker(){

        }
        
        @Override
        public void run() {
            //do check
            while(!isStop){
                   Log.i("zhouhao2", "i check event!!");
                   try {
                    Thread.sleep(DELAY);
                    
                    Message msg =handler.obtainMessage(REMOVE);
                    handler.sendMessage(msg);
                    
                    Thread.sleep(DELAY);
                    Message msg1 =handler.obtainMessage(ADD);
                    handler.sendMessage(msg1);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void setStop(boolean isStop) {
            this.isStop = isStop;
        }
    } 
}
