import java.io.*;
public class ByteArrayTester {
  public static void main(String agrs[])throws IOException{
    byte[] buff=new byte[]{2,15,67,-1,-9,9};
    ByteArrayInputStream in=new ByteArrayInputStream(buff,1,4);
    int data=in.read();
    while(data!=-1){
      System.out.print(data +" ");
      data=in.read();
    }
    in.close();  //ByteArrayInputStream的close()方法实际上不执行任何操作
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
