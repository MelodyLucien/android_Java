import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class ToggleDemo extends JFrame {
  private JTextArea area = new JTextArea(6, 15);
  private JScrollPane paneWithTextArea=new JScrollPane(area);
  private JPanel paneWithButtons=new JPanel();
  private JLabel label1=new JLabel("兴趣：");
  private JCheckBox
    cb1 = new JCheckBox("游泳"),
    cb2 = new JCheckBox("唱歌"),
    cb3 = new JCheckBox("旅游");

  private JLabel label2=new JLabel("性别：");
  private JRadioButton
    rb1 = new JRadioButton("男",true), //默认为选中状态
    rb2 = new JRadioButton("女");
  private ButtonGroup group=new ButtonGroup();

  private String sex="男";  //性别
  private Set<String> hobbies=new HashSet<String>();  //兴趣爱好

  private ActionListener listener1 =new ActionListener(){  //监听JCheckBox触发的ActionEvent
    public void actionPerformed(ActionEvent event){
         JCheckBox cb=(JCheckBox)event.getSource();
         if(cb.isSelected())
           hobbies.add(cb.getText());
         else
           hobbies.remove(cb.getText());
         printStatus();
    }
  };

  private ActionListener listener2 =new ActionListener(){ //监听JRadioButton触发的ActionEvent
    public void actionPerformed(ActionEvent event){
         JRadioButton rb=(JRadioButton)event.getSource();
         sex=rb.getText();
         printStatus();
    }
  };

  public ToggleDemo(String title) {
    super(title);
    area.setEditable(false);
    cb1.addActionListener(listener1);
    cb2.addActionListener(listener1);
    cb3.addActionListener(listener1);
    rb1.addActionListener(listener2);
    rb2.addActionListener(listener2);
    //把JRadioButton加入到一个ButtonGroup中
    group.add(rb1);
    group.add(rb2);

    paneWithButtons.setLayout(new FlowLayout());
    paneWithButtons.add(label1);
    paneWithButtons.add(cb1);
    paneWithButtons.add(cb2);
    paneWithButtons.add(cb3);
    paneWithButtons.add(label2);
    paneWithButtons.add(rb1);
    paneWithButtons.add(rb2);

    Container contentPane = getContentPane();  //默认的布局管理器为BorderLayout

    contentPane.add(paneWithButtons,BorderLayout.NORTH);
    contentPane.add(paneWithTextArea,BorderLayout.CENTER);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }
  private void printStatus(){
    area.append("您的兴趣爱好包括：");
    Iterator<String> it=hobbies.iterator();
    while(it.hasNext())
      area.append(it.next()+" ");
    area.append("  您的性别为："+sex+"\n" );
  }
  public static void main(String[] args) {
     new ToggleDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
