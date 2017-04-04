package com.zhouhao2.strings;

import java.io.File;

public class Test {
	
	public static void main(String[] args) {
		
		  Test test = new Test();
		
           C1 c1 = test.new C1();
           
           C2 c2= test.new C2();
			
			char a[] = {'a','z','A','Z'};
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					c1.test(c2);
				}
			}).start();
			
			
         new Thread(new Runnable() {
				
				@Override
				public void run() {
					c2.test(c1);
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

		@Override
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
		
		@Override
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
