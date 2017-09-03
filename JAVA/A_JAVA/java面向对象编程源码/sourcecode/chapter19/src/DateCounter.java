import java.util.*;
import java.text.*;
public class DateCounter{
  public static void main(String[] args)throws Exception {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Date d1=dateFormat.parse("2006-03-11");
    Date d2=dateFormat.parse("2006-04-21");
    System.out.println("相差的天数为：" +getPeriod(d2,d1));  //打印“相差的天数为：41”
  }

  //计算两个日期之间相差的天数
  public static long getPeriod(Date d1,Date d2){
    long p=d1.getTime()-d2.getTime();
    return p/(1000*60*60*24);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
