package com.my.counter;

//eit的继承方式来进行设计
public class myCounter extends Counter {
 
	
	public int getCount() {
		return 6;
	}
	 
	public static void main(String[] args) {
		new myCounter().run();
	}
}
 
