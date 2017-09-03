import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class TextAreaDemo extends JFrame {
  JTextArea area1=new JTextArea(5,10);  //创建5行10列的文本区域
  JTextArea area2=new JTextArea(5,10);  //创建5行10列的文本区域

  //在垂直方向总是显示滚动条，在水平方向只有当需要的时候才显示滚动条
JScrollPane pane1=new JScrollPane(area1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                               JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  
  //panel2按默认方式创建，在垂直和水平方向都只有当需要的时候才显示滚动条
  JScrollPane pane2=new JScrollPane(area2);
  JButton copyButton=new JButton("Copy");
  public TextAreaDemo(String title) {
    super(title);
    copyButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        //把第一个文本区域中被选中的文本拷贝到第二个文本区域中
        area2.setText(area1.getSelectedText());
      }
    });
    Container contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout());
    contentPane.add(pane1);
    contentPane.add(copyButton);
    contentPane.add(pane2);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);

  }
  public static void main(String[] args) {
   new TextAreaDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
