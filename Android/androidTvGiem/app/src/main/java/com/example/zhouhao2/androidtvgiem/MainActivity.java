package com.example.zhouhao2.androidtvgiem;

import android.content.Context;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);
        Button mBtnNewTest = (Button) findViewById(R.id.button_new_test);
        Button mBtnNewTest_prohibit = (Button) findViewById(R.id.btn_prohibit);
        Button mBtnNewTest_release = (Button) findViewById(R.id.btn_release);
        Button mBtnNewTest_restore = (Button) findViewById(R.id.btn_retore);
        PowerManager pm=(PowerManager)getSystemService(Context.POWER_SERVICE);


        mBtnNewTest_prohibit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewInvokerExample.getInstance(MainActivity.this.getApplicationContext()).startProhibit();
            }
        });
        mBtnNewTest_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewInvokerExample.getInstance(MainActivity.this.getApplicationContext()).startRelease();
            }
        });
        mBtnNewTest_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewInvokerExample.getInstance(MainActivity.this.getApplicationContext()).startRestore();
            }
        });
    }
}
