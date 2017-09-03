import java.util.*;
public class BitSetTester {
  static final int BYTE_SIZE=8;
  static final int SHORT_SIZE=16;
  static final int INT_SIZE=32;
  static final int LONG_SIZE=64;

  private static BitSet toBitSet(long data,int size){
    BitSet bs=new BitSet();
    for(int i=size-1;i>=0;i--)
      if((((long)1<<i)&data)!=0)  //获得每个二进制位
        bs.set(i);
      else
        bs.clear(i);
    return bs;
  }
  public static BitSet byteToBitSet(byte data){
    return toBitSet(data,BYTE_SIZE);
  }
  public static BitSet shortToBitSet(short data){
     return toBitSet(data,SHORT_SIZE);
  }
  public static BitSet intToBitSet(int data){
     return toBitSet(data,INT_SIZE);
  }
  public static BitSet longToBitSet(long data){
     return toBitSet(data,LONG_SIZE);
  }
  public static void printBitSet(BitSet bs){
   printBitSet(bs,bs.size());
  }
  public static void printBitSet(BitSet bs,int size){
    String bits=new String();
    for(int i=0;i<size;i++){
      bits=(bs.get(i)?1:0)+bits;
      if((i+1)%8==0)bits=" "+bits;
    }
    System.out.println("bits:"+bits);
  }

  public static void main(String args[]){
   byte a=-125;
   short b=-125;
   int c=-125;
   long d=-125;
   printBitSet(byteToBitSet(a),BYTE_SIZE);
   printBitSet(shortToBitSet(b),SHORT_SIZE);
   printBitSet(intToBitSet(c),INT_SIZE);
   printBitSet(longToBitSet(d),LONG_SIZE);

   a=125;
   b=125;
   c=125;
   d=125;
   printBitSet(byteToBitSet(a));
   printBitSet(shortToBitSet(b));
   printBitSet(intToBitSet(c));
   printBitSet(longToBitSet(d));
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
