package problem;
public class SyncTest {
  public static void main(String args[]) {
    Stack stack = new Stack("stack1");
    Producer producer1 = new Producer(stack,"producer1");
    Consumer consumer1 = new Consumer(stack,"consumer1");
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
      goods="goods"+(theStack.getPoint()+1);
      theStack.push(goods);
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

  public int getPoint(){return point;}

  public String pop() {
    String goods = buffer[point];
    buffer[point]=null;
    Thread.yield();
    point--;
    return goods;
  }

  public void push(String goods) {
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

 


