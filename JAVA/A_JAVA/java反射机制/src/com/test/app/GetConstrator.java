package com.test.app;

import java.lang.reflect.Constructor;

public class GetConstrator {
	public GetConstrator() {
	}

	protected GetConstrator(int i, double d) {
	}

	public static void main(String args[]) {
		try {
			Class cls = Class.forName("com.test.app.GetConstrator");

			// 获取该类的所有的构造方法
			Constructor ctorlist[] = cls.getDeclaredConstructors();
			for (int i = 0; i < ctorlist.length; i++) {
				Constructor ct = ctorlist[i];
				System.out.println("name = " + ct.getName());

				// 获取所有的参数类型，循环输出
				Class pvec[] = ct.getParameterTypes();
				for (int j = 0; j < pvec.length; j++)
					System.out.println("param #" + j + " " + pvec[j]);

				// 获取所有的抛出的异常的类型，循环输出
				Class evec[] = ct.getExceptionTypes();
				for (int j = 0; j < evec.length; j++)
					System.out.println("exc #" + j + " " + evec[j]);
				System.out.println("-----");
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}