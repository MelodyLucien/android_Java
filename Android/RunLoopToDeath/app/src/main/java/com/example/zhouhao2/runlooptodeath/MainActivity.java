package com.example.zhouhao2.runlooptodeath;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void runLoopToDeath(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isTv = false;
                do{
                    isTv = Settings.Global.getInt(getContentResolver(),"sys.hisense.mm",0) == 0 ? false:true ;
                }while (!isTv);
            }
        }).start();
    }
}
