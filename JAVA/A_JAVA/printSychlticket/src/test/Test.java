package test;

public class Test {
	
	public static void main(String[] args) {
		
		final Ticket ticket=new Ticket();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				 while(ticket.getNum()>0)
						ticket.produceTickets();
	
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
			         while(ticket.getNum()>0)
						ticket.produceTickets();
				
			}
		}).start();
	}
	
}


class Ticket{
	
	private int num=10000;
	
	public synchronized void produceTickets(){
		if(!(num<0))
		System.out.println("produce ticket"+num--+"-----by"+Thread.currentThread().getName());
		
	}
	
	public int getNum(){
		return num;
	}
	
	
}
