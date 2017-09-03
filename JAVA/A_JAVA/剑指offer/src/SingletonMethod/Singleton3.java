package SingletonMethod;


//线程安全的单例模式,这样再多的线程访问也只会有一个实例
public class Singleton3 {
	
	//也可以设计为public的，暴漏给客户端对象,在类的加载时就会被执行
	  private static Singleton3 instance=null;			
			//other field.......
			
			//声明为私有的构造方法，客户端无法实现该类的实例
			private Singleton3() {
				System.out.println("我是最简单那的单例模式");
			}
			
			//全局访问点
			public static synchronized Singleton3 getInstance()
			{
				if(instance==null)
				{
					instance=new Singleton3();
				}
				return instance;
				
			}
			
			//other......
		    public static void main(String[] args) {
			  
		   }
//问题：在多线程访问时，性能不好。（同步块的范围过大了，导致性能的下降）还能优化吗？答案：能		   
		
}
