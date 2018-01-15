package com.archermind.os;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.KeysVirusVerifierManager;
import android.os.Looper;
import android.os.RemoteException;
import android.os.IVerifyObserver;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class AidlDemoActivity extends Activity implements OnClickListener {

	private final String TAG="client:AidlDemoActivity";
	private Button fuc02Button = null;
    private KeysVirusVerifierManager mKeysVirusVerifierManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mKeysVirusVerifierManager = KeysVirusVerifierManager.getInstance();
		Button bindBtn=(Button)findViewById(R.id.bindButton);
		fuc02Button = (Button)findViewById(R.id.fuc02Button);
		bindBtn.setOnClickListener(this);
        fuc02Button.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.fuc02Button:
			    if(mKeysVirusVerifierManager.isServiceAvaiable()) {
                    mKeysVirusVerifierManager.registerCallBack(mCallBack);
                }
		  default:
			  break;
		}
	}

    private IVerifyObserver mCallBack = new IVerifyObserver.Stub(){

        @Override
        public boolean onVerify(String path) throws RemoteException {
            Log.i(TAG, "onVerify: "+path);
            return false;
        }
    };

    class MyHandler extends Handler{
        public MyHandler(Looper looper){
            super(looper);
        }
    }

}
