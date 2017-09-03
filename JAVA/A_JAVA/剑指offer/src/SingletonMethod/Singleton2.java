package SingletonMethod;


//如何延迟创建实例，是指在第一次使用时在创建呢即延迟创建
public class Singleton2 {
	
	//也可以设计为public的，暴漏给客户端对象,在类的加载时就会被执行
		private static Singleton2 instance=null;
		
		//other field.......
		
		//声明为私有的构造方法，客户端无法实现该类的实例
		private Singleton2() {
			System.out.println("我是最简单那的单例模式");
		}
		
		//全局访问点
		public static Singleton2 getInstance()
		{
			if(instance==null)
			{
				instance=new Singleton2();
			}
			return instance;
			
		}
		
		//other......
	    public static void main(String[] args) {
		  
	   }
	
	
//注：此方法线程不安全，在instance的判断上有多线程的并发时的缺点
}
