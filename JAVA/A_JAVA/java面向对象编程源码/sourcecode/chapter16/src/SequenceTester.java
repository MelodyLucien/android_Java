import java.io.*;
public class SequenceTester {
  public static void main(String[] args)throws IOException {
    InputStream s1=new ByteArrayInputStream("你".getBytes());  
    InputStream s2=new ByteArrayInputStream("好".getBytes());  
    InputStream in=new SequenceInputStream(s1,s2);
    int data;
    while((data=in.read())!=-1)
      System.out.print(data+" ");
    in.close();  //关闭所有被串联的输入流
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
