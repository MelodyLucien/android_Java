import java.io.*;
import java.nio.*;
public class ViewTester{
  public static void main(String args[])throws IOException{
    ByteBuffer bb=ByteBuffer.allocate(4);
    while(bb.hasRemaining())  //打印4个0
       System.out.println(bb.get());
    bb.rewind();

    CharBuffer cb=bb.asCharBuffer();  //获得ByteBuffer的CharBuffer视图
    cb.put("你好");
    while(bb.hasRemaining())  //依次打印79,96,89,125
       System.out.println(bb.get());
  }
}





/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
