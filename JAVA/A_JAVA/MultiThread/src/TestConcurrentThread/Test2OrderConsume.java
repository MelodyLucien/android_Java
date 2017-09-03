package TestConcurrentThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * 第二题：现成程序中的Test类中的代码在不断地产生数据，然后交给TestDo.doSome()方法去处理，
 * 就好像生产者在不断地产生数据，消费者在不断消费数据。请将程序改造成有10个线程来消费生成者产生的数据，
 * 这些消费者都调用TestDo.doSome()方法去进行处理，故每个消费者都需要一秒才能处理完，
 * 程序应保证这些消费者线程依次有序地消费数据，只有上一个消费者消费完后，下一个消费者才能消费数据，
 * 下一个消费者是谁都可以，但要保证这些消费者线程拿到的数据是有顺序的。原始代码如下：
 * @author zhouhao
 */
public class Test2OrderConsume {
	
	public static void main(String[] args) {
		//解题思路，使用一个队列来解决问题，队列中存储对象为一个线程，按顺序取出线程，
		//每个线程在执行完之后，唤醒下一个即可
		
		
		final BlockingQueue<Thread> queue=new ArrayBlockingQueue<>(10);
		
		System.out.println("begin:"+(System.currentTimeMillis()/1000));
		for(int i=0;i<10;i++){  //这行不能改动
			String input = i+"";  //这行不能改动
			final String tempStr=input;
			queue.add(new Thread(new Runnable() {
				@Override
				public void run() {
					String output = TestDo.doSome(tempStr);
					System.out.println(Thread.currentThread().getName()+ ":" + output);
					Thread thread=queue.poll();
					if(thread!=null){
						thread.start();
					}
				}
			}));
		}
		
		queue.poll().start();
	
	   //解法缺点：没有实现线程随机访问，可改进成利用信号量完成设计
		
	}
}

//不能改动此TestDo类
class TestDo {
	public static String doSome(String input){
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String output = input + ":"+ (System.currentTimeMillis() / 1000);
		return output;
	}
}
