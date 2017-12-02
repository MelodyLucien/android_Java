package com.example.progressbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    public static final String TAG=MyReceiver.class.getSimpleName().toString();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: "+intent.toString());
        int action_type=intent.getIntExtra("volume_ctrl_key_action_type",0);
        int keycode = intent.getIntExtra("android.intent.extra.KEY_EVENT",25);
        int repeated_num = intent.getIntExtra("volume_ctrl_key_repeated_num",1);

        Log.i(TAG, "action_type  : "+action_type+"\n"
                  +"keycode      : "+keycode+"\n"
                  +"repeated_num : "+repeated_num);

        boolean isDown = (action_type == 0) ? true:false;

        boolean isIncrease = (keycode == 24) ? true:false;

        int step =  repeated_num;

        Log.i(TAG, "onReceive: i am ok");

        //MyLocalStartService.notify(isIncrease,step);

//        try {
//            Thread.sleep(50l);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
