import java.util.Set;
import java.util.HashSet;

public class HouseMarket{
  private static Set<RenterIFC> renters=new HashSet<RenterIFC>();
  public static void registerRenter(RenterIFC renter){
    renters.add(renter);
  }

  public static RenterIFC findRenter(){
    return (RenterIFC)renters.iterator().next();
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
