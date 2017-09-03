import java.util.EmptyStackException;
public class Stack {
  private Object[] elements;  //存放对象
  private int size=0;
  private int capacityIncrement=10;  //堆栈的容量增长的步长
	
  public Stack(int initialCapacity,int capacityIncrement) {
    this(initialCapacity);
    this.capacityIncrement=capacityIncrement;
  }
  public Stack(int initialCapacity) {
    elements=new Object[initialCapacity];
  }

  public void push(Object object){
   ensureCapacity();
   elements[size++]=object;
  }

  public Object pop(){
   if(size==0)
     throw new EmptyStackException();
   elements[size]=null;  //清除过期的对象引用
   return elements[--size];
  }
  
  private void ensureCapacity(){  //增加堆栈的容量
    if(elements.length==size){
      Object[] oldElements=elements;
      elements=new Object[elements.length+capacityIncrement];
      System.arraycopy(oldElements,0,elements,0,size);
    }
  }

  public static void main(String args[]){
    Stack stack=new Stack(1000);
    for(int a=0;a<1000;a++)
      stack.push(new Integer(a));
    for(int a=0;a<1000;a++)
      System.out.println(stack.pop());
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
