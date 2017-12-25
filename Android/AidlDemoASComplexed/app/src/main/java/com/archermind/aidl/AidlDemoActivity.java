package com.archermind.aidl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AidlDemoActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */

	private final String TAG="client:AidlDemoActivity";
	private GlobalKeyMonitorManager mGlobalKeyMonitorManager = null;

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
		mGlobalKeyMonitorManager = GlobalKeyMonitorManager.getInstance(this.getApplicationContext());
    }
    
	@Override
	public void onClick(View v) {
		switch(v.getId()){

          case R.id.bindButton:
                Log.i(TAG, "onClick: bind");
			    mGlobalKeyMonitorManager.bind();
                break;
			case R.id.registcallbak:
               int[] keys = new int[]{62,128,4};
				/* try {
					GlobalInputEventMonitorRequest.RequestItem requestItem= new GlobalInputEventMonitorRequest.RequestItem(GlobalInputEventMonitorRequest.InputEventType.MOTION_EVENT,
							KeyEvent.KEYCODE_MENU,GlobalInputEventMonitorRequest.Action.FILTER_KEEP_MONITOR,new Intent("sdfd"));
					GlobalInputEventMonitorRequest request=new GlobalInputEventMonitorRequest(GlobalInputEventMonitorRequest.RequestType.MONITOR_EVENT);
					request.setRequestItems(new GlobalInputEventMonitorRequest.RequestItem[]{requestItem});
					mGlobalKeyMonitorManager.processMonitorRequest("zhouhao2", request);
				} catch (RemoteException e) {
					e.printStackTrace();
				}*/
				mGlobalKeyMonitorManager.releaseKeysOnly(keys);
				break;
			case R.id.unregistercallback:
/*				try {
					mGlobalKeyMonitorManager.processMonitorCancel("zhouhao2");
				} catch (RemoteException e) {
					e.printStackTrace();
				}*/
				mGlobalKeyMonitorManager.restoreKeys();
				break;
		  default:
			  break;
		}
	}

	@Override
	protected void onDestroy() {
		Log.i(TAG, "onDestroy: ");
        if(mGlobalKeyMonitorManager != null){
			mGlobalKeyMonitorManager.unBind();
		}
		super.onDestroy();
	}
}