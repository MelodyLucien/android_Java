package com.android.jvm.test;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GetJvm {
	
	/**
	 * ��ȡ����ջ�������Ϣ
	 */
	public void getStacks(){
		StackTraceElement[] elem=Thread.currentThread().getStackTrace();
		  for(int i=0;i<elem.length;i++){
		   System.out.println(elem[i]);
		  }
	}
	
	
	/**
	 * ��ȡ���������̵߳�ջ������Ϣ
	 */
	public void getAllStacks(){
	  StackTraceElement[] s;
	  Thread t;
	  Map<Thread,StackTraceElement[]> m=Thread.getAllStackTraces();//����һ��Map��ֻ�����е�key
	                                                               //��Vlaue����<>��ָ��������
	  for(int i=0;i<m.size();i++){
	   Set<Thread>t1=m.keySet();//����һ��set ���е�Ԫ����Thread���͵�
	   Iterator<Thread> it=t1.iterator();
	   while(it.hasNext()){
	    t=it.next();
	    System.out.println(t);
	   }
	   Collection<StackTraceElement[]> s1=m.values();//����һ��collection��ֻ�����е�Ԫ����  
	                                                 //StatckTraceElement����
	   Iterator<StackTraceElement[]> i1=s1.iterator();
	   
	   System.out.println("�����߳���Ϊ");
	   while(i1.hasNext()){
	    s=i1.next();
	    for(int j=0;j<s.length;j++)
	     System.out.println(s[j]);
	   }
	   
	  }
	}
	
	
}
