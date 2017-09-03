import java.util.*;
public class ElementTypeTesterNew{
   public Set<String> createSet(){
     Set<String> set = new HashSet<String>();
     set.add("Mike");
     return set;
   }
   public void printSet(Set<String> set){
     Iterator<String> it=set.iterator();
     while(it.hasNext()){
       String str=it.next();  //无需类型转换
       System.out.println(str);
     }
   }
  public static void main(String args[]){
    ElementTypeTesterNew s=new ElementTypeTesterNew();
    Set<String> set=s.createSet();
    s.printSet(set);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
