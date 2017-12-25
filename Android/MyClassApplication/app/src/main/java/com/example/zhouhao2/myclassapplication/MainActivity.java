package com.example.zhouhao2.myclassapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Property;

import java.util.Properties;

import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String libraryPath = System.getProperty("java.library.path");
        Properties p = System.getProperties();
        Log.i(TAG, "onCreate: "+p.toString());
        Log.i(TAG, "onCreate: "+libraryPath);
    }
}
