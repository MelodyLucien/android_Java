
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
				p.set("张三","男");
			}
			else {
				p.set("李湘","女");
			}
			i=i+1;
		}
		
	}
	

}
