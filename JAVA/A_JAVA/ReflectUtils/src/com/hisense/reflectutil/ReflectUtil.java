package com.hisense.reflectutil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 此类要实现一个利用反射通用的调用api的功能
 * 
 * @author Jobs
 * 
 */
public class ReflectUtil {
	
	/**
	 * @param cls  构造器的类名
	 * @param partypes 参数类型
	 * @param arglist  实参列表
	 * @return  返回构造出来的一个对象
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
	 * @param className  类名
	 * @param paraTypes  参数配型的列表
	 * @param arglist    实参列表
	 * @param returnType 返回类型
	 * @param fieldName  属性的名称
	 * @return  返回一个属性（可能为一个类）
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
	 * @param className  类名
	 * @param paraTypes  参数配型的列表
	 * @param arglist    实参列表
	 * @param returnType 返回类型
	 * @param fieldName  属性的名称
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
	 * @param className 加载的类名
	 * @param paraTypes 参数类型
	 * @param arglist   实参列表
	 * @param returnType  返回类型
	 * @param methodName 方法名
	 * @param methodTypes 方法参数类型
	 * @param methodArgs 方法的实参列表
	 * @return   方法的返回
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
