package com.my.counter;

public abstract class Counter {
 
	public void  run() {
		
		int SUM=0;
		int  n=getCount();
		for (int i = 0; i < n; i++) {
			SUM+=i;
		}
		
		System.out.println(SUM);
	 
	}
	 
	public abstract int getCount();
}
 
