import java.io.*;
public class CharArrayTester {
  public static void main(String args[])throws IOException{
    char[] buff=new char[]{'a','你','好','1'};
    CharArrayReader reader=new CharArrayReader(buff);
    int data;
    while((data=reader.read())!=-1)
      System.out.print((char)data+" ");
    reader.close();  //CharArrayReader的close()方法不执行任何操作
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
