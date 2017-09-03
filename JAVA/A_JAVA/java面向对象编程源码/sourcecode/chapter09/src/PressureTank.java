public class PressureTank {
  private int pressure;  //当前压力
  private static final int SAFE_PRESSURE=2;  //当锅内压力低于2个大气压，可打开锅盖
  private static final int CRITICAL_POINT=3;  //爆炸时的压力临界点为3个大气压

  public boolean isSafeForOpen(){
    return pressure<=SAFE_PRESSURE;
  }
  
public void exhaust(){  //排气
    pressure=SAFE_PRESSURE;
  }

  public void open(){  //打开锅盖
    if(pressure>=CRITICAL_POINT)throw new ExplosionException();
  }

  public void cook(){ 
    pressure=CRITICAL_POINT;
  }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 
