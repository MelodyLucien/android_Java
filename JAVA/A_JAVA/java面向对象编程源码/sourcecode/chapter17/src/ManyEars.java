import java.awt.*;
import java.awt.event.*;
public class ManyEars extends Frame{
  private Button button=new Button("test");
  private TextArea textArea=new TextArea(40,20);

  public ManyEars (String title){
    super(title);

    // Button注册1个MouseListener监听器和5个ActionListener监听器
    for(int i=0;i<5;i++)button.addActionListener(new ListenerA());
    button.addMouseListener(new ListenerB());

    add(button,BorderLayout.NORTH);
    add(textArea,BorderLayout.CENTER);
    pack();
    setVisible(true);
  }

  private void print(String str){
    textArea.setText(textArea.getText()+str+"\n");
  }

  private static int count;  //统计ListenerA的实例的数目
  class ListenerA implements ActionListener{
    private int serialNumber;
    public ListenerA(){count++;serialNumber=count;}
    public void actionPerformed(ActionEvent evt){
      print("ListenerA"+serialNumber+" receives ActionEvent.");
     }
  }

  class ListenerB extends MouseAdapter{
    public void mousePressed(MouseEvent evt){
      //获得所有的向Button注册的ActionListener
      ActionListener[] listeners=(ActionListener[])button.getListeners(ActionListener.class);
      print("ListenerB receives MouseEvent.");
      //从Button中注销一个ActionListener
      if(listeners.length>0)button.removeActionListener(listeners[0]);
      print("There are "+(listeners.length>0?listeners.length-1:0)+" ListenerA.");
    }
  }

  public static void main(String args[]){
    new ManyEars("hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
