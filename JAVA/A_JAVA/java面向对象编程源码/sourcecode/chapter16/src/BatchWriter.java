import java.io.*;
import java.nio.*;
public class BatchWriter{
  public static void main(String args[])throws IOException{
    ByteBuffer bb=ByteBuffer.allocate(1024);

    IntBuffer ib=bb.asIntBuffer();
    ib.put(new int[]{10,20,30,40,50});  //向缓冲区写入一批int类型的数据
    System.out.println(ib.get(3));  //打印40

    ib.put(3,400);
    System.out.println(ib.get(3));  //打印400

    ib.rewind();
    while(ib.hasRemaining()){  //依次打印10,20,30,400,50
      int i=ib.get();
      if(i==0)break;  
      System.out.println(i);
    }
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
