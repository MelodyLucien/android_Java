package com.test.app;

import java.lang.reflect.*;  
public class GetFields { 
   private double d; 
   public static final int i = 37; 
   String s = "testing";  
   public static void main(String args[]) { 
	   
      try { 
    	  
           Class cls = Class.forName("com.test.app.GetFields"); 
           //��ȡ�����ĳ�Ա��������Ϣ
           Field fieldlist[] = cls.getDeclaredFields(); 
           for (int i = 0; i < fieldlist.length; i++) { 
              Field fld = fieldlist[i]; 
              System.out.println("name = " + fld.getName()); 
              System.out.println("decl class = " + fld.getDeclaringClass()); 
              System.out.println("type = " + fld.getType()); 
              int mod = fld.getModifiers(); 
              System.out.println("modifiers = " + Modifier.toString(mod)); 
              System.out.println("-----"); 
           } 
      } 
      catch (Throwable e) { 
           System.err.println(e); 
      } 
   } 
}  