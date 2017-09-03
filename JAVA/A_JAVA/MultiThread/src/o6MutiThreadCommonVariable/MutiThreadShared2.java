package o6MutiThreadCommonVariable;

/**
 * Ӧ�Զ�����������Ҳ�����Ψһ��Ҫ�ö��ֲ���
 * @author zhouhao
 *
 */
public class MutiThreadShared2 {
	
	//private static SharedData2 data2=new SharedData2();

	public static void main(String[] args) {
		
		final SharedData2 data = new SharedData2();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				data.increment();
			}
			
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				data.dcrement();
			}
		}).start();

	}

}

class SharedData2  {

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
