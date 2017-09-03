public class Swaper {

  /** 交换array1数组与array2数组的内容 */
  public static void change(int[] array1, int[] array2){
    for(int i=0;i<array1.length;i++){
      int temp=array1[i];
      array1[i]=array2[i];
      array2[i]=temp;
    }
  }

  /** 交换变量a与变量b的内容 */
  public static void change(int a,int b){
    int temp=a;
    a=b;
    b=temp;
  }

  /** 获得字符串中的所有字符  */
  public static char[] getChars(String str){
    if(str==null)
      return new char[0];  //返回长度为0的数组

    char[] result=new char[str.length()];
    for(int i=0;i<str.length();i++)
      result[i]=str.charAt(i);

    return result;
  }

  public static void main(String args[]){
    int[] array1={1,3,5,7},array2={2,4,6,8};
    
    change(array1,array2);  //把数组array1的内容与数组array2交换
    for(int i=0;i<array2.length;i++)
      System.out.println("array2["+i+"]="+array2[i]);

    change(array2[1],array2[2]);
    System.out.println("array2[1]="+array2[1]);
    System.out.println("array2[2]="+array2[2]);

    char[] chars=getChars("Hello"); //获得字符串“Hello”中的所有字符
    for(int i=0;i<chars.length;i++)
      System.out.print(chars[i]);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
