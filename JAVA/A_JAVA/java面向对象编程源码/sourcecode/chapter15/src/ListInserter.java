import java.util.*;
public class ListInserter {
  /**  向List列表中按顺序插入一数据 */
  public static void insert(List<Integer> list,int data){
    ListIterator<Integer> it=list.listIterator();
    while(it.hasNext()){
      Integer in=it.next();
      if(data<=in.intValue()){
        it.previous();
        it.add(new Integer(data));  //插入元素
        break;
      }
    }
  }
  public static void main(String args[]){
    List<Integer> list=new LinkedList<Integer>();  //创建一个链接列表
    list.add(new Integer(3));
    list.add(new Integer(2));
    list.add(new Integer(5));
    list.add(new Integer(9));

    Collections.sort(list);
    insert(list,6);
    System.out.println(Arrays.toString(list.toArray()));
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
