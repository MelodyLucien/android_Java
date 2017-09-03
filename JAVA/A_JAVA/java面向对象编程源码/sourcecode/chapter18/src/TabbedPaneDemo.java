import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class TabbedPaneDemo extends JFrame {
  private String[] colorNames = {
    "red", "blue", "green", "black",
    "yellow", "pink", "white"};
  private Color[] colors = {
    Color.RED, Color.BLUE, Color.GREEN, Color.BLACK,
    Color.YELLOW, Color.PINK, Color.WHITE};
    private JTabbedPane tabs = new JTabbedPane();
   //private JTabbedPane tabs = new JTabbedPane(JTabbedPane.BOTTOM,
   //                                     JTabbedPane.SCROLL_TAB_LAYOUT );

  private JTextField txt = new JTextField(20);

  public TabbedPaneDemo(String title) {
    super(title);
    for(int i = 0; i < colors.length; i++){
      JPanel panel=new JPanel();
      panel.setBackground(colors[i]);
      tabs.addTab(colorNames[i],panel);  //加入一个页面
    }
    tabs.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        txt.setText("Tab selected: " +
          tabs.getSelectedIndex());
      }
    });
    Container contentPane = getContentPane();
    contentPane.add(BorderLayout.SOUTH, txt);
    contentPane.add(tabs);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  public static void main(String[] args) {
     new TabbedPaneDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
