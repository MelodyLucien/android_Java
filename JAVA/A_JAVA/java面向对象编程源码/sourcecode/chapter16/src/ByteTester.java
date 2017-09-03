import java.io.*;
public class ByteTester {
  public static void main(String agrs[])throws IOException{
     ByteArrayOutputStream out=new ByteArrayOutputStream();
     out.write(259);
     
     byte[] buff=out.toByteArray(); //获得字节数组
     out.close();

     System.out.println("buff.length="+buff.length);

     ByteArrayInputStream in=new ByteArrayInputStream(buff);
     int data;
     while((data=in.read())!=-1)
       System.out.println(data);
     
     in.close();
  }

}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
