import java.net.URLClassLoader;
import java.net.URL;

public class Tester {
  public static void main(String[] args)throws Exception {
    URL url=new URL("http://www.javathinker.org/java/book/classes/");
    URLClassLoader loader=new URLClassLoader(new URL[]{url}); //父加载器为系统类加载器
    Class objClass = loader.loadClass("Sample");
    Object obj=objClass.newInstance();
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
