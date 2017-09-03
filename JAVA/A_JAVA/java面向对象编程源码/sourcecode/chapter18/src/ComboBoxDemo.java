import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComboBoxDemo extends JFrame {
 private String[] cities = {
    "北京", "上海", "南京", "深圳",
    "济南", "沈阳", "苏州", "杭州","常州"
  };
  private JTextField textField = new JTextField(15);
  private JComboBox comboBox = new JComboBox();
  private JButton button = new JButton("添加更多城市选项");
  private int count = 0;

  public ComboBoxDemo(String title) {
    super(title);
    for(int i = 0; i < 4; i++)
      comboBox.addItem(cities[count++]);

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(count < cities.length)
          comboBox.addItem(cities[count++]);
      }
    });
    comboBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        textField.setText("index: "+ comboBox.getSelectedIndex() + "   " +
        comboBox.getSelectedItem());
      }
    });

    comboBox.setEditable(true);  //使下拉列表可以被编辑
    textField .setEditable(false);
   
Container contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout());
    contentPane.add(comboBox);
    contentPane.add(button);
    contentPane.add(textField);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  public static void main(String[] args) {
     new ComboBoxDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
