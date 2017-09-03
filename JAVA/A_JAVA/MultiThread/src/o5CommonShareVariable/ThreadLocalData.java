package o5CommonShareVariable;

import java.util.HashMap;
import java.util.Random;

/**
 * 解决方案之一，用ThreadLocal来进行存储
 * @author zhouhao
 *
 */
public class ThreadLocalData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
    for (int i = 0; i < 2; i++) {
    	
		new Thread(new Runnable() {

			@Override
			public void run() {
				
					int data=new Random().nextInt();
					System.out.println(Thread.currentThread().getName()+"put data ="+data);
					MyThreadData threadData = MyThreadData.getThreadData();
					threadData.setAge(data);
					threadData.setName("zh"+data);
					System.out.println(Thread.currentThread().getName()+threadData);
					threadData.setAge(data+1);
					threadData.setName("zh"+(data+1));
					System.out.println("after alert--"+Thread.currentThread().getName()+threadData);
			}
		}).start();
	  }
	}
	


}

class MyThreadData{
	
	private int age;
	
	private String name;
	
	private static ThreadLocal<MyThreadData> threadLocal=new ThreadLocal<>();
	
	private MyThreadData() {
		
	}
	
	/**
	 * the threadData object is not the same  is different with the old singleton
	 * @return
	 */
	public static MyThreadData getThreadData(){
		
		MyThreadData threadData=threadLocal.get();
		
		if(threadData==null){
			threadData= new MyThreadData();
			threadLocal.set(threadData);
			return threadData;
		}
		return threadData;
	}

	
	public int getAge() {
		return age;
	}
	
	
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MyThreadData [age=" + age + ", name=" + name + "]";
	}
	
	
	
	
	
}
