package com.archermind.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AidlDemoActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private IKeyBinder mService;
	private final String TAG="client:AidlDemoActivity";
	private Binder binder = new MyBinder();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		Button bindBtn=(Button)findViewById(R.id.bindButton);
		bindBtn.setOnClickListener(this);

		Button funRigisterCallback = (Button)findViewById(R.id.registcallbak);
		funRigisterCallback.setOnClickListener(this);
		Button funUnRigisterCallback = (Button)findViewById(R.id.unregistercallback);
		funUnRigisterCallback.setOnClickListener(this);

    }
    
	@Override
	public void onClick(View v) {
		switch(v.getId()){

          case R.id.bindButton:
              Log.i(TAG, "onClick: bind");
                Intent intent=new Intent("com.archermind.aidl.myservice");
                bindService(intent,mConnection,BIND_AUTO_CREATE);
                break;
			case R.id.registcallbak:
				try {
					mService.registerCallBack(binder,mCallBack);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			case R.id.unregistercallback:
				try {
					mService.unregisterCallBack(binder);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
		  default:
			  break;
		}
	}
	
	private ServiceConnection mConnection=new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mService=IKeyBinder.Stub.asInterface(service);
			try {
				mService.registerCallBack(binder,mCallBack);
				Log.i(TAG, "onServiceConnected: callingPid ："+Binder.getCallingPid());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			Log.v(TAG,"onServiceConnected");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			try {
				mService.unregisterCallBack(binder);
				Log.i(TAG, "onServiceDisconnected: callingPid ："+Binder.getCallingPid());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			mService=null;
			Log.v(TAG,"onServiceDisconnected");
		}
	};
	
	private final IKeyEventCallBack.Stub mCallBack=new IKeyEventCallBack.Stub() {

		@Override
		public int onKeyEventCallBack(int keycode,boolean isDown,int repeatCount,int scancode,long downTime,long eventTime) throws RemoteException {
			Log.v(TAG,"onActionBack keycode= "+keycode);
			Log.i(TAG, "onActionBack: callingPid ："+Binder.getCallingPid());
			return MyService.PASS_TO_USER;
		}
	};

	private class MyBinder extends Binder{
           public MyBinder(){

		   }
	}

	@Override
	protected void onDestroy() {
		Log.i(TAG, "onDestroy: ");
		if(mService != null){
			unbindService(mConnection);
		}
		super.onDestroy();
	}
}