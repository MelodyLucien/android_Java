package ��̬���;

/**
 * һ���򵥵�����  ����̬
 * @author zhouhao
 *
 */
public class Client {
	public static void main(String[] args) {
		Father father=new Son();
		father.getName();
		System.out.println(father.j);
		System.out.println(father.i);
		System.out.println(((Son)father).i);
		
		
	}

}
