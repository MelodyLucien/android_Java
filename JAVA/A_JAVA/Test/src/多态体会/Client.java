package 多态体会;

/**
 * 一个简单的例子  体会多态
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
