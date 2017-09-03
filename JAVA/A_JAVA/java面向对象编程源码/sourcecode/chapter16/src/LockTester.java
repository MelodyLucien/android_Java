import java.io.*;
import java.nio.channels.*;
public class LockTester {
  public static void main(String args[])throws Exception{
    FileOutputStream fos=new FileOutputStream("D:\\test.txt");
    FileLock fl=fos.getChannel().tryLock();
    if(fl!=null){
      System.out.println("Locked File");
      System.out.println(fl.isShared());
      Thread.sleep(60000);  //锁定文件60秒
      fl.release();
      System.out.println("Released Lock");
    }
  }
}





/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
