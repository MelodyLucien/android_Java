import java.io.*;
public class Redirecter{
  /** 为标准I/O重定向 */
  public static void redirect(InputStream in,PrintStream out,PrintStream err){
    System.setIn(in);
    System.setOut(out);
    System.setErr(err);
  }

  /** 把来自标准输入流的数据写到标准输出流和标准标准错误输出流 */
  public static void copy()throws IOException{
    InputStreamReader reader=new InputStreamReader(System.in);
    BufferedReader br=new BufferedReader(reader);
    String data;
    while((data=br.readLine())!=null && data.length()!=0){
     System.out.println(data);  //向标准输出流写数据
     System.err.println(data);  //向标准错误输出流写数据
    }
  }
  public static void main(String args[])throws IOException{
    InputStream standardIn=System.in;
    PrintStream standardOut=System.out;
    PrintStream standardErr=System.err;

    InputStream in=new BufferedInputStream(new FileInputStream("D:\\test.txt"));
    PrintStream out=new PrintStream(
new BufferedOutputStream(new FileOutputStream("D:\\out.txt")));
    PrintStream err=new PrintStream
(new BufferedOutputStream(new FileOutputStream("D:\\err.txt")));
    redirect(in,out,err);  //把标准I/O重定向到文件
    copy();  //把D:\test.txt文件中的数据拷贝到D:\out.txt和D:\err.txt文件中

    //对于用户创建的流，不再使用它们时，应该关闭它们
    in.close();
    out.close();
    err.close();

    redirect(standardIn,standardOut,standardErr);  //使标准I/O采用默认的流
    copy();  //把从键盘输入的数据输出到控制台
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
