package com.hisense.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastTestReceiverRegister extends BroadcastReceiver{
    public static final String TAG="BroadcastTestReceiverRegister";
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.i(TAG, "receive my intent:"+intent.toString());
    }

}
