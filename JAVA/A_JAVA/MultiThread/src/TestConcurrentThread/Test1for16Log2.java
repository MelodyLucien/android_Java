package TestConcurrentThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用阻塞队列来解决问题:首先将产生的数据缓存
 * 再将缓存的数据用四个线程循环读出即可
 * @author zhouhao
 * 
 */
public class Test1for16Log2 {

	public static void main(String[] args) {

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		/*
		 * 模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
		 * 修改程序代码，开四个线程让这16个对象在4秒钟打完。
		 */

		// 初始化一个阻塞队列，用于缓存产生的log
		final BlockingQueue<String> queue = new ArrayBlockingQueue<>(16);

		// 同时开启四个线程，从阻塞队列中取出缓存的数据，一个一个进行处理
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							parseLog(queue.take()); // 使用take会在队列为空的时候阻塞
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		
		for (int i = 0; i < 16; i++) { // 这行代码不能改动
			final String log = "" + (i + 1);// 这行代码不能改动
			{
				try {
					queue.put(log);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// parseLog方法内部的代码不能改动
	public static void parseLog(String log) {
		System.out.println(log + ":" + (System.currentTimeMillis() / 1000));

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}