package com.example.test;

public final class WindowState {
	
	public static void main(String[] args) {
		
	WindowState windowState = new WindowState();
	
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
				synchronized (windowState) {
					System.out.println("before wait");
					windowState.wait();
					System.out.println("ok");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}).start();
		
		synchronized(windowState){
			try {

				Thread.sleep(4000);
				
				System.out.println("notify");
				windowState.notify();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("state");
		}
		
		synchronized(windowState){
			try {

				Thread.sleep(4000);
				
				System.out.println("notify");
				windowState.notify();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("state");
		}
		
	}
}
