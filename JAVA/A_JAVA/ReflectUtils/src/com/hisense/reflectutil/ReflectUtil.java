package com.hisense.reflectutil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ����Ҫʵ��һ�����÷���ͨ�õĵ���api�Ĺ���
 * 
 * @author Jobs
 * 
 */
public class ReflectUtil {
	
	/**
	 * @param cls  ������������
	 * @param partypes ��������
	 * @param arglist  ʵ���б�
	 * @return  ���ع��������һ������
	 */
	private Object createFromConstructor(Class cls, Class partypes[],Object[] arglist){
		
         Constructor ct = null;
		try {
			ct = cls.getConstructor(partypes);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} 
        
		Object retobj=null;
         try {
			 retobj = ct.newInstance(arglist);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
         return retobj;
	}

	
	/**
	 * 
	 * @param className  ����
	 * @param paraTypes  �������͵��б�
	 * @param arglist    ʵ���б�
	 * @param returnType ��������
	 * @param fieldName  ���Ե�����
	 * @return  ����һ�����ԣ�����Ϊһ���ࣩ
	 */
	public Object getAttribute(String className,Class[] paraTypes,Object[] arglist,Class returnType,String fieldName){
		
		Class cls = null;
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Field fld=null;
		try {
			 fld = cls.getField(fieldName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		Object obj=createFromConstructor(cls,paraTypes,arglist);
		
		Object returnObj=null;
		try {
			returnObj = fld.get(obj);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	

	/**
	 * 
	 * @param className  ����
	 * @param paraTypes  �������͵��б�
	 * @param arglist    ʵ���б�
	 * @param returnType ��������
	 * @param fieldName  ���Ե�����
	 */
	public void setAttribute(String className,Class[] paraTypes,Object[] arglist,Class returnType,String fieldName,Object fieldValue){
		
		Class cls = null;
	
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Field fld=null;
		try {
			 fld = cls.getField(fieldName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		Object obj=createFromConstructor(cls,paraTypes,arglist);
		
		try {
			fld.set(obj,fieldValue);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param className ���ص�����
	 * @param paraTypes ��������
	 * @param arglist   ʵ���б�
	 * @param returnType  ��������
	 * @param methodName ������
	 * @param methodTypes ������������
	 * @param methodArgs ������ʵ���б�
	 * @return   �����ķ���
	 */
	public Object invokeMethod(String className,Class[] paraTypes,Object[] arglist,Class returnType,
			String methodName,Class methodTypes[],Object[] methodArgs){
		
		Object returnObject=null;
		try {
		    Class<?> c = Class.forName(className);
		    Method main = c.getDeclaredMethod(methodName, methodTypes);
	  	 
		    returnObject= main.invoke(null,methodArgs);

	        // production code should handle these exceptions more gracefully
		} catch (ClassNotFoundException x) {
		    x.printStackTrace();
		} catch (NoSuchMethodException x) {
		    x.printStackTrace();
		} catch (IllegalAccessException x) {
		    x.printStackTrace();
		} catch (InvocationTargetException x) {
		    x.printStackTrace();
		}
	
		return returnObject;
	}
	
	
	
 
	
	
	
}
