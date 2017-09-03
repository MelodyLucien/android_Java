package o6MutiThreadCommonVariable;
/**
 * Ӧ��һ���������Ҳ�����һ��
 * @author zhouhao
 *
 */
public class MutiThreadShared1 {

	public static void main(String[] args) {
		SharedData data = new SharedData();
		new Thread(data).start();
		new Thread(data).start();

	}

}

class SharedData implements Runnable {

	private int count = 100;

	public synchronized void dcrement() {
		System.out.println(--count);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void run() {
		synchronized (this) {
			while(count>0)
				dcrement();
		}
		
	}

}
