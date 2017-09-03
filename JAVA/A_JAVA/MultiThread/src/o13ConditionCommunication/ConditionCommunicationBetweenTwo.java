package o13ConditionCommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ����4�ڵĴ�ͳ���̼߳��ͨ�Ÿĳ�����condition��lock����ͨ��
 * @author zhouhao
 */

public class ConditionCommunicationBetweenTwo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
      final Business business=new Business();  //�ڲ���ֻ�ܷ���final����
		
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

	public  void print1(int i){  //ͬ��������Ϊthis����
		lock.lock();
		try{
		while(!isPrint1){   //or if  �˴���while�ǻ��ٴμ�������ֵ�Ƿ�ı䣬��ֹ��ٻ���
			try {
				
				condition.await();                 //ʹ��this�����ͬ������
				                             //����֮���Ӵ˴���������
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