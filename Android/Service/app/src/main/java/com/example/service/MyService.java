package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		Log.i("service","onBind");
		Toast.makeText(MyService.this, "onbindingMyService", Toast.LENGTH_LONG).show();
		return null;
	}
	@Override
	public void unbindService(ServiceConnection conn) {
		Log.i("service","unbindService");
		Toast.makeText(MyService.this, "unbindingMyService", Toast.LENGTH_LONG).show();
		
	}
	@Override
	public void onCreate() {
		Log.i("service","onCreate");
		Toast.makeText(MyService.this, "oncreatingMyService", Toast.LENGTH_LONG).show();
	}
	
	public void onStart(Intent intent, int startId) {
		Log.i("service","onStart");
		Toast.makeText(MyService.this, "onstartingMyService", Toast.LENGTH_LONG).show();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		return 0;

	}

	@Override
	public void onDestroy() {
		Log.i("service","onDestroy");
		Toast.makeText(MyService.this, "ondestoryMyService", Toast.LENGTH_LONG).show();
	}

}
