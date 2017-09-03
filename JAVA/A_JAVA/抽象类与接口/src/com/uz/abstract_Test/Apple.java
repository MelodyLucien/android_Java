package com.uz.abstract_Test;

public class Apple extends Fruit{
	
	public String color="红";
  
	//通过重写来实现方法的实现0
	@Override
	public void harvest() {
		System.out.println("丰收了我,我是苹果");
	}
	
	 @Override
		public String toString() {
			return "Apple [color=" + color + "]";
		}
	

}
