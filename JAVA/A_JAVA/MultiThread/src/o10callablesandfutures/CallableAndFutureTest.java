package o10callablesandfutures;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFutureTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ExecutorService executorService=Executors.newFixedThreadPool(3);
		
		Future<String> future= executorService.submit(new Callable<String>() {
			 
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "hello";
			}
		});
		 
		
		 try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		 
		 executorService.shutdown();
		
		 
//___________________________to get the first completed		 
		ExecutorService threadPool2=Executors.newFixedThreadPool(3);
		CompletionService completionService=new ExecutorCompletionService(threadPool2);
		
		
		for (int i = 0; i < 10; i++) {
			final int task=i;
			
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return new Integer(task);
				}
			});
		}
		
		
		for (int i = 0; i <10; i++) {
			try {
				Future<Integer> future2=completionService.take();
				System.out.println(future2.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		threadPool2.shutdown();

	}

}
