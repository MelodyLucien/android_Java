package diffnames;

public class A{
  int v=1;
  class B{
    int v=2;
    public void test(){
      System.out.println("v="+v);  //打印v=2
      System.out.println("this.v="+this.v);  //打印this.v=2
      System.out.println("A.this.v="+A.this.v);  //打印A.this.v=1
    }
  }

  public static void main(String args[]){
    new A().new B().test();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
