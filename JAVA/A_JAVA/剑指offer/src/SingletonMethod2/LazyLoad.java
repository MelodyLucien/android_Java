package SingletonMethod2;

//使用静态内部类来,不会出现并发性的问题

public class LazyLoad {
	
	private LazyLoad() {
		
	}
	
	
   private static class Holder{
	   private static final LazyLoad LOAD=new LazyLoad();
   }
   
   
   //只有在调用getinstance方法时才会加载holder类，并且加载holder类时，首先会初始化他的static变量的
   public static LazyLoad getInstance(){
	   return Holder.LOAD;
   }
   
}
