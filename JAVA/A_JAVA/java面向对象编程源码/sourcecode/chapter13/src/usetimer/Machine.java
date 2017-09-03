package usetimer;
import java.util.Timer;
import java.util.TimerTask;
public class Machine extends Thread{
  private int a;
  private static int count;

  public void start(){
    super.start();
    Timer timer=new Timer(true);  //把与Timer关联的线程设为后台线程
 
  TimerTask task=new TimerTask(){  //匿名类
      public void run(){
        while(true){  //无限循环
          reset();
          try{
            sleep(1000);
            }catch(InterruptedException e){/*throw new RuntimeException(e);*/}
        }
      }
    };
  
  timer.schedule(task,10,500);  //设置定时任务
  }
  public void reset(){a=0;}
  public void run(){
    while(true){
      System.out.println(getName()+":"+a++);
      if(count++==100)break;
      yield();
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

 


