import java.lang.ref.*;
import java.util.*;

class Grocery{
  private static final int SIZE = 10000;
  //属性d使得每个Grocery对象占用较大内存空间，有80KB左右
  private double[] d = new double[SIZE];  
  private String id;
  public Grocery(String id) { this.id = id; }
  public String toString() { return id; }
  public void finalize() {
    System.out.println("Finalizing " + id);
  }
}

public class References {
  private static ReferenceQueue<Grocery> rq = new ReferenceQueue<Grocery>();
  public static void checkQueue() {
    Reference<? extends Grocery> inq = rq.poll();  //从队列中取出一个引用
    if(inq != null)
      System.out.println("In queue: "+inq+" : "+inq.get());
  }

  public static void main(String[] args) {
    final int size=10;
 
    //创建10个Grocery对象及10个软引用
    Set<SoftReference<Grocery>> sa = new HashSet<SoftReference<Grocery>>();
    for(int i = 0; i < size; i++) {
      SoftReference<Grocery> ref=
               new SoftReference<Grocery>(new Grocery("Soft " + i), rq);
      System.out.println("Just created: " +ref.get());
      sa.add(ref);
    }
    System.gc();
    checkQueue();

    //创建10个Grocery对象以及10个弱引用
    Set<WeakReference<Grocery>> wa = new HashSet<WeakReference<Grocery>>();
    for(int i = 0; i < size; i++) {
       WeakReference<Grocery> ref=
          new WeakReference<Grocery>(new Grocery("Weak " + i), rq);
      System.out.println("Just created: " +ref.get());
      wa.add(ref); 
    }
    System.gc();
    checkQueue();

    //创建10个Grocery对象及10个虚引用
    Set<PhantomReference<Grocery>> pa = new HashSet<PhantomReference<Grocery>>();
    for(int i = 0; i < size; i++) {
      PhantomReference<Grocery>ref = 
             new PhantomReference<Grocery>(new Grocery("Phantom " + i), rq);
      System.out.println("Just created: " +ref.get());
      pa.add(ref);
    }
    System.gc();
    checkQueue();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
