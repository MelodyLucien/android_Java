import java.awt.*;
public class ManualLayout {
  public static void main(String args[]){  
    Frame f=new Frame("hello");
    f.setLayout(null);  //取消布局管理器
    f.setSize(300,100);  //宽300，高100
    Button b=new Button("press me");
    b.setSize(100,30);  //宽100，高30
    b.setLocation(40,60); //x坐标40,y坐标60
    f.add(b);
    f.setVisible(true);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
