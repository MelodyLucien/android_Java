import java.io.*;
public class UseFilenameFilter{
  public static void main(String args[])throws Exception{
    File dir1=new File("D:\\");
    
    FilenameFilter filter=new FilenameFilter(){  //匿名类
       public boolean accept(File dir,String name){
         System.out.println("根路径："+dir.getPath()+" 子路径："+name); 
         File currFile=new File(dir,name); 
         if(currFile.isFile() && name.indexOf(".txt")!=-1)            
           return true;
         else
           return false;  
       }
    };
    
    //仅仅返回D:\目录下扩展名为“.txt”的文件清单
    String[] lists=new File("D:\\").list(filter);
    for(int i=0;i<lists.length;i++)
       System.out.println(lists[i]);
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
