import java.io.*;
public class StringReaderTester {
  public static void main(String args[])throws IOException{
    StringReader reader=new StringReader("abcd1好");
    int data;
    while((data=reader.read())!=-1)
      System.out.print((char)data+" ");
    reader.close();  //StringReader的close()方法不执行任何操作
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
