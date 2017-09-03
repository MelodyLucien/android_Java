package releaselock;
public class Machine extends Thread {
  private int a=1;
  public synchronized void print(){
      System.out.println("a="+a);
  }
  public void run() {
    synchronized(this){
     try{
       Thread.sleep(2000);
     }catch(InterruptedException e){
       throw new RuntimeException(e);
     }
     a=1/0;  //抛出ArithmeticException
     a++;
    }
  }
  public static void main(String args[]){
    Machine machine=new Machine();
    machine.start();
    Thread.yield();
    machine.print();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


