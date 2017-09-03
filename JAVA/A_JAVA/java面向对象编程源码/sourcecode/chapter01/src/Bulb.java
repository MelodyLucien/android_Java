public class Bulb{
  private Circuit circuit;
  public void setCircuit(Circuit circuit){
    this.circuit=circuit; 
  }
  public void light(){
    circuit.transportPower(); 
  }
  public void goOut(){System.out.println("go out");}
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
