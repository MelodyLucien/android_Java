public class ReadingLamp{
  /** 灯泡 */ 
  private Bulb bulb;
  
  /** 电源线路 */
  private Circuit circuit;

  /** 构造方法 */
  public ReadingLamp(Bulb bulb, Circuit circuit){
    this.bulb=bulb;
    this.circuit=circuit;
    bulb.setCircuit(circuit);
    circuit.setBulb(bulb);
  }

  /** 更换台灯的灯泡 */
  public void setBulb(Bulb bulb){
    this.bulb=bulb;
    bulb.setCircuit(circuit);
    circuit.setBulb(bulb);
  }

  public void on(){circuit.switchOn();}
  public void off(){circuit.switchOff();}

  public static void main(String args[]){
    Bulb bulb=new Bulb();
    Circuit circuit=new Circuit();
    ReadingLamp lamp=new ReadingLamp(bulb,circuit);
    lamp.on();
    lamp.off();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
