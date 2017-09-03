class Grandpa{
  protected Grandpa(){
    System.out.println("default Grandpa");
  }
  public Grandpa(String name){
    System.out.println(name);
  }
}

class Father extends Grandpa{
  protected Father(){
    System.out.println("default Father");
  }

  public Father(String grandpaName,String fatherName){
    super(grandpaName);
    System.out.println(fatherName);
  }
}

public class Son extends Father{
  public Son(){
    System.out.println("default Son");
  }

  public Son(String grandpaName,String fatherName,String sonName){
    super(grandpaName,fatherName);
    System.out.println(sonName);
  }

  public static void main(String args[]){
    Son s1= new Son("My Grandpa", "My Father", "My Son");  //①
Son s2=new Son();  //②
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
