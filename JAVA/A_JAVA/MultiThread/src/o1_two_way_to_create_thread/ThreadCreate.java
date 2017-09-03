package o1_two_way_to_create_thread;

public class ThreadCreate {
	public static void main(String[] args) {
		
		//1----------------------------------------------------
		Thread thread=new Thread(){
			@Override
			public void run() {
				System.out.println("I am the first way!");
			}
		};
			thread.start();
		
			
	    //2------------------------------------------------------
		Runnable runnable=new Runnable() {
			
			@Override
			public void run() {
				System.out.println("I am the second way!");
				
			}
		};
		
		Thread thread1 =new Thread(runnable);
		thread1.start();
		
		
		
		//question which run() to run----------------------------------
		Thread thread2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				
			}
			
		}){
			public void run() {
				
			}
		};
		
		thread2.start();
		//answer the run() of thread2,because of succession
	}
	
	

}
