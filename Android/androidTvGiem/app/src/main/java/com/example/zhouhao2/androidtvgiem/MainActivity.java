package com.example.zhouhao2.androidtvgiem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private AIKeyEventSource mAIKeyEventSource = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAIKeyEventSource=AIKeyEventSource.getInstance(getApplicationContext());
    }
}
