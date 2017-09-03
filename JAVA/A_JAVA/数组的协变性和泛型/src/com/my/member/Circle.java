package com.my.member;

public class Circle extends Shape {

	private int center;
	private int ridus;

	public Circle(int area) {
		super(area);
	}
	
	public Circle(int center,int ridus){
		super(ridus*ridus*Math.PI);
		this.center=center;
		this.ridus=ridus;
	}
}
