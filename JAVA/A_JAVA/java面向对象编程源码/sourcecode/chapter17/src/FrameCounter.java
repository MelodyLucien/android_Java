import java.awt.*;
import java.awt.event.*;
public class FrameCounter extends Frame implements ActionListener{
  private Button button=new Button("1");

  public FrameCounter(String title){
    super(title);
    //把FrameCounter本身的实例注册为Button的监听器
    button.addActionListener(this);

    add(button);
    setSize(100,100);
    setVisible(true);
  }

  /** 实现ActionListener的actionPerformed()方法 */
  public void actionPerformed(ActionEvent evt){
     int count=Integer.parseInt(button.getLabel());
     button.setLabel(new Integer(++count).toString());  //把Button上的标号加1
  }

  public static void main(String args[]){
    new FrameCounter("hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
