package com.ml.main;

import com.ml.logutils.LogUtils;
import com.ml.watchdog.MLWatchDog;
import com.ml.watchdog.MLWatchDog.Monitor;


public class MLActivityManagerService implements Monitor {
	
	public static String TAG = MLActivityManagerService.class.getSimpleName();
	
	public MLActivityManagerService() {
		LogUtils.i(TAG, "add mlams to watchDog");
		MLWatchDog.getInstance().addMonitor(this);
	}

	public void monitor() {
		synchronized(this){
            LogUtils.i(TAG, "ml_ams monitoring");
        }	
	}

}
