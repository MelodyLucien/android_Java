import java.io.*;
public class StringBufferInputStreamTester {
  public static void main(String agrs[])throws IOException{
    StringBufferInputStream in=new StringBufferInputStream("abcd1好");
    int data;
    while((data=in.read())!=-1)
      System.out.print (data+" ");
    in.close();
  }
}





/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
