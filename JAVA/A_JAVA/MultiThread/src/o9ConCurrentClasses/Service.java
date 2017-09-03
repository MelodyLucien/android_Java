package o9ConCurrentClasses;

import java.util.concurrent.*;

/**
 * �̳߳ؼ�����һ�����񽻸�һ���̳߳��е��߳����������
 * @author zhouhao
 */
public class Service {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
       //ExecutorService threadPool=Executors.newFixedThreadPool(3);
       //ExecutorService threadPool=Executors.newCachedThreadPool();
		
		/**
		 * �൱�ڴ���ִ��
		 */
		ExecutorService threadPool=Executors.newSingleThreadExecutor();
		
       for (int j = 0; j < 10; j++) {
    	  final int task=j;
        threadPool.execute(new Runnable() {
		@Override
		public void run() {
			for (int i = 0; i <10; i++) {
				System.out.println(Thread.currentThread().getName()+"   is processing the loop "+i+"  of   "+task);
			}
			System.out.println("over a task of  "+task);
		}
	});
       }
      
       
       /**
        * ʹ�ö�ʱ�߳�ʵ������
        */
       ScheduledExecutorService executorService= Executors.newScheduledThreadPool(5);
       executorService.schedule(new Runnable() {
    	   
		@Override
		public void run() {
			
			for (int i = 0; i < 100; i++) {
				System.out.println(Thread.currentThread().getName()+"  "+i+"   booming");
			}
			
		}
	}, 1, TimeUnit.SECONDS);
       
       threadPool.shutdown();
       executorService.shutdown();
	}

}
