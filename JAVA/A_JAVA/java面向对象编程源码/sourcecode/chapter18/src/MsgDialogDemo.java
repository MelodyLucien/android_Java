import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class MsgDialogDemo extends JFrame {
  private JButton[] b = {
    new JButton("Alert"), new JButton("Yes/No"),
    new JButton("Color"), new JButton("Input"),
    new JButton("Fruit")
  };
  private JTextField txt = new JTextField(15);
  
  private ActionListener al = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      String id = ((JButton)e.getSource()).getText();
      if(id.equals("Alert"))
        JOptionPane.showMessageDialog(null,
          "你的文件中可能有病毒!", "警告",
          JOptionPane.ERROR_MESSAGE);

      else if(id.equals("Yes/No")){
        int sel=JOptionPane.showConfirmDialog(null,
          "需要保存文件吗", "提示",
          JOptionPane.YES_NO_OPTION);

        txt.setText(sel==JOptionPane.YES_OPTION?"需要保存文件":"不需要保存文件");

      }else if(id.equals("Color")) {
        Object[] options = { "Red", "Green" ,"Blue"};
        int sel = JOptionPane.showOptionDialog(
          null, "请选择你最喜欢的颜色!", "选择颜色",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.WARNING_MESSAGE, null,
          options, options[0]);
        if(sel != JOptionPane.CLOSED_OPTION)
          txt.setText("你选择的颜色是: " + options[sel]);

      } else if(id.equals("Input")) {
        String val = JOptionPane.showInputDialog(
            "你还有什么话要说?");
        txt.setText(val);

      }else if(id.equals("Fruit")) {
        Object[] selections = {"Apple", "Orange", "Banana"};
        Object val = JOptionPane.showInputDialog(
          null, "请选择你最喜欢的水果!", "选择水果",
          JOptionPane.INFORMATION_MESSAGE,
          null, selections, selections[0]);
        if(val != null)
          txt.setText(val.toString());
      }
    }
  };
  public MsgDialogDemo(String title) {
    super(title);
    Container contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout());
    for(int i = 0; i < b.length; i++) {
      b[i].addActionListener(al);
      contentPane.add(b[i]);
    }
    contentPane.add(txt);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  public static void main(String[] args) {
     new MsgDialogDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
