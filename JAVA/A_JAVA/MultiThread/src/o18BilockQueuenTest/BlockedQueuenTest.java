package o18BilockQueuenTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;



/**
 * ���Խ������̹߳������⣬�����봫ͳ�������̵߳�ͬ����Ƚϣ�
 * ע  ��ͳ��������ͬ��ֻ���������߳�֮�䣬���Ϊ����̻߳�Ƚϸ���
 * �൱�������� �����еȴ���֮��һ��һ��ȡ��ִ�У���˽������
 * @author zhouhao
 *
 */
public class BlockedQueuenTest {

	public static void main(String[] args) {
		
        final Business business=new Business();  //�ڲ���ֻ�ܷ���final����
		for (int i = 0; i < 2; i++) {
			 new Thread(new Runnable() {               //��һ���������߳�

					@Override
					public void run() {
						
							for (int i = 0; i < 5; i++) {
								business.print1(i);
						}
					}
				}).start();
		}
       
	
	for (int i = 0; i < 3; i++) {	
		new Thread(new Runnable() {               //�ڶ����������߳�

			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
				   business.print2(i);
				}
			}
		}).start();
	}
	}
}

class Business{
	
	private BlockingQueue<Integer> blockingQueue1=new ArrayBlockingQueue<>(1);
	private BlockingQueue<Integer> blockingQueue2=new ArrayBlockingQueue<>(1);
	
	//�������췽��
	/************************************/
	{ 
		try {
			blockingQueue2.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	/*********************************/
	
	public void print1(int i){  //ͬ��������Ϊthis����
		
		try {
			blockingQueue1.put(1);            //����1װ��������������е�ֻ�ܽ�����������
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		
		for (int j = 0; j < 3; j++) {
			System.out.println(Thread.currentThread()
					.getName()
					+ "--"
					+ j
					+ "---loop of"
					+ i);
		}
		
		
		try {
			blockingQueue2.take();             //���Դ���������2��ȡ��һ������
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public  void print2(int i){
		try {
			blockingQueue2.put(1);                 //��1ģʽ��ͬ
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int j = 0; j < 3; j++) {
			System.out.println(Thread.currentThread()
					.getName()
					+ "--"
					+ j
					+ "---loop of"
					+ i);
		}
		
		try {
			blockingQueue1.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	
}
