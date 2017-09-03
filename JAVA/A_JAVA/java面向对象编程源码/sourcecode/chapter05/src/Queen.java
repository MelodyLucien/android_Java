public class Queen {
  private final int size;  //棋盘的大小，也表示皇后的数目
  private int[] location;  //皇后在棋盘的每行上的列的位置
  private int[] colsOccupied;  //皇后在棋盘上占据的列
  private int[] cross1Occupied;  //皇后在棋盘上占据的正对角线
  private int[] cross2Occupied;  //皇后在棋盘上占据的反对角线
  private static int count;  //解决方案的个数

  private static final int STATUS_OCCUPIED=1;  //占领状态
  private static final int STATUS_OCCUPY_CANCELED=0;  //未占领状态

  public Queen(int size){
     //初始化
     this.size=size;
     location=new int[size];
     colsOccupied=new int[size];
     cross1Occupied=new int[2*size];
     cross2Occupied=new int[2*size];
  }

  public void printLocation(){
      System.out.println("以下是皇后在棋盘上的第"+count+"种摆放位置");
      for(int i=0;i<size;i++)
        System.out.println("行:"+i+"  列:"+location[i]);
  }
  
  /** 判断位置(i,j)是否被占领 */
  private boolean isOccupied(int i,int j){
    return (colsOccupied[j]==1)
          || (cross1Occupied[i-j+size-1]==1)
          || (cross2Occupied[i+j]==1);
  }
  
  /** 如果参数flag为1，表示占领位置(i,j)；
   *  如果参数flag为0，表示取消占领位置(i,j)
   */
  private void setStatus(int i,int j,int flag){
    colsOccupied[j]=flag;  //宣布占领或取消占领第j列
    cross1Occupied[i-j+size-1]=flag;  //宣布占领或取消占领正对角线
    cross2Occupied[i+j]=flag;  //宣布占领或取消占领反对角线
  }
    
  /** 从第i行开始摆放皇后 */
  public void place(int i){
    for(int j=0;j<size;j++)  //在第i行，分别尝试把皇后放在每一列上
      if(!isOccupied(i,j)){  //判断该位置是否被占据
          location[i]=j;  //摆放皇后，在第i行把皇后放在第j列
          setStatus(i,j,STATUS_OCCUPIED);  //宣布占领(i,j)位置
          if( i<size-1)
            place(i+1);  //如果所有皇后没有摆完，递归摆放下一行的皇后
          else{
            count++;   //统计解决方案的个数
            printLocation();  //完成任务，打印所有皇后的位置
          }
          //回溯，撤销占领位置(i,j)
          setStatus(i,j,STATUS_OCCUPY_CANCELED);
     }
  }
  
  public void start(){
    place(0);  //从第0行开始放置皇后
  }
  
  public static void main(String args[]){
    new Queen(8).start();
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
