package com.example.progressbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	private ProgressBar ps,pl;
	private ProgressBar ph;
	private Button btn;

    public static final String TAG = MainActivity.class.getSimpleName().toString();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.i(TAG, "onKeyDown: key down "+keyCode);

/*        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            Intent it = new Intent();
            it.setAction("tv.policy.SYSTEM_KEY");
            it.putExtra("android.intent.extra.KEY_EVENT",24);
            it.putExtra("volume_ctrl_key_action_type",0);
            sendBroadcast(it,null);
        }else if(keyCode ==  KeyEvent.KEYCODE_DPAD_LEFT){
            Intent it = new Intent();
            it.setAction("tv.policy.SYSTEM_KEY");
            it.putExtra("android.intent.extra.KEY_EVENT", 25);
            it.putExtra("volume_ctrl_key_action_type",0);
            sendBroadcast(it,null);
        }*/

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

            Intent it = new Intent();
            it.setAction("tv.policy.SYSTEM_BACK");
            it.putExtra("android.intent.extra.KEY_EVENT", 25);
            it.putExtra("volume_ctrl_key_action_type",0);
            sendBroadcast(it,"com.hisense.permission.monitorkeys");

        super.onBackPressed();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        Log.i(TAG, "onKeyUp: key up "+keyCode);

        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            Intent it = new Intent();
            it.setAction("tv.policy.SYSTEM_KEY");
            it.putExtra("android.intent.extra.KEY_EVENT",24);
            it.putExtra("volume_ctrl_key_action_type",1);
            sendBroadcast(it,null);
        }else if(keyCode ==  KeyEvent.KEYCODE_DPAD_LEFT){
            Intent it = new Intent();
            it.setAction("tv.policy.SYSTEM_KEY");
            it.putExtra("android.intent.extra.KEY_EVENT", 25);
            it.putExtra("volume_ctrl_key_action_type",1);
            sendBroadcast(it,null);
        }

        return super.onKeyUp(keyCode, event);
    }
}
