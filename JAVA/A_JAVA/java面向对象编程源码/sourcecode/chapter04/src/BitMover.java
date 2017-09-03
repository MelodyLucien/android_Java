public class BitMover{ 
  /** 交换高4位与第4位的位置 */
  public static byte swap(byte b){
    int lowBits=b & 0xF;  //获得低4位
    int highBits=b & 0xF0;  //获得高4位 

    //把低4位左移4位，变成高4位
    //把高4位无符号右移4位，变成低4位
    //再把两者进行位或
    int result= lowBits<<4 | highBits>>>4; 
  
    return (byte)result;  //强制类型转换，截取int数据的后八位
  }

  public static void main(String args[]){
     System.out.println(swap((byte)10));  //打印-96
     System.out.println(swap((byte)1));   //打印16
     System.out.println(swap((byte)-1));  //打印-1
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 
