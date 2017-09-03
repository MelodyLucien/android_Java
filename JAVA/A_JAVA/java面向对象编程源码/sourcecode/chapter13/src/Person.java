public class Person extends Thread{
  private Well well;
  public Person(Well well){
    this.well=well;
    start();  //启动自身线程
  }

  public void run(){
    synchronized(well){
      for(int i=0;i<10;i++){  //打10桶水
        well.withdraw();
        yield();
      }
    }
  }
  public static void main(String args[]){
    Well well=new Well();
    Person persons[]=new Person[10];
    for(int i=0;i<10;i++)  //创建10个Person线程
      persons[i]=new Person(well);
  }
}

class Well{
  private int water=1000;
  public void withdraw(){  //打一桶水
    water--;
    System.out.println(Thread.currentThread().getName()+": water left:"+water);
  }
}






/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


