package TestConcurrentThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;


/**
 * �ڶ��⣺�ֳɳ����е�Test���еĴ����ڲ��ϵز������ݣ�Ȼ�󽻸�TestDo.doSome()����ȥ����
 * �ͺ����������ڲ��ϵز������ݣ��������ڲ����������ݡ��뽫����������10���߳������������߲��������ݣ�
 * ��Щ�����߶�����TestDo.doSome()����ȥ���д�����ÿ�������߶���Ҫһ����ܴ����꣬
 * ����Ӧ��֤��Щ�������߳�����������������ݣ�ֻ����һ�����������������һ�������߲����������ݣ�
 * ��һ����������˭�����ԣ���Ҫ��֤��Щ�������߳��õ�����������˳��ġ�ԭʼ�������£�
 * @author zhouhao
 */
public class Test2OrderConsume2 {
	
	public static void main(String[] args) {
		//����˼·��ʹ��һ�����к�һ���ź���
		
		
		//���ڽ���ͬʱ���һ������Ŀ���
		final Semaphore semaphore=new Semaphore(1);
		
		//����˳�������������
		final BlockingQueue<String> queue=new ArrayBlockingQueue<>(10);
		
		//  ͬʱ    ����10���߳̽�������
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
		for(int i=0;i<10;i++){  //���в��ܸĶ�
			String input = i+"";  //���в��ܸĶ�
			final String tempStr=input;
			try {
				queue.put(tempStr);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	
	}
}


