public class Sample{
  private int a;  //实例变量
  public int method(){
    int b=0;  //局部变量
    a++;
    b=a;
    return b;
  }
  public static void main(String args[]){
    Sample s=null;  //局部变量
    int a=0;  //局部变量

    s=new Sample();
    a=s.method(); 
    System.out.println(a);
  }
}

/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


