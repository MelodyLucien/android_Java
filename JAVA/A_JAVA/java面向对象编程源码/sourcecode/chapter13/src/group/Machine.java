package group;
public class Machine extends Thread{
  public Machine(ThreadGroup group, String name){super(group,name);}
  public void run(){
    for(int a=0;a<1000;a++){
      System.out.println(Thread.currentThread().getName()+":"+a);
      yield();
    }
  }
  public static void main(String args[])throws Exception{
    ThreadGroup group=new ThreadGroup("machines");
    for(int i=1;i<=5;i++){
      Machine machine=new Machine(group,"machine"+i);
      machine.start();
    }
    int activeCount=group.activeCount();
    Thread[] machines=new Thread[activeCount];
    group.enumerate(machines);
    for(int i=0;i<activeCount;i++)
      System.out.println(machines[i].getName()+" is alive");
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


