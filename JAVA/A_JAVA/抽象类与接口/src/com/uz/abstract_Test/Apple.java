package com.uz.abstract_Test;

public class Apple extends Fruit{
	
	public String color="��";
  
	//ͨ����д��ʵ�ַ�����ʵ��0
	@Override
	public void harvest() {
		System.out.println("��������,����ƻ��");
	}
	
	 @Override
		public String toString() {
			return "Apple [color=" + color + "]";
		}
	

}
