import java.awt.*;
import java.awt.event.*;
public class GridLayoutDemo extends Frame{
  private final String names[]={"one","two","three","four","five","six"};
  private Button[] buttons=new Button[6];
  private boolean flag=true;
  final GridLayout layout1=new GridLayout(2,3,5,10);
  final GridLayout layout2=new GridLayout(3,2);

  public GridLayoutDemo(String title){
    super(title);
    setLayout();
    ActionListener listener=new ActionListener(){
        public void actionPerformed(ActionEvent event){
           setLayout();  //切换网格布局
           GridLayoutDemo.this.validate();  //使新的布局生效
         }
    };

    for(int i=0;i<buttons.length;i++){
      buttons[i]=new Button(names[i]);
      buttons[i].addActionListener(listener);
      add(buttons[i]);
    }

    setSize(250,250);
    setVisible(true);
  }

  private void setLayout(){
    if(flag)
      setLayout(layout1);
    else
      setLayout(layout2);

    flag=!flag;
  }
  public static void main(String args[]){
     new GridLayoutDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
