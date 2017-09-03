package wrongstart;
public class Machine extends Thread{
  private int a=0;
  public void start(){
    run();
  }
  public void run(){
for(a=0;a<50;a++){  //使用Machine对象的实例变量a
      System.out.println(currentThread().getName()+":"+a);
      try{
        sleep(100);
      }catch(InterruptedException e){ throw new RuntimeException(e);}
    }
  }
  public static void main(String args[]){
    Machine machine=new Machine();
    machine.start();
  }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


