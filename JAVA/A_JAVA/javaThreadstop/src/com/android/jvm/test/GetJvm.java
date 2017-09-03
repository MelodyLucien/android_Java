package com.android.jvm.test;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GetJvm {
	
	/**
	 * 获取运行栈的相关信息
	 */
	public void getStacks(){
		StackTraceElement[] elem=Thread.currentThread().getStackTrace();
		  for(int i=0;i<elem.length;i++){
		   System.out.println(elem[i]);
		  }
	}
	
	
	/**
	 * 获取所有运行线程的栈区的信息
	 */
	public void getAllStacks(){
	  StackTraceElement[] s;
	  Thread t;
	  Map<Thread,StackTraceElement[]> m=Thread.getAllStackTraces();//返回一个Map，只是其中的key
	                                                               //和Vlaue都是<>中指定的类型
	  for(int i=0;i<m.size();i++){
	   Set<Thread>t1=m.keySet();//返回一个set 其中的元素是Thread类型的
	   Iterator<Thread> it=t1.iterator();
	   while(it.hasNext()){
	    t=it.next();
	    System.out.println(t);
	   }
	   Collection<StackTraceElement[]> s1=m.values();//返回一个collection，只是其中的元素是  
	                                                 //StatckTraceElement数组
	   Iterator<StackTraceElement[]> i1=s1.iterator();
	   
	   System.out.println("共有线程数为");
	   while(i1.hasNext()){
	    s=i1.next();
	    for(int j=0;j<s.length;j++)
	     System.out.println(s[j]);
	   }
	   
	  }
	}
	
	
}
