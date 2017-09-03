public class Spliter{
  public static void print(String[] s){
    for(int i=0;i<s.length;i++)
      System.out.print(s[i]+" ");
    System.out.println();
  }
  public static void main(String[] args)throws Exception {
     String[] result;
     String str="user=Linda";
     result=str.split("=");  //result={"user","Linda"}
     print(result);  

     str="11:23:14";
     result=str.split(":");  //result={"11","23","14"}
     print(result);

     str="11::14";
     result=str.split(":");  //result={"11","","14"}
     print(result);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
