public class Sleeper extends Thread{
  public void run(){
    try{
      sleep(60000);  //睡眠1分钟
      System.out.println("sleep over");
    }catch(InterruptedException e){
      System.out.println("sleep interrupted");
    }
    System.out.println("end");
  }

  public static void main(String[] args)throws Exception{
    Sleeper sleeper = new Sleeper();
    sleeper.start();
    Thread.sleep(10);
    sleeper.interrupt();  //中断Sleeper线程的睡眠
  }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


