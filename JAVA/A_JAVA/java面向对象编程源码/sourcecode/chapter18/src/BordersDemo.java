import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class BordersDemo extends JFrame{

  /** 按照参数指定的边框创建一个JPanel对象 */
  public static JPanel getPanelWithBorder(Border b) {
    JPanel jp = new JPanel();
    jp.setLayout(new BorderLayout());
    String nm = b.getClass().toString();
    nm = nm.substring(nm.lastIndexOf('.') + 1);
    //在JPanel中央有一个JLabel，它显示边框类的名字
    jp.add(new JLabel(nm, JLabel.CENTER),BorderLayout.CENTER);
    jp.setBorder(b);  //设置JPanel的边框
    return jp;
  }

  public BordersDemo(String title) {
    super(title);
    Container contentPane = getContentPane();
    contentPane.setLayout(new GridLayout(2,4));
    contentPane.add(getPanelWithBorder (new TitledBorder("Title")));
    contentPane.add(getPanelWithBorder (new EtchedBorder()));
    contentPane.add(getPanelWithBorder (new LineBorder(Color.BLUE)));
    contentPane.add(getPanelWithBorder(
      new MatteBorder(5,5,30,30,Color.PINK)));
    contentPane.add(getPanelWithBorder (
      new BevelBorder(BevelBorder.RAISED)));
    contentPane.add(getPanelWithBorder (
      new SoftBevelBorder(BevelBorder.LOWERED)));
    contentPane.add(getPanelWithBorder(new CompoundBorder(
      new EtchedBorder(),
      new LineBorder(Color.BLUE))));

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);

  }
  public static void main(String[] args) {
   new BordersDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
