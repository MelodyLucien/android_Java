package interrupt;
import java.util.*;
public class Machine extends Thread{
  private int a=0;
  private Timer timer=new Timer(true);
  
  public synchronized void reset(){a=0;}

  public void run(){
    while(true){
      synchronized(this){
         while(a>3){
          final Thread thread=Thread.currentThread();
          timer.schedule(new TimerTask(){  //定义一个继承TimerTask的匿名类
              public void run(){
                System.out.println(thread.getName()+" has waited for 3 seconds");
                thread.interrupt();  //中断阻塞
              }
          },3000);
          
        try{
            this.wait();  //如果等待时间超过3秒，会收到InterruptedException
           }catch(InterruptedException e){
             System.out.println(thread.getName()+ " is interrupted");
             return;
           }
         }

         a++;
         System.out.println("a="+a);
      }
    }
  }

  public static void main(String args[])throws Exception{
    Machine machine=new Machine();
    machine.start();
   }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


