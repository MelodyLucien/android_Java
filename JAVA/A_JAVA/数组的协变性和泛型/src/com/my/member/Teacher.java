package com.my.member;

public class Teacher extends People{

	public Teacher(String name, int age) {
		super(name, age);
	}

	@Override
	public String toString() {
		return "Teacher [getName()=" + getName() + ", getAge()=" + getAge()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}


	
	
}
