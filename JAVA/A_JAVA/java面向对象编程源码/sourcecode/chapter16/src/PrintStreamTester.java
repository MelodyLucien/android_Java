import java.io.*;
public class PrintStreamTester{
  private static void readBuff(byte[] buff)throws IOException{
    ByteArrayInputStream in=new ByteArrayInputStream(buff);
    int data;
    while((data=in.read())!=-1)
       System.out.print(data+" ");
    System.out.println();
in.close();
  }

  public static void main(String agrs[])throws IOException{
     //通过PrintStream写字符串“好”
   ByteArrayOutputStream out=new ByteArrayOutputStream();
     PrintStream ps=new PrintStream(out,true);
     ps.print("好");
     ps.close();

     byte[] buff=out.toByteArray(); //获得字节数组
     System.out.println("采用本地操作系统的默认字符编码：");
     readBuff(buff);
     
     //通过DataOutputStream写字符串“好”
     out=new ByteArrayOutputStream();
     DataOutputStream ds=new DataOutputStream(out);
     ds.writeUTF("好");
     ds.close();

     buff=out.toByteArray(); //获得字节数组
     System.out.println("采用适用于Java语言的UTF-8字符编码：");
     readBuff(buff);
   }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
