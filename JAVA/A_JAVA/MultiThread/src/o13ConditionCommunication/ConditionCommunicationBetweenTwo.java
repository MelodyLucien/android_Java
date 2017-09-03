package o13ConditionCommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 将第4节的传统的线程间的通信改成利用condition和lock进行通信
 * @author zhouhao
 */

public class ConditionCommunicationBetweenTwo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
      final Business business=new Business();  //内部类只能访问final变量
		
        new Thread(new Runnable() {

			@Override
			public void run() {
				
					for (int i = 0; i < 50; i++) {
						business.print1(i);
				}
			}
		}).start();

		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
				   business.print2(i);
				}
			}
		}).start();

	}
	
	

}


class Business{
	
	private boolean isPrint1=true;
	private ReentrantLock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();

	public  void print1(int i){  //同步监听器为this对象
		lock.lock();
		try{
		while(!isPrint1){   //or if  此处用while是会再次检查变量的值是否改变，防止虚假唤醒
			try {
				
				condition.await();                 //使用this对象的同步队列
				                             //唤醒之后会从此处继续进行
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (int j = 0; j < 10; j++) {
			System.out.println(Thread.currentThread()
					.getName()
					+ "--"
					+ j
					+ "---loop of"
					+ i);
		}
		
		isPrint1=false;
		condition.signal();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	
	public  void print2(int i){
		lock.lock();
		try{
		while(isPrint1){  // or if
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (int j = 0; j < 100; j++) {
			System.out.println(Thread.currentThread().getName()
					+ "--" + j + "---loop of" + i);
		}
		
		isPrint1=true;
		condition.signal();
		}catch(Exception e){
		e.printStackTrace();
	}finally{
		lock.unlock();
	}
}
}