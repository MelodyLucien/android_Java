public class WithFinally {
  public void methodA(int money)throws SpecialException{
      if(--money<=0) throw new SpecialException("Out of money");
      System.out.println("methodA");
  }
  public static void main(String args[]){
     try{
        new WithFinally().methodA(1);  //抛出SpecialException异常
        System.out.println("main");
     }catch(SpecialException e){
        System.out.println("Wrong");
     }finally{
       System.out.println("Finally");
     }
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 
