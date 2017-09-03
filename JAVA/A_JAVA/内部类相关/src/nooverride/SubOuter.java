package nooverride;

class Outer{
  Inner in;
  Outer(){in=new Inner();}  //构造Outer.Inner类的实例

  public class Inner{  //public访问级别
    public Inner(){System.out.println("inner of Outer");}
  }
}

public class SubOuter extends Outer{
  class Inner{  //默认访问级别
    public Inner(){System.out.println("inner of SubOuter");}
  }

  public static void main(String args[]){
     SubOuter.Inner in1=new SubOuter().new Inner();
     Outer.Inner in2=new Outer().new Inner();
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
