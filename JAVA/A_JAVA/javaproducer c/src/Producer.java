
public class Producer implements Runnable{
	P p;
	public Producer(P p) {
		this.p=p;
	}

	@Override
	public void run() {
		int i=0;
		while(true){
			if (i%2==0) {
				p.set("����","��");
			}
			else {
				p.set("����","Ů");
			}
			i=i+1;
		}
		
	}
	

}
