package correctstart;
public class Machine extends Thread{
  private int a=0;
  private static int count=0;  //统计被启动的Machine线程的数目
  public void start(){
    super.start();
    System.out.println(currentThread().getName()
+":第"+(++count)+"个Machine线程启动");  //这行代码由主线程执行
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
    Machine machine1=new Machine();
    Machine machine2=new Machine();
    machine1.start();
    machine2.start();
  }
}





/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


