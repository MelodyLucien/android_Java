package TestConcurrentThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ʹ�������������������:���Ƚ����������ݻ���
 * �ٽ�������������ĸ��߳�ѭ����������
 * @author zhouhao
 * 
 */
public class Test1for16Log2 {

	public static void main(String[] args) {

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		/*
		 * ģ�⴦��16����־������Ĵ��������16����־���󣬵�ǰ������Ҫ����16����ܴ�ӡ����Щ��־��
		 * �޸ĳ�����룬���ĸ��߳�����16��������4���Ӵ��ꡣ
		 */

		// ��ʼ��һ���������У����ڻ��������log
		final BlockingQueue<String> queue = new ArrayBlockingQueue<>(16);

		// ͬʱ�����ĸ��̣߳�������������ȡ����������ݣ�һ��һ�����д���
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							parseLog(queue.take()); // ʹ��take���ڶ���Ϊ�յ�ʱ������
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		
		for (int i = 0; i < 16; i++) { // ���д��벻�ܸĶ�
			final String log = "" + (i + 1);// ���д��벻�ܸĶ�
			{
				try {
					queue.put(log);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// parseLog�����ڲ��Ĵ��벻�ܸĶ�
	public static void parseLog(String log) {
		System.out.println(log + ":" + (System.currentTimeMillis() / 1000));

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}