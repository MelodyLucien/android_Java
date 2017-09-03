import java.util.Map;
import java.util.HashMap;
public class Ghost {
  private static final Map<String,Ghost> ghosts=new HashMap<String,Ghost>();
  private final String name;
  public Ghost(String name) {
    this.name=name;
  }
  public String getName(){return name;}

  public static Ghost getInstance(String name){
    Ghost ghost =ghosts.get(name);
        if (ghost == null) {
            ghost=new Ghost(name);
            ghosts.put(name,ghost);
        }
        return ghost;

  }
  public static void removeInstance(String name){
    ghosts.remove(name);
   }
  protected void finalize()throws Throwable{
    ghosts.put(name,this);
    System.out.println("execute finalize");
    throw new Exception("Just Test");
  }

  public static void main(String args[])throws Exception{
    Ghost ghost=Ghost.getInstance("IAmBack"); //①
    System.out.println(ghost); //②
    String name=ghost.getName(); //③
    ghost=null; //④
    Ghost.removeInstance(name); //⑤
    System.gc();  //⑥
    //把CPU让给垃圾回收线程
    Thread.sleep(3000);  //⑦
    ghost=Ghost.getInstance("IAmBack");  //⑧
    System.out.println(ghost);  //⑨

  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
