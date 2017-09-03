import java.awt.*;
import java.awt.event.*;
public class CardLayoutDemo extends Frame{
  private final String names[]={"white","red","yellow"};
  private final Color colors[]={Color.WHITE,Color.RED,Color.YELLOW};
  private Button[] buttons=new Button[3];
  private Panel northPanel=new Panel();
  private Panel centerPanel=new Panel();
  private Panel[] cardPanels=new Panel[3];
  private GridLayout gridLayout=new GridLayout(1,3);
  private CardLayout cardLayout=new CardLayout();

  ActionListener listener=new ActionListener(){
    public void actionPerformed(ActionEvent event){
       Button button=(Button)event.getSource();
       cardLayout.show(centerPanel,button.getLabel());  //显示相应的卡
    }
  };

  public CardLayoutDemo (String title){
    super(title);

    northPanel.setLayout(gridLayout);
    centerPanel.setLayout(cardLayout);
    for(int i=0;i<buttons.length;i++){
      buttons[i]=new Button(names[i]);
      buttons[i].addActionListener(listener);
      northPanel.add(buttons[i]);

      cardPanels[i]=new Panel();
      cardPanels[i].setBackground(colors[i]);
      centerPanel.add(cardPanels[i],names[i]);  //向centerPanel加入cardPanel
    }

    add(northPanel,BorderLayout.NORTH);
    add(centerPanel,BorderLayout.CENTER);
    setSize(250,250);
    setVisible(true);
  }
  public static void main(String args[]){
     new CardLayoutDemo("Hello");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
