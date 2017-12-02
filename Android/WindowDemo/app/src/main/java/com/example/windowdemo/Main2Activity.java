package com.example.windowdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {

    private WindowManager mWindowManager = null;

    private WindowManager.LayoutParams mLayoutParams = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.CENTER);
        setContentView(R.layout.activity_main);

/*        Intent in = new Intent();
        in.setClass(this,MyService.class);
        startService(in);*/
       //init();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    private void init() {
        Log.i("service", "init: ");

        mWindowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);

        View mLayoutParamsout = LayoutInflater.from(this.getApplicationContext()).inflate(R.layout.layout,null);

        mLayoutParams = new WindowManager.LayoutParams();

        //set default parameters
        updateDate(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, Gravity.LEFT | Gravity.TOP, 100, 300);

        mWindowManager.addView(mLayoutParamsout, mLayoutParams);
    }

    public void updateDate(int type,int flag,int gravity,int x,int y) {
        //set custom width and height and alpha
        mLayoutParams.alpha = 1.0f;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.type = type;
        mLayoutParams.flags= flag;
        mLayoutParams.gravity = gravity;
        mLayoutParams.x = x;
        mLayoutParams.y = y;
        mLayoutParams.format = PixelFormat.TRANSLUCENT;
    }

}
