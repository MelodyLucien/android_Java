package control;
public class ControlledThread extends Thread {
  public static final int SUSP=1;
  public static final int STOP=2;
  public static final int RUN=0;

  private int state = RUN;

  public synchronized void setState( int state){
    this.state = state;
    if (state == RUN)
     notify();
  }

  public synchronized boolean checkState() {
    while(state == SUSP){
      try{
        System.out.println(Thread.currentThread().getName()+":wait");
        wait();
      }catch (InterruptedException e){throw new RuntimeException(e.getMessage()); }
    }
    if (state == STOP){
      return false;
    }
    return true;
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


