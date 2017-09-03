package usecooper;
import java.util.Set;
public class IntSet {
  private Set<Integer> set;

  public IntSet(Set<Integer> set){
    this.set=set;
  }
  public boolean add(int a){
     return set.add(Integer.valueOf(a));  //调用Set的add(Object o)方法
  }
  public boolean remove(int a){
    return set.remove(Integer.valueOf(a));  //调用Set的remove(Object o)方法
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
