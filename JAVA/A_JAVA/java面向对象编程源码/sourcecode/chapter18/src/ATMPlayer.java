import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class ATMPlayer extends JApplet implements Runnable {
  private int index=4;	//图片的索引
  private Thread changer;	//动态改变图片的线程，形成动画效果
  private boolean stopFlag=true;  //控制线程启动与关闭的标志
  private Image[] images; //存放钞票图片
  private Image mouth; //吐钞口
  private JButton contrlButton=new JButton("  开始取款！ ");

  public void init() {

    images=new Image[4];
    
    //作为独立应用程序时通过以下这段代码从本地机器加载图片
    Toolkit tk=getToolkit();
    images[0]=tk.getImage(getClass().getResource("money1.jpg"));
    images[1]=tk.getImage(getClass().getResource("money2.jpg"));
    images[2]=tk.getImage(getClass().getResource("money3.jpg"));
    images[3]=tk.getImage(getClass().getResource("money4.jpg"));
    mouth=tk.getImage(getClass().getResource("mouth.jpg"));
    
/*
    //作为小应用程序时通过以下这段代码从网上下载图片
    images[0]=getImage(getCodeBase(),"money1.jpg");
    images[1]=getImage(getCodeBase(),"money2.jpg");
    images[2]=getImage(getCodeBase(),"money3.jpg");
    images[3]=getImage(getCodeBase(),"money4.jpg");
    mouth=getImage(getCodeBase(),"mouth.jpg");
*/
    contrlButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(stopFlag)start();
        else stop();
    }});

    Container contentPane=getContentPane();
    contentPane.setBackground(Color.WHITE);
    contentPane.add(contrlButton, BorderLayout.NORTH);
  }

  public void start(){
    changer=new Thread(this);
    stopFlag=false;
    index=0;
    contrlButton.setText("  够啦！ ");
    changer.start();
  }

  public void paint(Graphics g) {
    super.paint(g);
    g.drawImage(mouth,100,45,this);
    synchronized(this){if(index<4)g.drawImage(images[index],107,50,this);}
  }

  public void stop(){
    stopFlag=true;
    contrlButton.setText("  开始取款！ ");
  }

  public void run(){
    while(!stopFlag){
      repaint(); //The word will be redrawed with new fontSize
      try{
        if(index==3)Thread.sleep(500);
        else Thread.sleep(200);
      }catch(InterruptedException e){throw new RuntimeException(e);}

      if(++index>4) index=0;   //显示下一张照片
    }

    index=4;
    repaint();  //刷新图形
  }

  public static void main(String args[]){
    JFrame gui=new JFrame("取款机");
    ATMPlayer atm=new ATMPlayer();
    atm.init();

    gui.setContentPane(atm);
    gui.setSize(300,300);
    gui.setVisible( true );
    gui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
