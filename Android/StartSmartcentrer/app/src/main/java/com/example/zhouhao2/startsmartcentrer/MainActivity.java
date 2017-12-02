package com.example.zhouhao2.startsmartcentrer;

import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    TextView tv = null;
    Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            Trace.beginSection("computeNumber");
            // computeNumber();
            //getActivity();
            screenLock();
            Trace.endSection();
            sendEmptyMessageDelayed(0,1000);
        }

        private void computeNumber(){
            int i = 0;
            while(i<10000){
                Log.i(TAG, "computeNumber: "+i);
                ++i;
            }
        }
    };

    PackageManager packageManager = null;
    KeyguardManager keyguardManager = null;
    PowerManager powerManager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        packageManager = getPackageManager();
        keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mHandler.sendEmptyMessage(0);
    }

    private void getActivity() {
        ComponentName cm = new ComponentName("com.jamdeo.smartsensecenter", "com.jamdeo.tilelibrary.VidaaTileService");
        try {
          ServiceInfo serviceInfo = packageManager.getServiceInfo(cm,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void screenLock(){
        PowerManager.WakeLock wl = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "zhouhao2");
        wl.acquire();
        try {
            Log.i(TAG, "screenLock: ");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wl.release();

    }


}
