package com.ml.main;

import com.ml.logutils.LogUtils;
import com.ml.watchdog.MLWatchDog;
import com.ml.watchdog.MLWatchDog.Monitor;

public class DeadLockService implements Monitor{

public static String TAG = DeadLockService.class.getSimpleName();
	
	public DeadLockService() {
		LogUtils.i(TAG, "add DeadLockService to watchDog");
		MLWatchDog.getInstance().addMonitor(this);
	}

	public void monitor() {
		synchronized(this){
            LogUtils.i(TAG, "DeadLockService monitoring");
        }	
	}
	
	public static void run(String[] args) {
		
		DeadLockService test = new DeadLockService();
		
         final C1 c1 = test.new C1();
         
         final C2 c2= test.new C2();
			
			char a[] = {'a','z','A','Z'};
			
		new Thread(new Runnable() {
				
				public void run() {
					synchronized(this){
					   c1.test(c2);
					}
				}
			}).start();
			
			
        new Thread(new Runnable() {
				
				public void run() {
					synchronized(this){
					   c2.test(c1);
					}
				}
			}).start();
			
			for (int i = 0; i < a.length; i++) {
				System.out.println((int)a[i]);
			}
			
			
	}
	
	interface C{
		void test(C c);
	}
	
	class C1  implements C{

		public synchronized  void test(C c) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			c.test(this);
			
     
		}
		
	}
	
	class C2 implements C{
		
		public synchronized  void test(C c) {
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 c.test(this);
		}
		
	}
}


