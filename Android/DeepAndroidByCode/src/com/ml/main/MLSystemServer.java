package com.ml.main;

import com.ml.logutils.LogUtils;
import com.ml.watchdog.MLWatchDog;

public class MLSystemServer{
	
	private static final int Systemserverintervaltime = 2000;

	private static final String TAG = MLSystemServer.class.getSimpleName();
	
	public MLActivityManagerService mlams;

	public void main(String[] args) {
		
		mlams= new MLActivityManagerService();
		
		MLWatchDog.getInstance().start();
		
		while(true)
		{
			try {
				Thread.sleep(Systemserverintervaltime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			LogUtils.i(TAG, "system_server do.....");
		}
	}
}
