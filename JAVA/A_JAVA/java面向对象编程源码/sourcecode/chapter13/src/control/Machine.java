package control;
public class Machine extends ControlledThread{
  private int count;  //共享数据

  public void run(){
    while(true){
     synchronized(this){
       count++;
       System.out.println(Thread.currentThread().getName()+":run "+count+" times");
     }
     if(!checkState()){
       System.out.println(Thread.currentThread().getName()+":stop");
       break;
     }
    }
  }

  public synchronized int getCount(){return count;}

  public synchronized void reset(){
    count=0;
    System.out.println(Thread.currentThread().getName()+":reset");
  }

  public static void main(String args[]){
    Machine machine=new Machine();
    machine.start();
    for(int i=0;i<200;i++){
      if(machine.getCount()>5){
        machine.setState(ControlledThread.SUSP);  //让machine线程暂停
        yield();
        machine.reset();
        machine.setState(ControlledThread.RUN);  //让machine线程恢复运行
       }
       yield();
    }
    machine.setState(ControlledThread.STOP);  //让machine线程终止运行
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 


