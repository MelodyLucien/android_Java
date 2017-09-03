package visitcontrol;

class Outer{
  public class InnerTool{  //内部类
    public int add(int a,int b){
      return a+b;
    }
  }

  private InnerTool tool=new InnerTool();

  public void add(int a,int b,int c){
    tool.add(tool.add(a,b),c);
  }
}

public class Tester{
  public static void main(String args[]){
    Outer o=new Outer();
    o.add(1,2,3);
    Outer.InnerTool tool=new Outer().new InnerTool();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
