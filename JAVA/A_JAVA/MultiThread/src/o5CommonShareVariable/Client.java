package o5CommonShareVariable;

import java.util.Random;

/**
 * 演示同一线程中的共享变量的值不一致的情况,原因：每个线程都有自己的一个副本，而且修改的时候也不是原子操作
 * @author zhouhao
 *
 */
public class Client {
	
	private static int data=-1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
    for (int i = 0; i < 2; i++) {
    	
		new Thread(new Runnable() {

			@Override
			public void run() {
					data=new Random().nextInt();
					System.out.println(Thread.currentThread().getName()+"put data ="+data);
					Client.print();
			}
			
		}).start();
	  }
	System.out.println(Thread.currentThread().getName()+"from Client  data ="+data);
	}
	
	
	public static void print(){
		System.out.println(Thread.currentThread().getName()+"from Client  data ="+data);
	}

}
