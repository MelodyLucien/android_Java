package exhandler;
class MachineGroup extends ThreadGroup{
  public MachineGroup(){super("MachineGroup");}

  public void uncaughtException(Thread t, Throwable e){
    System.out.println(getName()+" catches an exception from "+t.getName());
    super.uncaughtException(t,e);
  }
}

class MachineHandler implements Thread.UncaughtExceptionHandler{
  private String name;
  public MachineHandler(String name){this.name=name;}

  public void uncaughtException(Thread t, Throwable e){
    System.out.println(name+" catches an exception from "+t.getName());
  }
}

public class Machine extends Thread{
  public Machine(ThreadGroup group, String name){super(group,name);}
  public void run(){
    int a=1/0;  //throw ArithmeticException运行时异常
  }
  public static void main(String args[])throws Exception{
    ThreadGroup group=new MachineGroup();
     //设置Machine类的默认异常处理器     
    UncaughtExceptionHandler defaultHandler=new MachineHandler("DefaultHandler");   

    Machine.setDefaultUncaughtExceptionHandler(defaultHandler); 
    
    Machine m1=new Machine(group,"machine1");
    Machine m2=new Machine(group,"machine2");
    
    UncaughtExceptionHandler currHandler=new MachineHandler("Machine2\'handler");   
              //设置m2的当前异常处理器
    m2.setUncaughtExceptionHandler(currHandler);

    m1.start();
    m2.start(); 
  } 
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


