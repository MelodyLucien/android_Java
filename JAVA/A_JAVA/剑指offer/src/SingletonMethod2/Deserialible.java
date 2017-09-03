package SingletonMethod2;

import java.io.Serializable;

public class Deserialible implements Serializable{
	//也可以设计为public的，暴漏给客户端对象,在类的加载时就会被执行
		private static Deserialible instance=new Deserialible();
		
		//other field.......
		
		
		//声明为私有的构造方法，客户端无法实现该类的实例
		private Deserialible() {
			System.out.println("我是最简单那的单例模式");
		}
		
		//全局访问点
		public static Deserialible getInstance()
		{
			return instance;
			
		}
	  
		//**********************************
		//在反序列化时返回为同一个内存中的实例
		private Object readResolve()
		{
			return instance;
		}
		//**********************************
		//other......
		
		
		
	    public static void main(String[] args) {
		   
	    }

}
