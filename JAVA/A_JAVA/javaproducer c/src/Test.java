
public class Test {
	public static void main(String[] args) {
		P p=new P();
		Producer p1=new Producer(p);
		Consumer p2=new Consumer(p);
		Thread t1=new Thread(p1);
		Thread t2=new Thread(p2);
		t1.start();
		t2.start();
	}

}
