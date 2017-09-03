package privatetest;
class Base {
  private String showMe() {
    return "Base";
  }
  public void print(){
    System.out.println(showMe());  //到底调用Base类的showMe()还是Sub类的showMe()?
  }
}
public class Sub extends Base {
  public String showMe(){
    return "Sub";
  }
  public static void main(String args[]){
    Sub sub=new Sub();
    sub.print();
  }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
