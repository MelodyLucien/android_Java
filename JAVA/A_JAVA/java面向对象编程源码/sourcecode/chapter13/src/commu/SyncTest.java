package commu;
public class SyncTest {
  public static void main(String args[]) {
    Stack stack1 = new Stack("stack1");
    Producer producer1 = new Producer(stack1,"producer1");
    Consumer consumer1 = new Consumer(stack1,"consumer1");
    Consumer consumer2 = new Consumer(stack1,"consumer2");

    Stack stack2 = new Stack("stack2");
    Producer producer2 = new Producer(stack2,"producer2");
    Producer producer3 = new Producer(stack2,"producer3");
    Consumer consumer3= new Consumer(stack2,"consumer3");


  }
}

/**  生产者线程  */
class Producer extends Thread {
  private Stack theStack;

  public Producer (Stack s,String name) {
    super(name);
    theStack = s;
    start();  //启动自身生产者线程
  }

  public void run() {
    String goods;
    for (int i = 0; i < 200; i++) {
      synchronized(theStack){
        goods="goods"+(theStack.getPoint()+1);
        theStack.push(goods);
      }
      System.out.println(getName()+ ": push " + goods +" to "+theStack.getName());
      yield();
    }
  }
}

/**  消费者线程 */
class Consumer extends Thread {
  private Stack theStack;

  public Consumer (Stack s,String name) {
    super(name);
    theStack = s;  
    start();  //启动自身消费者线程
  }

  public void run() {
    String goods;
    for (int i=0; i < 200; i++) {
      goods = theStack.pop();
      System.out.println(getName() + ": pop " + goods +" from "+theStack.getName());
      yield();
    }
  }
}

/**  堆栈  */
class Stack {
  private String name;
  private String[] buffer=new String[100];
  int point=-1;

  public Stack(String name){this.name=name;}
  public String getName(){return name;}

  public synchronized int getPoint(){return point;}

  public synchronized String pop() {
      this.notifyAll();

      while(point==-1){
        System.out.println(Thread.currentThread().getName()+": wait");
        try{
          this.wait();
        }catch(InterruptedException e){throw new RuntimeException(e);}
      }

      String goods = buffer[point];
      buffer[point]=null;
      Thread.yield();
      point--;
      return goods;
  }
  public synchronized void push(String goods) {  
     this.notifyAll();

     while(point==buffer.length-1){
       System.out.println(Thread.currentThread().getName()+": wait");
       try{
         this.wait();
       }catch(InterruptedException e){throw new RuntimeException(e);}
    }
    point++;
    Thread.yield();
    buffer[point]=goods;
  }

}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


