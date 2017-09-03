package com.hisense.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastTestReceiver extends BroadcastReceiver{
    public static final String TAG="BroadcastTestReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "receive my intent:"+intent.toString());
    }

}