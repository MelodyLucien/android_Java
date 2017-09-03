package mypack;
import java.util.*;
public class ThreadLocal<T>{  //<T>为范型标记，是JDK1.5中的新特征
   
  private Map<Runnable,T> values=Collections.synchronizedMap(new HashMap<Runnable,T>());
  public T get(){
    Thread curThread=Thread.currentThread();
    T o=values.get(curThread);
    if(o==null && !values.containsKey(curThread)){
      o=initialValue();
      values.put(curThread,o); 
    }
    return o;
  }

  public void set(T newValue){
    values.put(Thread.currentThread(),newValue);

  }
  protected T initialValue(){
    return null;
  } 
}

/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/