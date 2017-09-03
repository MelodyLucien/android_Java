public class Circuit{
  private Bulb bulb;
  public void setBulb(Bulb bulb){
    this.bulb=bulb; 
  }
  public void switchOn(){
    bulb.light();
  }

  public void switchOff(){
    bulb.goOut();
  }
 
  public void transportPower(){System.out.println("transport power ");}
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
