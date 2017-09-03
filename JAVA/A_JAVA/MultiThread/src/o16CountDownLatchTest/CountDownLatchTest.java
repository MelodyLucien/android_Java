package o16CountDownLatchTest;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhouhao
 *
 */
public class CountDownLatchTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);  
		final CountDownLatch cdAnswer = new CountDownLatch(3); 
		
		System.out.println(" in stage1:  "+Thread.currentThread().getName());	
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						System.out.println(Thread.currentThread().getName());						
						cdOrder.await();  
						
						
						System.out.println(Thread.currentThread().getName() );								
						Thread.sleep((long)(Math.random()*10000));
						System.out.println(Thread.currentThread().getName());						
						cdAnswer.countDown();					
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			};
			service.execute(runnable);
		}		
		
		try {
			Thread.sleep((long)(Math.random()*10000));					
			cdOrder.countDown();
			
			
			System.out.println(" in stage2:  "+Thread.currentThread().getName());	
			cdAnswer.await();   
		} catch (Exception e) {
			e.printStackTrace();
		}				
		service.shutdown();

	}
}
