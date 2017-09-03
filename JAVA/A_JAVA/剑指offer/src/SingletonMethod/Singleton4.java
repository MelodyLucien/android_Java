package SingletonMethod;

//实现对于同步代码块的缩小

public class Singleton4 {
	
	//注意  voliate  表明具有synchronized的可见性，一旦有更新便可及时发现
	  private volatile static Singleton4 instance=null;			
			//other field.......
			
			//声明为私有的构造方法，客户端无法实现该类的实例
			private Singleton4() {
				System.out.println("我是最简单那的单例模式");
			}
			
			//全局访问点。注意双层检查的含义
			public static Singleton4 getInstance()
			{
				if(instance==null)
					
				  synchronized (Singleton4.class) {
					  
					if(instance==null)//double check if it is created
						instance=new Singleton4();
				}
				
				return instance;
				
			}
			
			//other......
		    public static void main(String[] args) {
			  
		   }

}
