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
	private ITaskBinder mService;
	private final String TAG="client:AidlDemoActivity";
	private IBinder binder = new MyBinder();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		Button bindBtn=(Button)findViewById(R.id.bindButton);
		bindBtn.setOnClickListener(this);

        Button fuc01=(Button)findViewById(R.id.fuc01Button);
        fuc01.setOnClickListener(this);
        Button fuc02=(Button)findViewById(R.id.fuc02Button);
        fuc02.setOnClickListener(this);
        Button fuc03=(Button)findViewById(R.id.fuc03Button);
        fuc03.setOnClickListener(this);
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

		  case R.id.fuc01Button:
              Log.i(TAG, "onClick: fuc01");
              try {
				mService.fuc01();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			  break;
		  case R.id.fuc02Button:
              Log.i(TAG, "onClick: fuc02");
              try {
				mService.fuc02();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			  break;
		  case R.id.fuc03Button:
              Log.i(TAG, "onClick: fuc03");
              try {
				Person person=new Person();
				person.setSex(0);
				person.setName("zhouhao");
				person.setDescrip("CTO");
				String ret=mService.fuc03(person);
				Log.i(TAG,"ret="+ret);

			} catch (RemoteException e) {
				e.printStackTrace();
			}
			  break;
			case R.id.registcallbak:
				try {
					mService.registerCallBack(binder,null);
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
			mService=ITaskBinder.Stub.asInterface(service);
			try {
				mService.registerCallBack(binder,mCallBack);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			Log.v(TAG,"onServiceConnected");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			try {
				mService.unregisterCallBack(binder);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			mService=null;
			Log.v(TAG,"onServiceDisconnected");
		}
	};
	
	private final ITaskCallBack.Stub mCallBack=new ITaskCallBack.Stub() {

		@Override
		public int onActionBack(String str) throws RemoteException {
			Log.v(TAG,"onActionBack str="+str);
			return 0;
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