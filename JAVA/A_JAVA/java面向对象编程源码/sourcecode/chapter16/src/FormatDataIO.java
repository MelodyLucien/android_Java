import java.io.*;
public class FormatDataIO{
  public static void main(String[] args)throws IOException {
    FileOutputStream out1=new FileOutputStream("D:\\test.txt");
    BufferedOutputStream out2=new BufferedOutputStream(out1); //装饰文件输出流
    DataOutputStream out=new DataOutputStream(out2); //装饰带缓冲输出流
    out.writeByte(-12);
    out.writeLong(12);
    out.writeChar('1');
    out.writeUTF("好");
    out.close();

    InputStream in1=new FileInputStream("D:\\test.txt");
    BufferedInputStream in2=new BufferedInputStream(in1); //装饰文件输入流
    DataInputStream in=new DataInputStream(in2); //装饰缓冲输入流
    System.out.print(in.readByte()+" ");
    System.out.print(in.readLong()+" ");
    System.out.print(in.readChar()+" ");
    System.out.print(in.readUTF()+" ");
    in.close();
 }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
