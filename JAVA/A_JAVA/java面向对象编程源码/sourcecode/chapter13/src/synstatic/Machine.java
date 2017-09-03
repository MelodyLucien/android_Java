package synstatic;
public class Machine implements Runnable {
  private static int a=1;  //静态变量
  public synchronized static void go(int i){
      a+=i;
      Thread.yield();
      a-=i;
      System.out.println(a);
  }
  public void run() {
    for(int i=0;i<1000;i++){
      go(i);
    }
  }
  public static void main(String args[]) throws InterruptedException{
    Machine machine=new Machine();
    Thread t1=new Thread(machine);
    Thread t2=new Thread(machine);
    t1.start();
    t2.start();
  }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


