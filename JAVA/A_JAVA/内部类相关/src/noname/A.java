package noname;

public class A {
A(int v){System.out.println("another constructor");}
  A(){System.out.println("default constructor");}

  void method(){System.out.println("from A");};

  public static void main(String args[]){
    new A().method();  //打印from A

    A a=new A(){  //匿名类
      void method(){System.out.println("from anonymous");}
    };
    a.method();  //打印from anonymous
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
