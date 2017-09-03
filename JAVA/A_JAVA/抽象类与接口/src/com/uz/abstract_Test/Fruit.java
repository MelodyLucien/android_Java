package com.uz.abstract_Test;
/*
     抽象类就是用来被继承的，其中可以有实现了的方法，和抽象方法

*/
abstract class Fruit {
	
	public void cliam(){
		System.out.println("我是水果的祖先");
	}
	public abstract void harvest();
}
