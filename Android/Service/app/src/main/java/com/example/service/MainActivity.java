package com.example.service;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	
    private Button startbtn,stopbtn,bindbtn,unbindbtn;
    
    
    private ServiceConnection conn=new ServiceConnection() {
		
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("service", "onServiceConnected");
			Toast.makeText(MainActivity.this, "onServiceConnected", Toast.LENGTH_LONG).show();
			
		}public void onServiceDisconnected(ComponentName name) {
			Log.i("service", "onServiceDisConnected");
			Toast.makeText(MainActivity.this, "onServiceDisConnected", Toast.LENGTH_LONG).show();
			
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listenner li = new listenner();
        startbtn=(Button)findViewById(R.id.startbtn);
        stopbtn=(Button)findViewById(R.id.stopbtn);
        bindbtn=(Button)findViewById(R.id.bindbtn);
        unbindbtn=(Button)findViewById(R.id.unbindbtn);
        startbtn.setOnClickListener(li);
        stopbtn.setOnClickListener(li);
        bindbtn.setOnClickListener(li);
        unbindbtn.setOnClickListener(li);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    

    class listenner implements OnClickListener{

		public void onClick(View v) {
			if(v==startbtn)
			{
				Intent in = new Intent("com.dh.MY_SERVICE");
				startService(in);
			}
			if(v==stopbtn)
			{
				Intent in=new Intent("com.dh.MY_SERVICE");
				stopService(in);
			}
			if(v==bindbtn)
			{
				Intent in =new Intent("com.dh.MY_SERVICE");
				bindService(in, conn,Service.BIND_AUTO_CREATE);
			}
			if(v==unbindbtn)
			{
				Intent in = new Intent("com.dh.MY_SERVICE");
				unbindService(conn);
			}
		}
    }
}
