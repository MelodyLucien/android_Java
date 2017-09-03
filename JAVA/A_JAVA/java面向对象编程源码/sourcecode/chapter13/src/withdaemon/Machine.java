package withdaemon;
public class Machine extends Thread{
  private int a;
  private static int count;

  public void start(){
    super.start();
    Thread deamon=new Thread(){  //匿名线程类
      public void run(){
        while(true){  //无限循环
          //每隔1秒把实例变量a设为0。
          reset();
          try{
            sleep(1000);
          }catch(InterruptedException e){throw new RuntimeException(e);}
        }
      }
    };
    deamon.setDaemon(true);
    deamon.start();
  }
  public void reset(){a=0;}
  public void run(){
    while(true){
      System.out.println(getName()+":"+a++);
      if(count++==100)break;
      yield();
    }
  }
  public static void main(String args[])throws Exception{
    Machine machine=new Machine();
    machine.start();
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


