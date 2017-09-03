package o14SemaphoreTest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class SemaphoreTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ExecutorService executorService=Executors.newCachedThreadPool();
		final Pool pool=new Pool();
		
		for (int i = 0; i <20; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					Object object=null;
					try {
						object=pool.getItem();
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"  ����   avaiable="+pool.getAviableNnumbers());
				    pool.putItem(object);
					System.out.println(Thread.currentThread().getName()+"  �Ѿ��뿪  avaiable="+pool.getAviableNnumbers());
				}
			});
			
		}
	}
}

class Pool {
	
	   private static final int MAX_AVAILABLE = 3;                             //���û�����Դ������Ϊ3
	   
	   private final Semaphore available = new Semaphore(MAX_AVAILABLE, true); //�����ź����ĸ���������fifo˳�������Դ
	   
	   public Pool() {
		   
		for (int i = 0; i < MAX_AVAILABLE; i++) {
			items[i]=new Object();
		}
		
	}

	   public Object getItem() throws InterruptedException {
	     available.acquire();                                     //���û�о�����������
	     return getNextAvailableItem();                           //ԭ�Ӳ���
	   }

	   public void putItem(Object x) {
	     if (markAsUnused(x))
	       available.release();
	   }

	   // Not a particularly efficient data structure; just for demo

	   protected Object[] items = new Object[MAX_AVAILABLE];
	   protected boolean[] used = new boolean[MAX_AVAILABLE];

	   protected synchronized Object getNextAvailableItem() {
	     for (int i = 0; i < MAX_AVAILABLE; ++i) {
	       if (!used[i]) {
	          used[i] = true;
	          return items[i];
	       }
	     }
	     return null; // not reached
	   }

	   protected synchronized boolean markAsUnused(Object item) {
	     for (int i = 0; i < MAX_AVAILABLE; ++i) {
	       if (item == items[i]) {
	          if (used[i]) {
	            used[i] = false;
	            return true;
	          } else
	            return false;
	       }
	     }
	     return false;
	   }
	   
	   protected int getAviableNnumbers() {
		   return available.availablePermits();
		
	}
}

