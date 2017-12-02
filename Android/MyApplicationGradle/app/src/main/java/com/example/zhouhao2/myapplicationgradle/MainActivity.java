package com.example.zhouhao2.myapplicationgradle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private KeySequenceListenner mKeySequenceListenner = new KeySequenceListenner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(mKeySequenceListenner.shouldPopWindow(keyCode)){
            Log.i(TAG, "start uifac ");
        }
        Log.i(TAG, "super.onKeyUp(keyCode,event) ");
        return super.onKeyUp(keyCode,event);
    }
}
