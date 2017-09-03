import java.awt.*;
public class SimpleFrame{
  public static void main(String args[]){
    Frame f=new Frame("hello");
    f.add(new Button("Press Me"));
    f.setSize(100,100);  //设置Frame的宽和高
    f.setVisible(true);   //使Frame变为可见
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
