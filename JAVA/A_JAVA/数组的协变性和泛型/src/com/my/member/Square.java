package com.my.member;

public class Square extends Shape {
	
	private int width;
	private int height;

	public Square(int area) {
		super(area);
	}
	
	/**
	 * 重载初始化的方法
	 */
	public Square(int width,int height){
		super(width*height);
		this.width=width;
		this.height=height;
	}
	
	

}
