import java.util.*;
public class ConcurrentTester{
  public static void main(String args[]){
     final int size=1000;
     final Set<Integer> set=new HashSet<Integer>();
     for(int i=0;i<size;i++)
       set.add(new Integer(i));

     Thread reader=new Thread(){  //遍历集合
       public void run(){
         Iterator<Integer> it=set.iterator();
         while(it.hasNext()){
           System.out.println(it.next());  //抛出ConcurrentModificationException
           yield();
         }
       }
     };

     Thread remover=new Thread(){  
       public void run(){
          for(int i=0;i<size;i++){
            set.remove(new Integer(i));  //删除集合中的元素
            yield();
          }
       }
     };

     reader.start();
     remover.start();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
