package �߳�֮����ڱ������޸�;

public class Threadd3 extends Thread{

	@Override
	public void run() {
		while(true)
		{
		Threadd.ShowPath=!Threadd.ShowPath;
		try {
			this.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
