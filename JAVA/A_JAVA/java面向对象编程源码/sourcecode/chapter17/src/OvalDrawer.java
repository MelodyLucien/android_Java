import java.awt.*;
import java.awt.event.*;
public class OvalDrawer extends Frame implements Runnable{
  private Color[] colors={Color.RED,Color.BLACK,Color.BLUE,
Color.GREEN,Color.DARK_GRAY};
  private Color color;
  private int x=10,y=10,width=10,height=10;

  public OvalDrawer(String title){
    super(title);
    setSize(300,300);
    setVisible(true);
    new Thread(this).start();
  }

  public void run(){
    while(true){
      x=(int)(Math.random()*300);
      y=(int)(Math.random()*300);
      width=(int)(Math.random()*100);
      height=(int)(Math.random()*100);
      color=colors[(int)(Math.random()*(colors.length-1))];
      repaint();
      try{Thread.sleep(400);}catch(InterruptedException e){throw new RuntimeException(e);}
    }
  }

  /*  
  public void update(Graphics g){
    paint(g);
  }
  */
  public void paint(Graphics g){
    g.setColor(color);
    g.fillOval(x,y,width,height);  //画椭圆
  }
  public static void main(String args[]){
    new OvalDrawer("hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
