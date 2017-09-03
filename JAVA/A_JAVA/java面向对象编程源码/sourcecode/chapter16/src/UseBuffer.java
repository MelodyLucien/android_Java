import java.io.*;
public class UseBuffer{
  public static void main(String agrs[])throws IOException{
    final int SIZE=1024;
    FileInputStream in=new FileInputStream("D:\\test.txt");
    FileOutputStream out=new FileOutputStream("D:\\out.txt");

    byte[] buff=new byte[SIZE];  //创建字节数组缓冲区

    int len=in.read(buff);  //把test.txt文件中的数据读入到buff中
    while(len!=-1){
      out.write(buff,0,len);  //把buff中的数据写到out.txt文件中
      len=in.read(buff);
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
