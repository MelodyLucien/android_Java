public class ArrayFinder{
  public static void print(int[] array,int middle){
      for(int i=0;i<array.length;i++){
        System.out.print(array[i]);
        if(i==middle)System.out.print("*");
        System.out.print("  ");
      }

      System.out.println();
  }

  /** 采用二叉查找算法 */
  public static int indexOf(int[] array, int value){
   int low=0;
   int high=array.length-1;
   int middle;

   while(low<high){
     middle=(low+high)/2;  //计算中间元素的索引
     print(array,middle);  //打印数组，用于跟踪查找过程
     if(array[middle]==value)return middle;

     if(value<array[middle])
       high=middle;
     else
     low=middle;
   }
   return -1;  //没有找到该元素，返回-1
  }

  public static void main(String args[]){
    int[] array={4,5,6,7,9,13,17};
    System.out.println("location of 13: "+indexOf(array,13));  //查找13在数组中的位置
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
