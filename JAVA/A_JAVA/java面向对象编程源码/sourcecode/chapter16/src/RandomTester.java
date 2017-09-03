import java.io.*;
public class RandomTester {
  public static void main(String args[])throws IOException{
     RandomAccessFile rf=new RandomAccessFile("D:\\test.dat","rw");
     for(int i=0;i<10;i++)
       rf.writeLong(i*1000);

     rf.seek(5*8);  //从文件开头开始，跳过第5个long数据，接下来写第6个long数据
     rf.writeLong(1234);

     rf.seek(0);  //把读写指针定位到文件开头
     for(int i=0;i<10;i++)
      System.out.println("Value"+i+":"+rf.readLong());

     rf.close();
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
