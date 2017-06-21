package com.zhouhao2.annotation;

public @interface Item {
	int id();
	String synopsis();
	String engineer() default "unassigned ";
	String date() default "unknown";
}
