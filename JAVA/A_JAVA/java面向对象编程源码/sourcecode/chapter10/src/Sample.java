public class Sample {
  public int v1=1;
  public Sample(){
    System.out.println("Sample is loaded by "+this.getClass().getClassLoader());
    new Dog();  //主动使用Dog类
  }
}






/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
