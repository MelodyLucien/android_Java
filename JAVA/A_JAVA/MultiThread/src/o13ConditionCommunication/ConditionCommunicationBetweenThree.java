package o13ConditionCommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 设计实现三个线程之间轮流工作
 * @author zhouhao
 *
 */
public class ConditionCommunicationBetweenThree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Bussiness business = new Bussiness();
	
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < 50; i++) {
	                    business.sub1(10, "sub1");
					}

				}
			}).start();
		
		
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					  business.sub2(20, "sub2");
				}

			}
		}).start();
	
	
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					  business.sub3(30, "sub3");
				}

			}
		}).start();
		}

	}



class Bussiness {

	private boolean sub12 = true;                         
	private boolean sub23 = false;
	private boolean sub31 = false;
	private ReentrantLock lock=new ReentrantLock();
	private Condition condition12=lock.newCondition();  //三个condition防止一个队列的排序混乱
	private Condition condition23=lock.newCondition();	
	private Condition condition31=lock.newCondition();
	
	public Bussiness() {
		// TODO Auto-generated constructor stub
	}

	public void sub1(final int times, final String say) {
		lock.lock();
				try{
					while(!sub12){
						try {
							condition12.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				     }
					for (int i = 0; i < times; i++) {
						say(say, i);
					}
					sub12=false;
					sub23=true;
					condition23.signal();
				}finally{
			     lock.unlock();
		       }
		
	}

	public void sub2(final int times, final String say) {
		lock.lock();
		 try{
		while(!sub23){
			try {
				condition23.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < times; i++) {
			say(say, i);
		}
		sub23=false;
		sub31=true;
		condition31.signal();
		 }finally{
		lock.unlock();
		 }
	}

	public void sub3(final int times, final String say) {
		lock.lock();
		try{
		while(!sub31){
			try {
				condition31.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < times; i++) {
			say(say, i);
		}
		sub12=true;
		sub31=false;
		condition12.signal();
		}finally{
		lock.unlock();
		}
	}

	private void say(String say, int i) {
		System.out.println(Thread.currentThread().getName() + "say  " + say
				+ "  " + i);
	}
}
