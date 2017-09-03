package deadlock;
public class Machine extends Thread{
  private Counter counter;  //共享数据
  public Machine(Counter counter){
    this.counter=counter;
    start();  //启动自身线程
  }

  public void run(){
    for(int i=0;i<1000;i++){
      counter.add();
    }
  }

  public static void main(String args[]) throws InterruptedException{
    Counter counter1=new Counter();
    Counter counter2=new Counter();
    counter1.setFriend(counter2);
    counter2.setFriend(counter1);

    Machine machine1=new Machine(counter1);
    Machine machine2=new Machine(counter2);
  }
}

class Counter{
  private int a;
  private Counter friend;
  public void setFriend(Counter friend){
    this.friend=friend;
  }

  public synchronized void add(){
    a++;
    Thread.yield();
    friend.delete();
    System.out.println(Thread.currentThread().getName()+": add");
  }
  public synchronized void delete(){
    a--;
    System.out.println(Thread.currentThread().getName()+": delete");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


