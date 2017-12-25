package com.archermind.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AidlDemoActivity extends Activity implements OnClickListener {

	private final String TAG="client:AidlDemoActivity";
	private IVirusVerifier mService = null;
	private Button fuc02Button = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Button bindBtn=(Button)findViewById(R.id.bindButton);
		fuc02Button = (Button)findViewById(R.id.fuc02Button);
		bindBtn.setOnClickListener(this);
        fuc02Button.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){

          case R.id.bindButton:
              Log.i(TAG, "onClick: bind");
                Intent intent=new Intent("com.archermind.aidl.myservice");
                bindService(intent,mConnection,BIND_AUTO_CREATE);
                break;
		  default:
			  break;
		}
	}
	
	private ServiceConnection mConnection=new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mService=IVirusVerifier.Stub.asInterface(service);
			try {
				mService.registerCallBack(mCallBack);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			Log.v(TAG,"onServiceConnected");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mService=null;
			Log.v(TAG,"onServiceDisconnected");
		}
	};

    private VerifyObserver mCallBack = new VerifyObserver.Stub(){

        @Override
        public boolean onVerify(String path) throws RemoteException {
            Log.i(TAG, "onVerify: "+path);
            return false;
        }
    };
}