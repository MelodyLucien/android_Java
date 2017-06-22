package com.hisense.checkdrm;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;


import com.hisense.checkdrm.R;
import com.hisense.util.ColorRender;
import com.hisense.util.ConCurCountDownWorker;
import com.zhouhao.config.MyLog;
import com.zhouhao.config.ParentLog;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

public class ShowActivity extends RoboActivity {

    public static final String TAG = "DrmCheck";

    public static final int START = 0;

    public static final int APPEND = 1;

    public static final int APPEND_TO_LIST = 2;

    public static final int TIME_DELAY = 1000;

    public static final int REFRESH_INTERVAL = 200;
    
    private boolean isSendResult = false;
    

    private String cmd;

    private static ColorRender colorRender = new ColorRender();

    public static ConCurCountDownWorker conCurCountDownWorker = new ConCurCountDownWorker();

    private LinkedList<Message> messages = new LinkedList<>();

    private Timer timer = new Timer();
    
    private Thread mTimeThread = null;
    
    public Thread mCommandThread = null;
    

    Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {

            case APPEND:
                
                String originalStr=(String) msg.obj;
                
                Log.i(TAG, originalStr);
               
                
                TestState.changeState(originalStr);
                
                //if we encounter a failure, show instantly
                if((isSendResult == false) && (TestState.getState() == TestState.FAILED)){
                    sendFailedResult();
                    
                  //if we encounter an over without an error ,check if we have started by checking some keywords
                  //such as OK,FAILED,PASSED which indicate we have started checking normally.
                }else if((isSendResult == false) && (TestState.GET_OVER_STR)){
                    if(TestState.CURRENT_STATE == TestState.NOTSTARTED){
                        sendFailedResult();
                    }else if(TestState.CURRENT_STATE == TestState.STARTED){
                        sendOkResult();
                    }
                }
                
                //String colorStr = colorRender.appendColor2String(originalStr.toString());
                textResult.append(originalStr);
                scrollView.fullScroll(android.view.View.FOCUS_DOWN);
                Log.i("zhouhao2", "receive apend");
                break;

            default:
                break;
            }
        }

        public void sendOkResult() {
            Log.i(TAG, "start ok activity");
            isSendResult = true;
            Intent in = new Intent();
            in.setClass(ShowActivity.this,ResultActivity.class);
            in.putExtra("result",TestState.STR_OK);
            startActivity(in);

        }

        public void sendFailedResult() {
            Log.i(TAG, "start fail activity");
            isSendResult = true;
            Intent in = new Intent();
            in.setClass(ShowActivity.this,ResultActivity.class);
            in.putExtra("result",TestState.STR_FAILED);
            startActivity(in);

        }
    };

    @InjectView(R.id.content)
    TextView textResult;

    @InjectView(R.id.scrollView1)
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        
        TestState.reSet();

        //color init
        colorRender.initColor(ShowActivity.this);
        
        //textResult.setBackgroundColor(Color.BLACK);
        
        cmd=getIntent().getStringExtra("cmd");
        
        if( cmd == null || cmd.equals("")){
            Log.i(TAG, "do nothing,just show log");
        }else {

            MyLog.setLocalLogObject(new ParentLog() {
    
                @Override
                public void info(String arg0, String arg1) throws Exception {
                    Log.i("zhouhao2", "receive msg");
                    Message message = new Message();
                    message.what = APPEND;
                    message.obj = arg1;
                    messages.add(message);
                    Log.i("zhouhao2", "add to msg list");
                }
            });
    
            mTimeThread = new Thread() {
                public void run() {
                    timer.scheduleAtFixedRate(new TimerTask() {
    
                        @Override
                        public void run() {
                            if (messages.size() != 0) {
                                Log.i("zhouhao", "refresh ui");
                                Message message = messages.pollFirst();
                                handler.sendMessage(message);
                            } else {
                                Log.i("zhouhao", "pollfirst is null");
                            }
                        }
                    }, TIME_DELAY + 20, REFRESH_INTERVAL);
                };
            };
            
            mTimeThread.start();
    
            mCommandThread=new Thread() {
                public void run() {
                    Log.i("zhouaho2", "get cmd :" + cmd);
                    conCurCountDownWorker.setOneCmd(cmd);
                    
                    if(cmd.trim().equals("chinadrmstorage checkkey")){
                        conCurCountDownWorker.run(ConCurCountDownWorker.RESULT_RETURN);
                    }
                    else if(cmd.trim().equals("hs_oemcrypto_test")){
                        conCurCountDownWorker.run(ConCurCountDownWorker.RESULT_IN);
                    }
                };
            };
            
            mCommandThread.start();
        }
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public void onBackPressed() {
        Log.i(TAG, "on back pressed!!");
        finish();
        super.onBackPressed();
    }
    
}
