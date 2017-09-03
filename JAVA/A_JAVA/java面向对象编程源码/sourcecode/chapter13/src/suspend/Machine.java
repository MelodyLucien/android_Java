package suspend;
public class Machine extends Thread{
  private int a;  //共享数据

  public void run(){
    for(int i=0;i<1000;i++){
      synchronized(this){
       a+=i;
       yield();  //给其他线程运行的机会
       a-=i;
     }
    }
  }

  public synchronized void reset(){ a=0;}

  public static void main(String args[]) throws InterruptedException{
    Machine machine=new Machine();
    machine.start();
    yield();  //给machine线程运行的机会
    machine.suspend();  //让machine线程暂停
    machine.reset();  //调用machine对象的同步代码块
    machine.resume();  //使machine线程恢复运行
  }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


