package runimpl;
public class Machine implements Runnable{
  private int a=0;
  public void run(){
    for(a=0;a<50;a++){
      System.out.println(Thread.currentThread().getName()+":"+a);
      try{
        Thread.sleep(100);
      }catch(InterruptedException e){throw new RuntimeException(e);}
    }
  }
  public static void main(String args[]){
    Machine machine=new Machine();
    Thread t1=new Thread(machine);
    Thread t2=new Thread(machine);
    t1.start();
    t2.start();
  }
}






/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


