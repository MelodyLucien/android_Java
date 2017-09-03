import java.awt.*;
import java.awt.event.*;
public class BorderLayoutDemo extends Frame{
  private final String names[]={"Hide North","Hide South","Hide East","Hide West","Hide Center"};
  private final String locations[]={BorderLayout.NORTH,BorderLayout.SOUTH,
BorderLayout.EAST,BorderLayout.WEST,BorderLayout.CENTER};
  private Button[] buttons=new Button[5];

  public BorderLayoutDemo(String title){
    super(title);
    final BorderLayout layout=(BorderLayout)this.getLayout();
    ActionListener listener=new ActionListener(){  //事件监听器
        public void actionPerformed(ActionEvent event){
           for(int i=0;i<buttons.length;i++)
             if(event.getSource()==buttons[i])
               buttons[i].setVisible(false);  //隐藏用户选择的按钮
             else
               buttons[i].setVisible(true);
           layout.layoutContainer(BorderLayoutDemo.this);  //重新调整Frame的布局
        }
     };

    for(int i=0;i<buttons.length;i++){
      buttons[i]=new Button(names[i]);
      buttons[i].addActionListener(listener);
      add(buttons[i],locations[i]);
    }
    setSize(250,250);
    setVisible(true);
  }

  public static void main(String args[]){
     new BorderLayoutDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
