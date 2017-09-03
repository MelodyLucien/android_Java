package dead;

public class Dath2 {
	public void dath2(Dath d) {
		System.out.println("i am Dath2");
		d2way();
		d.dway();
	}
	
	synchronized void d2way(){
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			
		}
		System.out.println("d2way is running");
	}

}
