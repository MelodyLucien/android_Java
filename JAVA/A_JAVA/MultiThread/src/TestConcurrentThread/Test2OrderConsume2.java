package TestConcurrentThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;


/**
 * 第二题：现成程序中的Test类中的代码在不断地产生数据，然后交给TestDo.doSome()方法去处理，
 * 就好像生产者在不断地产生数据，消费者在不断消费数据。请将程序改造成有10个线程来消费生成者产生的数据，
 * 这些消费者都调用TestDo.doSome()方法去进行处理，故每个消费者都需要一秒才能处理完，
 * 程序应保证这些消费者线程依次有序地消费数据，只有上一个消费者消费完后，下一个消费者才能消费数据，
 * 下一个消费者是谁都可以，但要保证这些消费者线程拿到的数据是有顺序的。原始代码如下：
 * @author zhouhao
 */
public class Test2OrderConsume2 {
	
	public static void main(String[] args) {
		//解题思路，使用一个队列和一个信号量
		
		
		//用于进行同时完成一次任务的控制
		final Semaphore semaphore=new Semaphore(1);
		
		//用于顺序处理产生的数据
		final BlockingQueue<String> queue=new ArrayBlockingQueue<>(10);
		
		//  同时    开启10个线程进行消费
		for (int i = 0; i < 10; i++) {
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
						String tempStr=queue.take();
						String output = TestDo.doSome(tempStr);
						System.out.println(Thread.currentThread().getName()+ ":" + output);
						semaphore.release();
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
			}).start();
			
		}
		
		System.out.println("begin:"+(System.currentTimeMillis()/1000));
		for(int i=0;i<10;i++){  //这行不能改动
			String input = i+"";  //这行不能改动
			final String tempStr=input;
			try {
				queue.put(tempStr);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	
	}
}


