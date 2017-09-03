import java.util.*;
import java.text.*;
public class DateParser{
  public static Date parseDate(String text,String format){
    try{
      return new SimpleDateFormat(format).parse(text);
    }catch(ParseException e){throw new RuntimeException(e.getMessage());}
  }

  public static void main(String[] args) {
    Date d1=parseDate("04-23-2006","MM-dd-yyyy");
    System.out.println(d1);

    d1=parseDate("2006/11/25 10:53:54","yyyy/MM/dd hh:mm:ss");
    System.out.println(d1);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
