package SingletonMethod;
//最简单的单例模型

public class Deserialible {
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
	
	//other......
    public static void main(String[] args) {
	   
    }
}
//问题，无法实现在只有第一次使用时才去实例化，因为对于static 变量的属性  ，在加载类时就会创建
//注意，Jvm在加载此类时，对于static属性的初始化只能有一个线程执行且仅有一次,所以本代码是线程安全的

