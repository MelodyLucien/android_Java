import java.util.*;
public class Visitor{
  public static void print(Collection<? extends Object> c){
    Iterator<? extends Object> it=c.iterator();
    //遍历集合中的所有元素
    while(it.hasNext()){
      Object element=it.next();  //取出集合中的一个元素
      System.out.println(element);  
    }
  }
  public static void main(String args[]){
    Set<String> set=new HashSet<String>();
    set.add("Tom");
    set.add("Mary");
    set.add("Jack");
    print(set);
 
    List<String> list=new ArrayList<String>();
    list.add("Linda");
    list.add("Mary");
    list.add("Rose");
    print(list);
    
    Map<String,String> map=new HashMap<String,String>();
    map.put("M","男"); 
    map.put("F","女");
    print(map.entrySet());  

  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
