package inherit;

class Outer{
  private int a;
  public Outer(int a){this.a=a;}

  class Inner{
    public Inner(){}
    public void print(){System.out.println("a="+a);}  //访问外部类的实例变量a
  }
}

public class Sample extends Outer.Inner{
  //public Sample(){} //编译错误

  public Sample(Outer o){
    o.super();
  }

  public static void main(String args[]){
     Outer outer1=new Outer(1);
     Outer outer2=new Outer(2);

     Outer.Inner in=outer1.new Inner();
     in.print();

     Sample s1=new Sample(outer1);
     Sample s2=new Sample(outer2);
     s1.print();  //打印a=1
     s2.print();  //打印a=2
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
