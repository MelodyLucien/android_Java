package o18BilockQueuenTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;



/**
 * 可以解决多个线程共享问题，可以与传统的两个线程的同步相比较，
 * 注  传统方法进行同步只能在两个线程之间，如果为多个线程会比较复杂
 * 相当于在阻塞 队列中等待，之后一个一个取出执行，因此解决问题
 * @author zhouhao
 *
 */
public class BlockedQueuenTest {

	public static void main(String[] args) {
		
        final Business business=new Business();  //内部类只能访问final变量
		for (int i = 0; i < 2; i++) {
			 new Thread(new Runnable() {               //第一个启动的线程

					@Override
					public void run() {
						
							for (int i = 0; i < 5; i++) {
								business.print1(i);
						}
					}
				}).start();
		}
       
	
	for (int i = 0; i < 3; i++) {	
		new Thread(new Runnable() {               //第二个启动的线程

			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
				   business.print2(i);
				}
			}
		}).start();
	}
	}
}

class Business{
	
	private BlockingQueue<Integer> blockingQueue1=new ArrayBlockingQueue<>(1);
	private BlockingQueue<Integer> blockingQueue2=new ArrayBlockingQueue<>(1);
	
	//匿名构造方法
	/************************************/
	{ 
		try {
			blockingQueue2.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	/*********************************/
	
	public void print1(int i){  //同步监听器为this对象
		
		try {
			blockingQueue1.put(1);            //队列1装满，其他进入队列的只能进入阻塞队列
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		
		for (int j = 0; j < 3; j++) {
			System.out.println(Thread.currentThread()
					.getName()
					+ "--"
					+ j
					+ "---loop of"
					+ i);
		}
		
		
		try {
			blockingQueue2.take();             //可以从阻塞队列2中取出一个数据
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public  void print2(int i){
		try {
			blockingQueue2.put(1);                 //与1模式相同
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int j = 0; j < 3; j++) {
			System.out.println(Thread.currentThread()
					.getName()
					+ "--"
					+ j
					+ "---loop of"
					+ i);
		}
		
		try {
			blockingQueue1.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	
}
