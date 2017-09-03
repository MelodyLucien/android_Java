import java.awt.*;
import java.awt.event.*;
public class AdapterDemo extends Frame{
  private Dialog dialog;
  private Label label;

  public AdapterDemo(String title){
    super(title);

    dialog=new Dialog(this,"Note",true);
    label=new Label();
    dialog.add(label);
    dialog.setSize(100,100);

    dialog.addWindowListener(new WindowAdapter(){
    public void windowClosing(WindowEvent evt){
      dialog.setVisible(false);  //隐藏会话框
    }
    });

    requestFocus();  //使Frame能够接收用户的键盘输入

    addKeyListener(new KeyAdapter(){
      public void keyTyped(KeyEvent ev){
        char key=ev.getKeyChar();  
        label.setText("用户选择的键为: " +key);
        dialog.setVisible(true);  //显示会话框
      }
    });

    addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent evt){
         System.exit(0);  //结束程序
      }
    });

    setSize(300,300);
    setVisible(true);
  }

  public static void main(String args[]){
    new AdapterDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
