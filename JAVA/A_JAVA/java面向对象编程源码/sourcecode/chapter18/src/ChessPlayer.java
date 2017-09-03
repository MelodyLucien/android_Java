import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ChessPanel extends JPanel{
  private int space=20; //网格间的距离
  private int grids=30; //棋盘的网格数
  private int radius=space/2; //棋的半径

  //当chesses[i][j]=0,表示网格节点(i,j)上无棋
  //当chesses[i][j]=1,表示网格节点(i,j)上放白棋
  //当chesses[i][j]=2,表示网格节点(i,j)上放黑棋
  private int[][] chesses=new int[grids+1][grids+1];
  private int currColor=1; //当前棋的颜色

  private JMenuBar chessMenuBar=new JMenuBar();
  private JMenu optMenu=new JMenu("操作");
  private JMenuItem startMenuItem=new JMenuItem("开始");
  private JMenuItem exitMenuItem=new JMenuItem("退出");

  private ActionListener startHandler=new ActionListener(){
    public void actionPerformed(ActionEvent e){
       clearGrids();  //清空棋盘
       currColor=1;
       repaint(); //刷新图形
    }
  };
  private ActionListener exitHandler=new ActionListener(){
    public void actionPerformed(ActionEvent e){
      System.exit(0);
    }
  };

   private MouseListener playChessHandler=new MouseAdapter(){
    public void mouseClicked(MouseEvent e){
      int x=e.getX();
      int y=e.getY();
      //放一颗棋子
      if(x<=grids*space && x>=0 && y<=grids*space && y>=0)
        if(chesses[round(x)][round(y)]==0){
          chesses[round(x)][round(y)]=currColor;
          currColor=currColor==1?2:1;  //切换棋子的颜色
          repaint();  //刷新图形
        }
    }
  };

  public int round(float a){  //获得接近a的网格节点坐标
    float f=a/space;
    return Math.round(f);
  }
  public ChessPanel(int space,int grids){
    this.space=space;
    this.grids=grids;
    this.radius=space/2;

    setBackground(Color.YELLOW);
    setSize(space*grids,space*grids);
    startMenuItem.addActionListener(startHandler);
    exitMenuItem.addActionListener(exitHandler);
    addMouseListener(playChessHandler);

    chessMenuBar.add(optMenu);
    optMenu.add(startMenuItem);
    optMenu.add(exitMenuItem);
   }

  public JMenuBar getMenuBar(){
    return chessMenuBar;
  }
  /** 画一颗棋子 */
  private void drawChess(Graphics g,int x,int y,int color){
    g.setColor(color==1?Color.WHITE:Color.BLACK);
    g.fillOval(x*space-radius,y*space-radius,radius*2,radius*2);
  }
  /**　画网格　*/
  private void drawGrids(Graphics g){
    g.setColor(Color.DARK_GRAY);
    for(int i=0;i<=grids;i++){
      g.drawLine(0,i*space,grids*space,i*space);
      g.drawLine(i*space,0,i*space,grids*space);
    }
  }

  /** 清空棋盘 */
  private void clearGrids(){
     for(int i=0;i<=grids;i++)
      for(int j=0;j<=grids;j++)
        chesses[i][j]=0;
  }

  public void paintComponent(Graphics g){  //覆盖paintComponent()方法
    super.paintComponent(g); //必须先调用父类的方法

    drawGrids(g);  //画网格
    for(int i=0;i<=grids;i++)
      for(int j=0;j<=grids;j++)
        if(chesses[i][j]!=0)
          drawChess(g,i,j,chesses[i][j]);  //画棋子
  }
}

public class ChessPlayer extends JFrame {
  private ChessPanel chessPanel=new ChessPanel(20,30);

  public ChessPlayer(String title) {
    super(title);

    Container contentPane = getContentPane();
    contentPane.add(chessPanel);
    setJMenuBar(chessPanel.getMenuBar());

    setSize(600,600);
    setVisible( true );
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  }

  public static void main( String args[] ){
      new ChessPlayer("五子棋/围棋");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
