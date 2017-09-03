import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SimpleSwingDemo extends JFrame implements ActionListener{
  private JLabel jLabel;
  private JButton jButton;
  private String labelPrefix ="Number of button clicks: ";
  private int numClicks = 0;

  public SimpleSwingDemo(String title){
   super(title);
   
   jLabel = new JLabel(labelPrefix + "0");
   jButton = new JButton("I am a Swing button!");

   // 创建一个快捷键: 用户按下Alt-i键等价于点击该Button
   jButton.setMnemonic('i');

   //设置鼠标移动到该Button时的提示信息
   jButton.setToolTipText("Press me");

   jButton.addActionListener(this);

/* //wrong
   setLayout(new GridLayout(2, 1));
   add(jLabel) ;
   add(jButton);
*/
  
   Container contentPane=getContentPane();
   contentPane.setLayout(new GridLayout(2, 1));
   contentPane.add(jLabel) ;
   contentPane.add(jButton);

   pack();
   setVisible(true);

   //当用户选择JFrame的关闭图标，将结束程序
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void actionPerformed(ActionEvent e) {
    numClicks++;
    jLabel.setText(labelPrefix + numClicks);
  }

  public static void main(String[] args) {
    new SimpleSwingDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
