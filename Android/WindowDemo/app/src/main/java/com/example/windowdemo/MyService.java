package com.example.windowdemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


public class MyService extends Service {
    private Button demoBtn = null;
    private WindowManager mWindowManager = null;
    private WindowManager.LayoutParams lay;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("zhouhao2", "onStartCommand: ");
        init();
        return super.onStartCommand(intent, flags, startId);
    }

    private void init() {

        Log.i("service", "init: ");

        mWindowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);

        View layout = LayoutInflater.from(this.getApplicationContext()).inflate(R.layout.layout,null);

        lay = new WindowManager.LayoutParams();

        //set default parameters
        updateDate(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, Gravity.LEFT | Gravity.TOP, 100, 300);

        mWindowManager.addView(layout, lay);
    }

    public void updateDate(int type,int flag,int gravity,int x,int y) {
        //set custom width and height and alpha
        lay.alpha = 1.0f;
        lay.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lay.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lay.type = type;
        lay.flags= flag;
        lay.gravity = gravity;
        lay.x = x;
        lay.y = y;
        lay.format = PixelFormat.TRANSLUCENT;
    }
}
