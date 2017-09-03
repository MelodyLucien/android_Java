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

			// ��ȡ��������еĹ��췽��
			Constructor ctorlist[] = cls.getDeclaredConstructors();
			for (int i = 0; i < ctorlist.length; i++) {
				Constructor ct = ctorlist[i];
				System.out.println("name = " + ct.getName());

				// ��ȡ���еĲ������ͣ�ѭ�����
				Class pvec[] = ct.getParameterTypes();
				for (int j = 0; j < pvec.length; j++)
					System.out.println("param #" + j + " " + pvec[j]);

				// ��ȡ���е��׳����쳣�����ͣ�ѭ�����
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