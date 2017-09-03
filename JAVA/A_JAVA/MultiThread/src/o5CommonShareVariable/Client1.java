package o5CommonShareVariable;

import java.util.HashMap;
import java.util.Random;

/**
 * 解决方案之一，用HasHmap来进行存储
 * @author zhouhao
 *
 */
public class Client1 {
	
	private static int data=-1;
	private static HashMap<Thread,Integer> threadData =new HashMap<>();

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
					threadData.put(Thread.currentThread(), data);
					new A().print();
					
				
				
			}
		}).start();
	  }
	}
	
	static class A{
		public void print(){
			System.out.println(Thread.currentThread().getName()+"A from Client  data ="+threadData.get(Thread.currentThread()));
		}
	}

}
