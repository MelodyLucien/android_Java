package allrun;
public class Machine extends Thread{
  public void run(){
    for(int a=0;a<50;a++){
      System.out.println(currentThread().getName()+":"+a);
      try{
        sleep(100);  //给其他线程运行的机会
      }catch(InterruptedException e){throw new RuntimeException(e);}
    }
  }
  public static void main(String args[]){
    Machine machine1=new Machine();  //创建第一个Machine对象
    Machine machine2=new Machine();  //创建第二个Machine对象
    machine1.start();  //启动第一个Machine线程
    machine2.start();  //启动第二个Machine线程
    machine1.run();  //主线程执行第一个Machine对象的run()方法
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


