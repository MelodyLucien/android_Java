package TestNotify;



/**
 * notify()之后的内容要进行完才行
 * @author zhouhao
 *
 */
public class TestNotify {
	
	public static void main(String[] args) {
            new Thread(new Test1()).start();
            
            try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
            
            new Thread(new Test2()).start();
		
	}

}

class Test1 implements Runnable{

	@Override
	public void run() {
		
		synchronized (TestNotify.class) {
			System.out.println("enter one");
			System.out.println("one is wating");

			
			try {
				TestNotify.class.wait();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			System.out.println("one is going on");
			System.out.println("one  is over");
		}
	


	}
	
	
	
}


class Test2 implements Runnable{

	@Override
	public void run() {
		
		synchronized (TestNotify.class) {
		System.out.println("enter two");
		System.out.println("two is notifing");

        TestNotify.class.notify();		
		
		
		System.out.println("two is going on");
		System.out.println("two  is over");
		}

	}
	
	
	
}