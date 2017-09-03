package TestConcurrentThread;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 现有程序同时启动了4个线程去调用TestDo.doSome(key, value)方法， 由于TestDo.doSome(key,
 * value)方法内的代码是先暂停1秒，然后再输出以秒为单位的当前时间值， 所以，会打印出4个相同的时间值，如下所示： 
 * 4:4:1258199615
 * 1:1:1258199615 
 * 3:3:1258199615 
 * 1:2:1258199615
 * 请修改代码，如果有几个线程调用TestDo.doSome(key, value)方法时，
 * 传递进去的key相等（equals比较为true），则这几个线程应互斥排队输出结果，
 * 即当有两个线程的key都是"1"时，它们中的一个要比另外其他线程晚1秒输出结果，如下所示： 
 * 4:4:1258199615 
 * 1:1:1258199615
 * 3:3:1258199615 
 * 1:2:1258199616
 * 总之，当每个线程中指定的key相等时，这些相等key的线程应每隔一秒依次输出时间值（要用互斥）， 如果key不同，则并行执行（相互之间不互斥）。
 * 
 * @author zhouhao
 * 
 */
// 不能改动此Test类
public class Test3OrderForSameValue2 extends Thread {

	private TestDoo2 testDo;
	private String key;
	private String value;

	public Test3OrderForSameValue2(String key, String key2, String value) {
		this.testDo = TestDoo2.getInstance();
		/*
		 * 常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
		 * 以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果
		 */
		this.key = key + key2;
		this.value = value;
	}

	public static void main(String[] args) throws InterruptedException {
		/*
		 * 解题思路：本题我使用了一个投机取巧的方法，使用string的intern()方法
		 */
		
		Test3OrderForSameValue2 a = new Test3OrderForSameValue2("1", "", "1");
		Test3OrderForSameValue2 b = new Test3OrderForSameValue2("1", "", "2");
		Test3OrderForSameValue2 c = new Test3OrderForSameValue2("3", "", "3");
		Test3OrderForSameValue2 d = new Test3OrderForSameValue2("4", "", "1");
		Test3OrderForSameValue2 e = new Test3OrderForSameValue2("4", "", "2");
		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		a.start();
		b.start();
		c.start();
		d.start();
		e.start();

	}

	public void run() {
		testDo.doSome(key, value);
	}
}

class TestDoo2 {
	
	//以上为单例模式经典代码，注意识别，保证使用的为同一个对象的代码
	private TestDoo2() {
	}

	private static TestDoo2 _instance = new TestDoo2();

	public static TestDoo2 getInstance() {
		return _instance;
	}
	
	
	//以下的解题思路：用同步的set类将key存起来
	//遇到相同的(equals()为true)key的时候只存一个就行，使用key作为同步的对象
	CopyOnWriteArraySet<Object> arraySet=new CopyOnWriteArraySet<>();
	
	public void doSome(Object key, String value) {
		
		
        Object object=key;
        if(!arraySet.contains(object)){
        	arraySet.add(object);
        }else{
        	for (Iterator iterator = arraySet.iterator(); iterator.hasNext();) {
				Object type = (Object) iterator.next();
				if(object.equals(type)){
				object=type;
				break;
				}
			}
        }
        
		synchronized (object) {
			
			// 以大括号内的是需要局部同步的代码，不能改动!
			{
				try {
					Thread.sleep(1000);
					System.out.println(key + ":" + value + ":"
							+ (System.currentTimeMillis() / 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
