package o3synchonized_code_fragment;

public class Client {
	static OutPuter outPuter=new OutPuter();
	
	public static void main(String[] args) {
		
		new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outPuter.print("zhouhaonihao");
				}
			}
			
		}.start();
		
		
		
		new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					outPuter.print("lili");
				}
				
			}
		}.start();
	}
	
	public static class OutPuter {
		
		public OutPuter() {
			
		}
		
		public void print(String string) {
			print1(string);
			
		}

		public void print1(String string){
			
			synchronized (this) {
				int length=string.length();
				for (int i = 0; i < length; i++) {
					System.out.print(string.charAt(i));
				}
				System.out.println();
			}
			
		}

		//the locker is the same with the print1()
		public synchronized void print2(String string){
			int length=string.length();
			for (int i = 0; i < length; i++) {
				System.out.print(string.charAt(i));
			}
			System.out.println();
		}
		
		
		//the locker is the OutPuter.class so it is eaqual to print4()
		public static synchronized void print3(String string){
			int length=string.length();
			for (int i = 0; i < length; i++) {
				System.out.print(string.charAt(i));
			}
			System.out.println();
		}
		
		
		//the locker is eaqual to print3()
		public  void print4(String string){
			synchronized(OutPuter.class){
			int length=string.length();
			for (int i = 0; i < length; i++) {
				System.out.print(string.charAt(i));
			}
			System.out.println();
			}
		}
	}

}
