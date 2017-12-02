
package com.example.broadcasttest;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import com.hisense.motionkit.CustomMotionArgs;
import com.hisense.motionkit.CustomPointerCoord;
import com.hisense.motionkit.MotionMonitorController;

import roboguice.activity.RoboActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class ShowActivity extends RoboActivity {
    
	public static String TAG = ShowActivity.class.getSimpleName();
    
    public static final int START =0;
    
    public static final int APPEND =1;  
    
    public static final int APPEND_TO_LIST =2;
    
    LinkedList<Message> messages = new LinkedList<>();
   
    public static final int TIME_DELAY =1000;
    
    Timer timer = new Timer();
    
    public String cmd;
    
    MyView myView = null;
    
    int period = 2;
    
	public static int count = 0;

	public static int MultiCount = 0;
	
    Handler handler =new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                
            case APPEND:

                /*textResult.append(((String)msg.obj).toString());
                scrollView.fullScroll(android.view.View.FOCUS_DOWN);
                Log.i(MotionMonitorController.TAG, "receive apend");*/
            	
            	CustomMotionArgs custompoints =(CustomMotionArgs)msg.obj;
            	int count = custompoints.getPointerCount();
            	CustomPointerCoord[] mypoints=custompoints.getPointerCoords();
            	for (int i = 0; i < count; i++) {
					myView.updatePoints(mypoints[i].x,mypoints[i].y,custompoints.action);
				}
                break;
                
            default:
                break;
            }
        }
    };
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_my_view);
        myView=(MyView) findViewById(R.id.myview);

        new Thread(){
           public void run() {

			timer.scheduleAtFixedRate(new TimerTask() {
                   
                   @SuppressLint("NewApi") @Override
                   public void run() {
                       if(messages.size()!=0){
                           Log.i(MotionMonitorController.TAG, "refresh ui");
                           Message message=messages.pollFirst();
                           handler.sendMessage(message);
                       }else{
                           //Log.i(MotionMonitorController.TAG, "pollfirst is null");
                       }
                   }
               }, TIME_DELAY+20,period);
           };
       }.start();
       
       
       new Thread(new Runnable() {
           
           @Override
           public void run() {
               Log.i("zhouhao2", " start thread run!!");
               MotionMonitorController.registerCallBack(new com.hisense.motionkit.NotifyMotionCallback() {
                   
                   @Override
                   public void onNotifyMotionCallback(CustomMotionArgs customMotionArgs) {
                	   
                	     countNumber(customMotionArgs);
                         Log.i(MotionMonitorController.TAG, "receive msg");
                         Message message = new Message();
                         message.what=APPEND;
                         message.obj=customMotionArgs;
                         messages.add(message);
                         Log.i(MotionMonitorController.TAG, "add to msg list");
                   }
               });
               
               MotionMonitorController.start();
           }
       }).start();
       
       
       //MotionMonitorController.setStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.i(MotionMonitorController.TAG,"onTouchEvent");
        Log.i(MotionMonitorController.TAG,event.toString());
        return false;
    }
    
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(MotionMonitorController.TAG,"onKeyDown");
        Log.i(MotionMonitorController.TAG,event.toString());
        return true;
    }
    
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.i(MotionMonitorController.TAG,"onKeyUp");
        Log.i(MotionMonitorController.TAG,event.toString());
        return true;
    }
    
    
    public static void countNumber(CustomMotionArgs motionArgs){
    	int mPointerCount = motionArgs.getPointerCount();
		if (mPointerCount == 1) {
			switch (motionArgs.getAction()) {
			case MotionEvent.ACTION_DOWN:
				count = 1;
				Log.i(TAG, "down count is :" + count);
				break;

			case MotionEvent.ACTION_MOVE:
				++count;
				Log.i(TAG, "move count is :" + count);
				break;

			case MotionEvent.ACTION_UP:
				Log.i(TAG, "up count is :" + count);
				break;
			}
			
		} else if (mPointerCount > 1) {
			
			switch (motionArgs.getAction()) {
			case MotionEvent.ACTION_DOWN:
				MultiCount += mPointerCount;
				Log.i(TAG, "down MultiCount is :" + MultiCount);
				break;

			case MotionEvent.ACTION_MOVE:
				MultiCount += mPointerCount;
				Log.i(TAG, "move MultiCount is :" + MultiCount);
				break;

			case MotionEvent.ACTION_UP:
				Log.i(TAG, "up MultiCount is :" + MultiCount);
				MultiCount=0;
				break;
			}
		}
    	
    }
    
    
}
