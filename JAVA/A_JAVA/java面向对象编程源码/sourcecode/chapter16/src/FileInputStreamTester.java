import java.io.*;
public class FileInputStreamTester{
  public static void main(String agrs[])throws IOException{
    FileInputStream in=new FileInputStream("D:\\test.txt");
    int data;
    while((data=in.read())!=-1)
      System.out.print(data +" ");
    in.close();
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
