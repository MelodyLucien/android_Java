package com.ml.watchdog;

import java.util.ArrayList;

import com.ml.logutils.LogUtils;

public class MLWatchDog extends Thread{
	
	private static final String TAG = MLWatchDog.class.getSimpleName();

	private static final long MAX_TIME_OUT = 3000;
	
	private static MLWatchDog mWatchdog;
	
	private static ArrayList<Monitor> monitors = new ArrayList<MLWatchDog.Monitor>();
	
	
	private MLWatchDog(){
		
	}
	
	public static MLWatchDog getInstance(){
		
		if(mWatchdog== null){
			mWatchdog = new MLWatchDog();
		}
		return mWatchdog;
	}
	

	public interface Monitor{
		void monitor();
	}
	
	@Override
	public void run() {
		LogUtils.i(TAG, "run MLwatchdog");
		int N = monitors.size();
		while(true){
			for (int i = 0; i < N; i++) {
				monitors.get(i).monitor();
			}
			
			try {
				Thread.sleep(MAX_TIME_OUT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void addMonitor(Monitor monitor){
		monitors.add(monitor);
	}
}
