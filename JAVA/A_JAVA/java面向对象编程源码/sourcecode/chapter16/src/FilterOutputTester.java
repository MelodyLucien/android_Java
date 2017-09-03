import java.io.*;
public class FilterOutputTester{
  public static void main(String[] args)throws IOException {
    FileOutputStream out1=new FileOutputStream("D:\\test.txt");
    BufferedOutputStream out2=new BufferedOutputStream(out1,2);  //装饰一个文件输出流
    DataOutputStream out=new DataOutputStream(out2);  //装饰一个带缓冲输出流
    out.writeUTF("你好啊");
   
    InputStream in1=new FileInputStream("D:\\test.txt");
    BufferedInputStream in2=new BufferedInputStream(in1); //装饰一个文件输入流
    DataInputStream in=new DataInputStream(in2); //装饰一个缓冲输入流
    System.out.println(in.readUTF());
    in.close();
 }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
