import java.util.*;
public final class Schedule {
  private final Date start;
  private final Date end;
  public Schedule(Date start,Date end){
    //不允许放假日期在开学日期的前面
    if(start.compareTo(end)>0)throw new IllegalArgumentException(start +" after " +end);
     this.start=new Date(start.getTime());  //采用保护性拷贝
     this.end=new Date(end.getTime());   //采用保护性拷贝
  }
  public Date getStart(){return (Date)start.clone();}  //采用保护性拷贝
  public Date getEnd(){return (Date)end.clone();}  //采用保护性拷贝

  public static void main(String args[]){
    Calendar c= Calendar.getInstance();
    c.set(2006,9,1);
    Date start=c.getTime();
    c.set(2007,1,25);
    Date end=c.getTime();
    Schedule s=new Schedule(start,end);

    end.setTime(System.currentTimeMillis());  //修改放假时间
    start=s.getStart();
    start.setTime(System.currentTimeMillis());  //修改开学时间
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
