package com.my.counter;


//eit�ĵڶ��ֱ����������㷨
public class myCounter2  {
 
		 
public static void main(String[] args) {
		new Counter2(new Icounter() {
			
			@Override
			public int getCount() {
				return 6;
			}
		}).run();
	}
}
 
