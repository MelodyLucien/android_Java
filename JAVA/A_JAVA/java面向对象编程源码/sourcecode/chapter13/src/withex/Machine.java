package withex;
public class Machine extends Thread{
  public void run(){
    for(int a=0;a<3;a++){
      System.out.println(currentThread().getName()+":"+a);
      if(a==1 && currentThread().getName().equals("m1"))
         throw new RuntimeException("Wrong from Machine");
      try{
        sleep(100);
      }catch(InterruptedException e){throw new RuntimeException(e);}
    }
  }
  public static void main(String args[])throws Exception{
    Machine machine=new Machine();
    machine.setName("m1");

    machine.start();
    machine.run();
    System.out.println("Is machine alive:"+machine.isAlive());
    System.out.println("main:end");
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


