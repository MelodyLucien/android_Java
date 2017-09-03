package com.zhouhao.test;

//import com.zhouhao.time.DateUtil;

public class TestLoop {
    
public static void main(String[] args) {
    thread.start();
}

static Thread thread = new Thread(){
    
    long timeout = 2000;
    
    public void run() {
        
      while(true){

          timeout=(long) Math.random()*10000;
          
          //System.out.println(DateUtil.getCurrentTimeByDefaultPattern());
          
          try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      }      
        
    };
};
}
