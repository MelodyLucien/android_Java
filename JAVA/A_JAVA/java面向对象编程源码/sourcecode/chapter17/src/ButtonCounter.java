import java.awt.*;
import java.awt.event.*;
public class ButtonCounter extends Frame{
  private Button button=new Button("1");
  
  public ButtonCounter(String title){
    super(title);
    //为Button注册ActionEvent的监听器
    button.addActionListener(new ActionListener(){  //定义一个内部类
        public void actionPerformed(ActionEvent evt){
          int count=Integer.parseInt(button.getLabel());
          button.setLabel(new Integer(++count).toString());  //把Button的标号加1
        }
      }
    );

    add(button);
    setSize(100,100);
    setVisible(true);
  }
  public static void main(String args[]){
    new ButtonCounter("hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
