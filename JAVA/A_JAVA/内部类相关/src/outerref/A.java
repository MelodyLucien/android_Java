package outerref;

public class A{
  private int a1;
  public int a2;
  static int a3;
  public A(int a1,int a2){this.a1=a1;this.a2=a2;}

  protected int methodA(){return a1*a2;}

  class B{  //内部类
    int b1=a1;  //直接访问private的a1
    int b2=a2;  //直接访问public的a2
    int b3=a3;  //直接访问static的a3
    int b4=new A(3,4).a1;  //访问一个新建的实例A的a1
    int b5=methodA();  //访问methodA()方法
  }

  public static void main(String args[]){
    A.B b=new A(1,2).new B();
    System.out.println("b.b1="+b.b1);  //打印b.b1=1
    System.out.println("b.b2="+b.b2);  //打印b.b2=2
    System.out.println("b.b3="+b.b3);  //打印b.b3=0
    System.out.println("b.b4="+b.b4);  //打印b.b4=3
    System.out.println("b.b5="+b.b5);  //打印b.b5=2
  }
}

/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
