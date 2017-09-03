public class MainCatcher{
  public void methodA(int money)throws SpecialException{
    if(--money<=0) throw new SpecialException("Out of money");
    System.out.println("methodA");
  }
  public void methodB(int money) throws SpecialException{
    methodA(money);
    System.out.println("methodB");
  }
  public static void main(String args[]){
    try{
      new MainCatcher().methodB(1);
      System.out.println("main");
    }catch(SpecialException e){
      System.out.println("Wrong");
    }
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 
