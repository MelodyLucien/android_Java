package com.archermind.os;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.IVirusVerifier;
import android.os.IVerifyObserver;
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
                Intent intent=new Intent("com.archermind.aidl.MyService");
                bindService(intent,mConnection,BIND_AUTO_CREATE);
                break;
			case R.id.fuc02Button:
				if(mService != null){
					if(mCallBack!=null){
						try {
							mService.registerCallBack(new Binder(),mCallBack);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
				}
		  default:
			  break;
		}
	}
	
	private ServiceConnection mConnection=new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mService=IVirusVerifier.Stub.asInterface(service);
			Log.v(TAG,"onServiceConnected");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mService=null;
			Log.v(TAG,"onServiceDisconnected");
		}
	};

    private IVerifyObserver mCallBack = new IVerifyObserver.Stub(){

        @Override
        public boolean onVerify(String path) throws RemoteException {
/*            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            Log.i(TAG, "onVerify: "+path);
            return false;
        }
    };
}