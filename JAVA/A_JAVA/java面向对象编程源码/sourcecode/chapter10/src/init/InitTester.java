package init;
class Base{
static int a=1;
  static{
    System.out.println("init Base");
  }
}

class Sub extends Base{
  static int b=1;
  static{
    System.out.println("init Sub");
  }
}

public class InitTester{
  static{System.out.println("init InitTester");}

/*
  public static void main(String args[]){
    System.out.println("b="+Sub.b);  //执行这行代码时，先依次初始化Base类和Sub类
  }
*/

 public static void main(String args[]){
    Base base;  //不会初始化Base类
    System.out.println("After defining base");
    base=new Base();  //执行这行代码时，初始化Base类
    System.out.println("After creating an object of Base");
    System.out.println("a="+base.a);
    System.out.println("b="+Sub.b);  //执行这行代码时，仅仅初始化Sub类
  }

}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
