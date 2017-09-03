import java.awt.*;
public class SimplePanel{
  public static void main(String args[]){
    Frame f=new Frame("hello");
    Panel p=new Panel();
    p.add(new Button("press me"));
    p.setBackground(Color.YELLOW);  //设置Panel的背景颜色
    f.add(p);  //把Panel加入到Frame中
    f.setSize(100,100);
    f.setBackground(Color.BLUE);  //设置Frame的背景颜色
    f.setVisible(true);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
