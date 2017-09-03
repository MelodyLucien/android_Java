package threadref;
public class Machine extends Thread{
  public void run(){
    for(int a=0;a<3;a++){
      System.out.println(currentThread().getName()+":"+a);
      yield();
    }
  }
  public static void main(String args[])throws Exception{
    Machine machine=new Machine();
    machine.setName("m1");

    machine.start();
    machine.run();
  }
}





/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


