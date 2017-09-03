package com.my.counter;

public  class Counter2 {
	
	Icounter counter;
 
	public Counter2(Icounter counter) {
		this.counter = counter;
	}

	public void  run() {
		
		int SUM=0;
		int  n=getCount();
		for (int i = 0; i < n; i++) {
			SUM+=i;
		}
		
		System.out.println(SUM);
	 
	}
	 
	public int getCount(){
		return counter.getCount();
	};
}
 
