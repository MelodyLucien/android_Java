import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class BigFileTester {
  public static void main(String args[])throws IOException{
    int capacity=0x8000000; //128M
    MappedByteBuffer mb=new RandomAccessFile("D:\\test.txt","rw")
                        .getChannel()
                        .map(FileChannel.MapMode.READ_WRITE,0,capacity);
    mb.put("你好啊".getBytes("GBK"));  //向文件中写入采用GBK编码的字符串“你好啊”
    mb.flip();
    System.out.println(Charset.forName("GBK").decode(mb));
  }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
