package com.my.member;

public class Student  extends People {

	public Student(String name, int age) {
		super(name, age);
	}

	@Override
	public String toString() {
		return "Student [getName()=" + getName() + ", getAge()=" + getAge()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	

}
