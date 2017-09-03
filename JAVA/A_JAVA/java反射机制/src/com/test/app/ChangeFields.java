package com.test.app;

import java.lang.reflect.*;


/**
 * @author zhouhao
 *
 */
public class ChangeFields {

	public double d = 10.02d;

	
	public ChangeFields() {
		System.out.println("construct");
	}

	public static void main(String args[]) {
		try {
			
			Class cls = Class.forName("com.test.app.ChangeFields");
			
			Field fld = cls.getField("d");
			ChangeFields f2obj = new ChangeFields();
			System.out.println("d = " + f2obj.d);
			
			fld.setDouble(f2obj, 12.34);
			System.out.println("d = " + f2obj.d);
			
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}
