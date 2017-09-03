public class WithRuntimeEx {
  public int divide(int a,int b){
    if(b==0)throw new IllegalArgumentException("除数不能为0");
    return a/b;  
  }
  public static void main(String args[]){
    new WithRuntimeEx ().divide(1,0);
    System.out.println("End");
  }
}







/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 
