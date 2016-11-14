package com.zhouhao2.debug;

public class MyDebuger {

	public static String getST() {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		StringBuffer bf = new StringBuffer();
		for (int i = 2; i < stacks.length; i++) {
			bf.append(stacks[i].toString() + "\n");
		}
		return bf.toString();
	}

	public static int getLineNumber() {
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}

	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName()
				.toString();
	}

	public static String getClassName() {
		return Thread.currentThread().getStackTrace()[2].getClassName()
				.toString();
	}
}
