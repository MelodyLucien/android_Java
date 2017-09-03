public class ArraySorter{
  /** 冒泡排序 */
  public static void bubbleSort(int[] array){
    for(int i=0;i<array.length-1;i++){  //外层循环
      for(int j=0;j<array.length-i-1;j++){  //内层循环
        if(array[j]>array[j+1]){
          int temp=array[j];
          array[j]=array[j+1];
          array[j+1]=temp;
        }
      }
      print(i+1,array);
    }
  }

  public static void print(int time,int[] array){
      System.out.print("第"+time+"趟排序：");
      for(int i=0;i<array.length;i++)
        System.out.print(array[i]+"  ");

      System.out.println();  //换行
  }

  public static void main(String args[]){
    int[] array={4,7,5,3,9,0};
    bubbleSort(array);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
