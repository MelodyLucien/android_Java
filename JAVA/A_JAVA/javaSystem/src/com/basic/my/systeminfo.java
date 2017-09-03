package com.basic.my;
import java.io.ObjectInputStream.GetField;
import java.util.*;


public class systeminfo{
	public static void main(String[] args) {
		Properties p=System.getProperties();
		Enumeration e = p.propertyNames();
		while(e.hasMoreElements())
		{
			String key=(String) e.nextElement();
			System.out.println(key+":"+" "+p.getProperty(key));
			
		}
	}

}
