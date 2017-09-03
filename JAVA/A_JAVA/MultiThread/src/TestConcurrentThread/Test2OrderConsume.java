package TestConcurrentThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * �ڶ��⣺�ֳɳ����е�Test���еĴ����ڲ��ϵز������ݣ�Ȼ�󽻸�TestDo.doSome()����ȥ����
 * �ͺ����������ڲ��ϵز������ݣ��������ڲ����������ݡ��뽫����������10���߳������������߲��������ݣ�
 * ��Щ�����߶�����TestDo.doSome()����ȥ���д�����ÿ�������߶���Ҫһ����ܴ����꣬
 * ����Ӧ��֤��Щ�������߳�����������������ݣ�ֻ����һ�����������������һ�������߲����������ݣ�
 * ��һ����������˭�����ԣ���Ҫ��֤��Щ�������߳��õ�����������˳��ġ�ԭʼ�������£�
 * @author zhouhao
 */
public class Test2OrderConsume {
	
	public static void main(String[] args) {
		//����˼·��ʹ��һ��������������⣬�����д洢����Ϊһ���̣߳���˳��ȡ���̣߳�
		//ÿ���߳���ִ����֮�󣬻�����һ������
		
		
		final BlockingQueue<Thread> queue=new ArrayBlockingQueue<>(10);
		
		System.out.println("begin:"+(System.currentTimeMillis()/1000));
		for(int i=0;i<10;i++){  //���в��ܸĶ�
			String input = i+"";  //���в��ܸĶ�
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
	
	   //�ⷨȱ�㣺û��ʵ���߳�������ʣ��ɸĽ��������ź���������
		
	}
}

//���ܸĶ���TestDo��
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
