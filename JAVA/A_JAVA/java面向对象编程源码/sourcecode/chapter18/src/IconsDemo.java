import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class IconsDemo extends JFrame {
  private static Icon[] icons;
  private JButton jb1, jb2 = new JButton("Disable");
  private boolean flag = false;
  public IconsDemo(String title) {
    super(title);
    icons = new Icon[] {
      new ImageIcon(getClass().getResource("image0.jpg")),
      new ImageIcon(getClass().getResource("image1.jpg")),
      new ImageIcon(getClass().getResource("image2.jpg")),
      new ImageIcon(getClass().getResource("image3.jpg")),
      new ImageIcon(getClass().getResource("image4.jpg")),
    };
    jb1 = new JButton("Pet", icons[0]);
    Container contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout());

    jb1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(flag) {
          jb1.setIcon(icons[0]);
          flag = false;
        } else {
          jb1.setIcon(icons[1]);
          flag = true;
        }
        jb1.setVerticalAlignment(JButton.TOP);
        jb1.setHorizontalAlignment(JButton.LEFT);
      }
    });

    jb1.setRolloverEnabled(true);
    jb1.setRolloverIcon(icons[2]);
    jb1.setPressedIcon(icons[3]);
    jb1.setDisabledIcon(icons[4]);
    jb1.setToolTipText("Click Me!");
    contentPane.add(jb1);
    jb2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(jb1.isEnabled()) {
          jb1.setEnabled(false);  //使按钮失效
          jb2.setText("Enable");
        } else {
          jb1.setEnabled(true);  //使按钮有效
          jb2.setText("Disable");
        }
      }
    });
    contentPane.add(jb2);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  public static void main(String[] args) {
    new IconsDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
