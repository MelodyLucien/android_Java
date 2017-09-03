package countbirds;
public class Machine implements Runnable {
  private int a=1;  //共享数据
  public void run() {
    for(int i=0;i<1000;i++){
      a+=i;
      Thread.yield();  //给其他线程运行的机会
      a-=i;
      System.out.println(a);
    }
  }
  public static void main(String args[]) throws InterruptedException{
    Machine machine=new Machine();
    Thread t1=new Thread(machine);
    Thread t2=new Thread(machine);
    t1.start();
    // t2.start();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


