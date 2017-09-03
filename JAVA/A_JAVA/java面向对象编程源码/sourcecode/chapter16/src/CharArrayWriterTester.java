import java.io.*;
public class CharArrayWriterTester {
  public static void main(String args[])throws IOException{
    CharArrayWriter writer=new CharArrayWriter();
    writer.write('你');
    writer.write('好');
    
    char[] buff=writer.toCharArray();
    System.out.println(new String(buff));  //打印“你好”

    writer.close();  //CharArrayWriter的close()方法不执行任何操作

  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
