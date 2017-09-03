package dead;

public class Dath {
	public void dath(Dath2 d2) {
		System.out.println("i am Dath ");
		dway();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		d2.d2way();
	}
	
	synchronized void dway(){
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("dway is runninng");
	}

}
