import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ListDemo extends JFrame {
  private String[] cities = {
    "北京", "上海", "南京", "深圳",
    "济南", "沈阳", "苏州", "杭州","常州"
  };

  private JList list = new JList(cities);
  private JTextArea textArea =new JTextArea(5, 20);

  private ListSelectionListener listener =
    new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) return;
        textArea.setText("");
        Object[] items=list.getSelectedValues();
        for(int i = 0; i < items.length; i++)
          textArea.append(items[i] + "\n");
      }
    };

  public ListDemo(String title) {
    super(title);
    textArea.setEditable(false);
    list.setVisibleRowCount(5);  //在界面上显示5个选项
    Container contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout());

    contentPane.add(textArea);
    contentPane.add(new JScrollPane(list)); //带滚动条
    list.addListSelectionListener(listener);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  public static void main(String[] args) {
     new ListDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
