package com.test.app;

import java.lang.reflect.*;  
import java.util.Arrays;
/*
 * 动态的改变数组中的值
*/
public class UseArray { 
   public static void main(String args[]) { 
      try { 
           Class cls = Class.forName("java.lang.String"); 
           Object arr = Array.newInstance(cls, 10); 
 	      
           Array.set(arr, 5, "this is a test"); 
           String s = (String) Array.get(arr, 5); 
           System.out.println(s); 
           
          for (int i = 0; i < 10; i++) {
        	  System.out.println("llll");
			System.out.println(Array.get(arr, i));
		}
          
         
      } 
      catch (Throwable e) { 
           System.err.println(e); 
      } 
   } 
} 