package 线程之间对于变量的修改;

public class Threadd extends Thread{
	public static boolean ShowPath=false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		System.out.println(ShowPath);
		try {
			this.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}
	

}
