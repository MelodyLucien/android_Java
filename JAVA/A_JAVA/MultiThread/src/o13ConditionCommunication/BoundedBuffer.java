package o13ConditionCommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用condition实现一个阻塞队列，
 * 即从队列中取出数据，如果没有数据，则进入取数据阻塞队列
 * 向队列中放入数据，如果已经放满，则进入发数据阻塞队列，
 * 完成更为复杂的多线程并发问题
 * 这个class  实现了一个有界的缓冲区
 * @author zhouhao
 */

public class BoundedBuffer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	   final Lock lock = new ReentrantLock();
	   final Condition notFull  = lock.newCondition(); 
	   final Condition notEmpty = lock.newCondition(); 

	   final Object[] items = new Object[100];
	   int putptr, takeptr, count;

	   public void put(Object x) throws InterruptedException {
	     lock.lock();
	     try {
	       while (count == items.length)           //如果数据已经满了，进入等待不满的队列
	         notFull.await();
	       items[putptr] = x;
	       if (++putptr == items.length) putptr = 0;
	       ++count;
	         notEmpty.signal();
	     } finally {
	       lock.unlock();
	     }
	   }

	   public Object take() throws InterruptedException {
	     lock.lock();
	     try {
	       while (count == 0)
	         notEmpty.await();
	       Object x = items[takeptr];
	       if (++takeptr == items.length) takeptr = 0;
	       --count;
	       notFull.signal();
	       return x;
	     } finally {
	       lock.unlock();
	     }
	   }


}
