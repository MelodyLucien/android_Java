package o11LockTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	
	static OutPuter outPuter = new OutPuter();

	public static void main(String[] args) {

		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outPuter.print("zhouhaonihao");
				}
			}

		}.start();

		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					outPuter.print("lili");
				}

			}
		}.start();
	}

	public static class OutPuter {

		Lock lock = new ReentrantLock();

		public OutPuter() {

		}

		public void print(String string) {
			print1(string);
		}

		public void print1(String string) {

			lock.lock();
			try {
				int length = string.length();
				for (int i = 0; i < length; i++) {
					System.out.print(string.charAt(i));
				}
				System.out.println();

			} finally {
				lock.unlock();
			}
		}

	}

}
