import java.awt.*;
import java.awt.event.*;
public class ColorChanger extends Frame {
  private Color color=Color.RED;
  private int times; //跟踪调用update()方法的次数
  public ColorChanger(String title){
    super(title);
    Button button=new Button("change color");
    button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        color=(color==Color.RED)?Color.GREEN:Color.RED;  //更换画笔的颜色
        repaint();  //刷新组件
      }
    });

    add(button,BorderLayout.NORTH);
    setSize(300,300);
    setVisible(true);

  }
  public void update(Graphics g){
     super.update(g);
     //跟踪update()方法被调用的次数
     System.out.println("call update "+(++times)+" times");
  }

  public void paint(Graphics g){
    g.setColor(color);  //设置画笔的颜色
    g.fillRect(0,0,300,300);  //画一个矩形
  }
  public static void main(String args[]){
    new ColorChanger("hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
