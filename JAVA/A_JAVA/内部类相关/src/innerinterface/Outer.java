package innerinterface;

public class Outer{
  public static interface Tool{ public int add(int a,int b);}  //静态内部接口

  private Tool tool=new Tool(){public int add(int a,int b){return a+b;}};  //匿名类

  public void add(int a,int b,int c){
    tool.add(tool.add(a,b),c);
  }

  public void setTool(Tool tool){
    this.tool=tool;
}
}

class MyTool implements Outer.Tool{
  public int add(int a,int b){
    int result=a+b;
    System.out.println(result);
    return result;
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
