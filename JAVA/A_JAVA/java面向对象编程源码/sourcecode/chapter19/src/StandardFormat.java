import java.util.*;
import java.text.*;
public class StandardFormat{
  public static void main(String[] args) {
    Date date = new Date();

    DateFormat shortDateFormat =
      DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);

    DateFormat mediumDateFormat =
      DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);

    DateFormat longDateFormat =
      DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);

    DateFormat fullDateFormat =
      DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);

    System.out.println(shortDateFormat.format(date));
    System.out.println(mediumDateFormat.format(date));
    System.out.println(longDateFormat.format(date));
    System.out.println(fullDateFormat.format(date));
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
