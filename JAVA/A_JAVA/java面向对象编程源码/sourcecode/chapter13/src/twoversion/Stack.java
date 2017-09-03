package twoversion;

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

  /**  采用了同步机制的SynStack内部类 */
  public static class SynStack extends Stack{
    private Stack stack;
    public SynStack(Stack stack){super(stack.getName());this.stack=stack;}
    public synchronized int getPoint(){return stack.getPoint();}
    public synchronized String pop() {return stack.pop();}
    public synchronized void push(String goods) { stack.push(goods);}
  }
  
  /** 返回一个Stack对象的同步版本 */
  public static Stack synchronizedStack(Stack stack){
    return new SynStack(stack);
  }

}

/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


