package com.android.jvm.test;

public class test {
	
	public static void main(String[] args) {
       GetJvm getjvm=new GetJvm();
       
       System.out.println("��ȡ��ǰ�����̵߳���Ϣ����");
       getjvm.getStacks();
       
       
       System.out.println("��ȡ���еĵ������̵߳�����ʱ��ջ����Ϣ");
       getjvm.getAllStacks();
	}
}
