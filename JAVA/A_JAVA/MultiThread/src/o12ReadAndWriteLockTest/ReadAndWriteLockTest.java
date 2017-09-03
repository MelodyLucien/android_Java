package o12ReadAndWriteLockTest;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.crypto.Data;

/**
 * 利用读写锁解决读者写者问题
 * @author zhouhao
 *
 */
public class ReadAndWriteLockTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	     final LockTest lockTest=new LockTest();
	     
	      for (int i = 0; i < 3; i++) {  //启动三个读者
			new Thread(new Runnable() {

				@Override
				public void run() {
					lockTest.read();

				}

			}).start();
		}
	      
	      for (int i = 0; i < 3; i++) {   //启动三个写者
				new Thread(new Runnable() {

					@Override
					public void run() {
						lockTest.write();

					}

				}).start();
			}
		
	}
	
	
}

class LockTest{
	
	final ReadWriteLock rwl=new ReentrantReadWriteLock();  //读写锁
	Lock r=rwl.readLock();           //获取读锁
	Lock w=rwl.writeLock();          //获取写锁

	public LockTest() {
		
	}
	
	public void read() {
		r.lock();
		try {
			System.out.println("I am reading");
			Thread.sleep(500);
			System.out.println("I have read ");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			r.unlock();
		}

	}
	
	public void write(){
		
		w.lock();
		try {
			System.out.println("I am writing");
			Thread.sleep(500);
			System.out.println("I have wrote ");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			w.unlock();
		}
	}
	
	
}

/*
 * 通过读写锁的设置来实现一个缓存系统
 * class CachedData {
	   Object data;
	   volatile boolean cacheValid;
	   final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	   void processCachedData() {
	     rwl.readLock().lock();
	     if (!cacheValid) {
	        // Must release read lock before acquiring write lock
	        rwl.readLock().unlock();
	        rwl.writeLock().lock();
	        try {
	          // Recheck state because another thread might have
	          // acquired write lock and changed state before we did.
	          if (!cacheValid) {
	            data = ...
	            cacheValid = true;
	          }
	          // Downgrade by acquiring read lock before releasing write lock
	          rwl.readLock().lock();
	        } finally  {
	          rwl.writeLock().unlock(); // Unlock write, still hold read
	        }
	     }

	     try {
	       use(data);
	     } finally {
	       rwl.readLock().unlock();
	     }
	   }
	 }
	 */


/**
 * 用锁实现对于字典的查询
 * @author zhouhao
 *
 */
class RWDictionary {
	
    private final Map<Integer, String> m = new TreeMap<Integer, String>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();//一个读写锁
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public String get(int key) {
        r.lock();
        try { return m.get(key); }
        finally { r.unlock(); }
    }
    public String[] allKeys() {
        r.lock();
        try { return (String[]) m.keySet().toArray(); }
        finally { r.unlock(); }
    }
    public String put(int key, String value) {
        w.lock();
        try { return m.put(key, value); }
        finally { w.unlock(); }
    }
    public void clear() {
        w.lock();
        try { m.clear(); }
        finally { w.unlock(); }
    }
 }


