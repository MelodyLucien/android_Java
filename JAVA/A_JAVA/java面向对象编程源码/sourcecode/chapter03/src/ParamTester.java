public class ParamTester{
  public int memberVariable=0;

  public static void main(String args[]){

    //声明并初始化4个局部变量
    int param1=0; // param1是基本数据类型
    ParamTester param2=new ParamTester(); //param2 是对象引用类型
    ParamTester param3=new ParamTester();//param3 是对象引用类型
    int[] param4={0}; //param4是数组引用类型
		
    //将4个局部变量作为参数传递给changeParameter()方法
    changeParameter(param1, param2, param3, param4);					
    //打印4个局部变量
    System.out.println("param1=" +param1);
    System.out.println("param2.memberVariable="+param2.memberVariable);                 System.out.println("param3.memberVariable=" + param3.memberVariable);
    System.out.println("param4[0]="+param4[0]);
  }

  public static void changeParameter(int param1, ParamTester param2, ParamTester   
                                   param3, int[] param4){
    param1=1; //改变基本数据类型参数的值
    param2.memberVariable=1; //改变对象类型参数的实例变量
    param3=new ParamTester();  //改变对象类型参数的引用，使它引用一个新的对象
    param3.memberVariable=1; //改变新的对象的实例变量
    param4[0]=1; //改变数组类型参数的元素
  }
}

/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 
