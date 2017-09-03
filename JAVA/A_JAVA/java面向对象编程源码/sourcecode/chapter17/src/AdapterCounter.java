import java.awt.event.*;
import java.awt.*;
public class AdapterCounter extends Frame{
  private Button button=new Button("1");

  public AdapterCounter(String title){
    super(title);
    //把MyMouseListener的实例注册为Button的监听器
    button.addMouseListener(new MyMouseListener(2));

    add(button);
    setSize(100,100);
    setVisible(true);
  }

  public static void main(String args[]){
    new AdapterCounter("hello");
  }
}

class MyMouseListener extends MouseAdapter{
  private int step;  //决定Button上的标号每次增加的步长
  public MyMouseListener(int step){this.step=step;}
  public void mousePressed(MouseEvent evt){
     Button button=(Button)evt.getSource();
     int count=Integer.parseInt(button.getLabel());
     button.setLabel(new Integer(step+count).toString());  //把Button上的标号加step
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
