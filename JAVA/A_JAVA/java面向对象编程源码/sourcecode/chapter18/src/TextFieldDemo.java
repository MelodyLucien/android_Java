import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
public class TextFieldDemo extends JFrame {
  private JButton
    b1 = new JButton("输入文本"),
    b2 = new JButton("拷贝");
  private JTextField
    t1 = new JTextField(30),
    t2 = new JTextField(30),
    t3 = new JTextField(30);
  private String str = new String(); //存放用户当前选择的文本
  private UpperCaseDocument ucd = new UpperCaseDocument();

  public TextFieldDemo(String title) {
    super(title);
    b1.addActionListener(new ActionListenerB1());
    b2.addActionListener(new ActionListenerB2());

    t1.setDocument(ucd); //JTextField与UpperCaseDocument关联
    //当文本框中的内容发生变化时触发DocumentEvent事件
    ucd.addDocumentListener(new DocumentListenerT());

    //当用户在文本框内输入回车触发ActionEvent事件
    t1.addActionListener(new ActionListenerT());

    Container contentPane = getContentPane();
    contentPane.setLayout(new GridLayout(4,1));
    JPanel buttonPanel=new JPanel();
    buttonPanel.add(b1);
    buttonPanel.add(b2);
    contentPane.add(buttonPanel);
    contentPane.add(t1);
    contentPane.add(t2);
    contentPane.add(t3);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);

  }
  class DocumentListenerT implements DocumentListener {  //监听第一个JTextField的文档事件
    public void changedUpdate(DocumentEvent e) {}
    public void insertUpdate(DocumentEvent e) {
      t2.setText(t1.getText());
      t3.setText("Text: "+ t1.getText());
    }
    public void removeUpdate(DocumentEvent e) {
      t2.setText(t1.getText());
    }
  }
  class ActionListenerT implements ActionListener {  //监听第一个JTextField的ActionEvent事件
    private int count = 0;
    public void actionPerformed(ActionEvent e) {
      t3.setText("t1 Action Event " + count++);
    }
  }
  class ActionListenerB1 implements ActionListener {  //监听第一个JButton
    public void actionPerformed(ActionEvent e) {
       t1.setEditable(true);  //使TextField可以被编辑
    }
  }
  class ActionListenerB2 implements ActionListener {  //监听第二个JButton
    public void actionPerformed(ActionEvent e) {
      if(t1.getSelectedText() == null)
        str = t1.getText();
      else
        str = t1.getSelectedText();

      ucd.setUpperCase(false);
      t1.setText("Inserted by Button 2: " + str);
      ucd.setUpperCase(true);
      t1.setEditable(false);  //使TextField不可以被编辑
    }
  }
  public static void main(String[] args) {
   new TextFieldDemo("Hello");
  }
}


class UpperCaseDocument extends PlainDocument {
  private boolean upperCase = true;
  public void setUpperCase(boolean flag) {
    upperCase = flag;
  }
  public void insertString(int offset, String str, AttributeSet attSet)
                          throws BadLocationException {
    if(upperCase) str = str.toUpperCase();
    super.insertString(offset, str, attSet);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
