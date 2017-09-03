package o2TraditionalTimer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {
	
	public static void main(String[] args) {
		
		//-after 10second boom then boom per second
	   /*  Timer timer=new Timer();
	      timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
	             System.out.println("boom!");			
			}
		}, 10000,1000);
		
	   */
		
		
		//  1,timer in timer
		
		  Timer timer=new Timer();
	      timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("boom!");
				  Timer timer=new Timer();
			      timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
			            System.out.println("boom!!");
					}
				}, 4000);
			}
		}, 2000,6000);
	      
	      
        //  2.timer in timer
	      while(true){
	    	  try {
	    		 System.out.println(new Date().getSeconds());
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
	      }
	      
	      
	  
	      
		}
	}
	


