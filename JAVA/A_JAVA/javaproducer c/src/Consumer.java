
public class Consumer implements Runnable {
	String name;
	P p;
	public Consumer(P p) {
		this.p=p;
	}
	@Override
	public void run() {
		name=Thread.currentThread().getName();
		while(true){
			System.out.println(name+p.get());
		}
		
	}
	
	

}
