import java.util.*;
public class CollectionsTester{
  public static void main(String args[]){
    List<String> list=Arrays.asList(new String[]{"Tom","Jack","Linda","Rose"});
    Collections.sort(list); //把List中的元素自然排序
    System.out.println(Collections.max(list));  //打印Tom
    System.out.println(Collections.min(list));  //打印Jack

    System.out.println(Collections.binarySearch(list,"Tom"));  //打印3
    System.out.println(Arrays.toString(list.toArray()));  //打印[Jack,Linda,Rose,Tom]
    Collections.shuffle(list);  //重新随机调整List中元素的位置
    System.out.println(Arrays.toString(list.toArray()));  //打印[Linda,Tom,Jack,Rose]
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
