package com.android.test.test;

public class Running implements Runnable{
	boolean brun=false; 
	@Override
	public void run() {
		while(!brun)
		{
			System.out.println(Thread.currentThread().getName()+"  is running");
		}
		
	}
	
	public synchronized void StopMe() {
	  
       brun=true;
       System.out.println("i stop");
    }

}
