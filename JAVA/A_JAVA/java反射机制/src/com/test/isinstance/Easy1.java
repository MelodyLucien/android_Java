package com.test.isinstance;
import java.lang.reflect.Method;

/*
 * �����ܹ������ǣ�
1.������ʱ����������ͣ�
2.��̬����ĳ����Ķ���
3.���������Ժͷ�����
4.������ö���ķ�����
5.�޸Ĺ��캯�������������ԵĿɼ��ԣ�
6.����������Ե�ֵ
*/
public class Easy1 {
	
public static void main(String[] args) {
	try {
		
		
		 Class c = Class.forName("java.util.Stack"); 
		 
		 //��ȡ�������͵���ķ��������к��߱�ʾ��ȡ�������͵ķ�װ��ķ���
         //Class c = int.class; ���� Class c = Integer.TYPE;
          
         Method m[] = c.getDeclaredMethods(); 
         
     
         for (int i = 0; i < m.length; i++) 
             System.out.println(m[i].toString()); //��ӡ��������ԭ��
         
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
  }
}
