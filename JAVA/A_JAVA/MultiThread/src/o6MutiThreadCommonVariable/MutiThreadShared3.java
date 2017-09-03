package o6MutiThreadCommonVariable;

public class MutiThreadShared3 {
	
	//private static SharedData2 data2=new SharedData2();

	public static void main(String[] args) {
		
		final SharedData3 data = new SharedData3();
		
		new Thread(new MyRunnable(data)).start();
		new Thread(new MyRunnable2(data)).start();

	}

}

class SharedData3  {

	private int count = 100;

	public synchronized void dcrement() {
		System.out.println(--count);
	}
	
	public synchronized void increment() {
		System.out.println(++count);
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}

class MyRunnable implements Runnable{
	private SharedData3 data3;
	
	public MyRunnable(SharedData3 data3) {
		this.data3=data3;
	}

	@Override
	public void run() {
		data3.dcrement();
	}
	
}

class MyRunnable2 implements Runnable{
	private SharedData3 data3;
	
	public MyRunnable2(SharedData3 data3) {
		this.data3=data3;
	}

	@Override
	public void run() {
		data3.increment();
	}
	
}
