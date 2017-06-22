package com.example.windowdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 
 * @author zhouhao2
 *
 */
public class BootShutDownReceiver extends BroadcastReceiver{

    public static final String TAG="BootShutDownReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "receive my intent:"+intent.toString());
        
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Intent in = new Intent(context, MyLocalStartService.class);
            context.startService(in);
        }else if(Intent.ACTION_SHUTDOWN.equals(intent.getAction())){

            //enable all list packages

        }
    }

}
