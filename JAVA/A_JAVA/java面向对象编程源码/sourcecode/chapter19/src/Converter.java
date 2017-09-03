public class Converter{
  public static void main(String[] args) {
    toByte("123");  //打印123
    toDouble("123.11D");  //打印123.11
    toByte("123D");  //抛出NumberFormatException
    toDouble("three");  //抛出NumberFormatException
  }

  public static void toByte(String s){
    try{
      System.out.println(new Byte(s));
    }catch(Exception e){e.printStackTrace();}
  }

  public static void toDouble(String s){
    try{
      System.out.println(new Double(s));
    }catch(Exception e){e.printStackTrace();}
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
