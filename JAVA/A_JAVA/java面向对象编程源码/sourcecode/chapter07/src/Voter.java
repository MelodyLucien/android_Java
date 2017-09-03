import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class Voter{
  private static final int MAX_COUNT=100;  //静态变量，最大投票数，到达此数就停止投票
  private static int count;  //静态变量，投票数
  private static Set<Voter> voters=new HashSet<Voter>();  //静态变量，存放所有已经投票的选名
  private String name;  //实例变量，投票人姓名

  public Voter(String name){this.name=name;}

  /** 投票 */
  public void voteFor(){
    if(count==MAX_COUNT){
      System.out.println("投票活动已经结束");
      return;
    }
    if(voters.contains(this))
      System.out.println(name+":你不允许重复投票！");
    else{
      count++;
      voters.add(this); 
      System.out.println(name+":感谢你投票！");
    }
  }

  /** 打印投票结果  */
  public static void printVoteResult(){
    System.out.println("当前投票数为："+count);

    System.out.println("参与投票的选民名单如下");
    
    for(Voter voter : voters){  //遍历voters集合
      System.out.println(voter.name);
    }
  }

  public static void main(String args[]){
    Voter tom=new Voter("Tom");
    Voter mike=new Voter("Mike");
    Voter jack=new Voter("Jack");

    tom.voteFor();
    tom.voteFor();
    mike.voteFor();
    jack.voteFor();

    Voter.printVoteResult();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
