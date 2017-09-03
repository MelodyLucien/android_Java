public class Scope{
  int x; //x为实例变量
  int y; //y为实例变量
  static int z; //z为静态变量

  void method(int x){ //x为方法参数
    int y=1;  //y为局部变量
    int z=1;  //z为局部变量
    this.x=x+1;  //this.x代表实例变量x；单独的x代表局部变量x
    this.y=y+1;  //this.y代表实例变量y；单独的y代表局部变量y
    Scope.z=z+1;  //Scope.z代表静态变量z；单独的z代表局部变量z

    System.out.println("x="+x);  //打印局部变量x
    System.out.println("y="+y);  //打印局部变量y
    System.out.println("z="+z);  //打印局部变量z
    System.out.println("this.x="+this.x);  //打印实例变量x
    System.out.println("this.y="+this.y);  //打印实例变量y
    System.out.println("Scope.z="+Scope.z);  //打印静态变量z
  }

  public static void main(String args[]){
    Scope obj=new Scope();
    obj.method(1);
  }
}

/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 
