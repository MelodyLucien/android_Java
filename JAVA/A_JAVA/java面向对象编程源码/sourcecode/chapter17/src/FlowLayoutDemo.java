import java.awt.*;
import java.awt.event.*;
public class FlowLayoutDemo{
  public static void main(String args[]){
    final Frame f=new Frame("hello");
    final FlowLayout fl=new FlowLayout();
    
    f.setLayout(fl);  //使Frame采用FlowLayout布局

    Button leftButton=new Button("left");
    leftButton.addActionListener(new ActionListener(){  //注册事件监听器
       public void actionPerformed(ActionEvent event){
         fl.setAlignment(FlowLayout.LEFT);  //左对齐
         fl.layoutContainer(f);  //使Frame重新布局
       }
    });
    Button centerButton=new Button("center");
    centerButton.addActionListener(new ActionListener(){  //注册事件监听器
       public void actionPerformed(ActionEvent event){
         fl.setAlignment(FlowLayout.CENTER);  //居中对齐
         fl.layoutContainer(f);  //使Frame重新布局
       }
    });
    Button rightButton=new Button("right");
    rightButton.addActionListener(new ActionListener(){  //注册事件监听器
       public void actionPerformed(ActionEvent event){
         fl.setAlignment(FlowLayout.RIGHT);  //右对齐
         fl.layoutContainer(f); //使Frame重新布局
       }
    });
    
    f.add(leftButton);
    f.add(centerButton);
    f.add(rightButton);
    f.setSize(200,100);
    f.setVisible(true);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
