package initbase;

class Base{
  static int a=1;
  static{
    System.out.println("init Base");
  }
  static void method(){
    System.out.println("method of Base");
  }
}
class Sub extends Base{
  static{
    System.out.println("init Sub");
  }
}
public class Sample{
  public static void main(String args[]){
    System.out.println("a="+Sub.a);  //仅仅初始化Base类
    Sub.method();  
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
