package loadtester;

public class Sample {
    public static void main(String[] args) {
      Class c;
      ClassLoader cl,cl1;
      cl = ClassLoader.getSystemClassLoader();  //获得系统加载器
      System.out.println(cl);  //打印系统加载器
      while (cl != null) {     //打印父加载器
        cl1=cl;
        cl = cl.getParent();
        System.out.println(cl1+"'s parent is "+cl);
      }

      try {
        c = Class.forName("java.lang.Object");  //获得代表Object类的Class实例
        cl = c.getClassLoader();  //获得加载Object类的加载器
        System.out.println("java.lang.Object's loader is " + cl);

        c = Class.forName("loadtester.Sample");  //获得代表Sample类的Class实例
        cl = c.getClassLoader();   //获得加载Sample类的加载器
        System.out.println("Sample's loader is " + cl);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}





/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
