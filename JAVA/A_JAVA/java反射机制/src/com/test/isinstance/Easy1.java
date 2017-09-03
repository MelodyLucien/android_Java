package com.test.isinstance;
import java.lang.reflect.Method;

/*
 * 反射能够让我们：
1.在运行时检测对象的类型；
2.动态构造某个类的对象；
3.检测类的属性和方法；
4.任意调用对象的方法；
5.修改构造函数、方法、属性的可见性；
6.更改类的属性的值
*/
public class Easy1 {
	
public static void main(String[] args) {
	try {
		
		
		 Class c = Class.forName("java.util.Stack"); 
		 
		 //获取基本类型的类的方法。其中后者表示获取基本类型的封装类的方法
         //Class c = int.class; 或者 Class c = Integer.TYPE;
          
         Method m[] = c.getDeclaredMethods(); 
         
     
         for (int i = 0; i < m.length; i++) 
             System.out.println(m[i].toString()); //打印出方法的原型
         
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
  }
}
