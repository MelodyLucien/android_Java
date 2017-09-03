package com.android.jvm.test;

public class test {
	
	public static void main(String[] args) {
       GetJvm getjvm=new GetJvm();
       
       System.out.println("获取当前运行线程的信息！！");
       getjvm.getStacks();
       
       
       System.out.println("获取所有的的运行线程的运行时的栈的信息");
       getjvm.getAllStacks();
	}
}
