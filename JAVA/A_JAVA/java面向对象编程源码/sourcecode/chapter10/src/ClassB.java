class ClassA{
  static{  System.out.println("now init ClassA");}
}
public class ClassB {
 public static void main(String args[])throws Exception{
   ClassLoader loader=ClassLoader.getSystemClassLoader();  //获得系统类加载器
   Class objClass=loader.loadClass("ClassA");  //加载ClassA
   System.out.println("after load ClassA");
   System.out.println("before init ClassA");
   objClass=Class.forName("ClassA");  //初始化ClassA
 }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
