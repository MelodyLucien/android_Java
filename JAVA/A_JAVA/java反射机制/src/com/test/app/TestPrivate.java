package com.test.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestPrivate {
	
	
	public int add(int i,int j){
		
		return i+j;
	}
	
public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	  Class cls;
	try {
		cls = Class.forName("com.test.app.TestPrivate");
		 Class partypes[] = new Class[2]; 
	      partypes[0] = Integer.TYPE; 
	      partypes[1] = Integer.TYPE; 
	  
	      Method meth = cls.getMethod("add", partypes); 
	      TestPrivate methobj = new TestPrivate(); 
	      Object arglist[] = new Object[2]; 
	      arglist[0] = new Integer(37); 
	      arglist[1] = new Integer(47); 
	      Object retobj = meth.invoke(methobj, arglist); 
	      Integer retval = (Integer) retobj; 
	      System.out.println(retval.intValue()); 
	      
	      
	} catch (ClassNotFoundException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	} 
     
}
}
