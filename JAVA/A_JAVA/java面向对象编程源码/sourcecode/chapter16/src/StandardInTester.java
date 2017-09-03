import java.io.*;
public class StandardInTester {
  public static void main(String args[])throws IOException{
    InputStreamReader reader=new InputStreamReader(System.in);
    BufferedReader br=new BufferedReader(reader);
    String data;
    while((data=br.readLine())!=null && data.length()!=0)
     System.out.println("echo:"+data);
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
