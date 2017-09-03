package extendth;
public class Machine extends Thread{
  public void run(){
    for(int a=0;a<50;a++)
      System.out.println(a);
  }
  
  public static void main(String args[]){
    Machine machine=new Machine();
    machine.start();  //启动machine线程
  }	
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


