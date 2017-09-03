import java.io.*;
import java.nio.*;
import java.nio.channels.*;
public class BufferTester {
  public static void main(String args[])throws IOException{
     final int BSIZE=1024;
     //把test.txt文件中的数据拷贝到out.txt中
     FileChannel in=new FileInputStream("D:\\test.txt").getChannel();
     FileChannel out=new FileOutputStream("D:\\out.txt").getChannel();

     ByteBuffer buff=ByteBuffer.allocate(BSIZE);
     while(in.read(buff)!=-1){
       buff.flip();
       out.write(buff);
       buff.clear();
     }
     in.close();
     out.close();
  }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
