package com.example.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.accessibilityservicetest.MainActivity;

import static com.example.accessibilityservicetest.MainActivity.TAG;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.i(TAG, "onReceive: "+intent.getAction().toString());

        startMainActivity(context);
    }

    private void startMainActivity(Context con){
        Intent in = new Intent();
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        in.setClass(con, MainActivity.class);
        con.startActivity(in);
    }


}
