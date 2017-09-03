package com.android.test.test;

public class Test {
    public static void main(String[] args) {
    	Running r = new Running();
		Thread t = new Thread(r);
		t.setName("Running");
		t.start();
		for (int i = 0; i < 10; i++) {
			System.out.println(i+"  "+Thread.currentThread().getName()+" is running");
			if (i==5) {
				 r.StopMe();
			}
		}
	}
}
