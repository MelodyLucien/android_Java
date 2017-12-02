package com.example.windowdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving\
        Log.i("zhouhao2", "onReceive: "+intent.getAction().toString());
        startMyService(context);
    }

    private void startMyService(Context context){
        Intent in = new Intent();
        in.setClass(context,com.example.windowdemo.MyService.class);
        context.startService(in);
    }
}
