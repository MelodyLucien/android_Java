import javax.swing.*;
import java.awt.*;
public class BoxLayoutDemo extends JFrame {
   public BoxLayoutDemo(String title){
      super( title);

      //创建使用BoxLayout的Box
      Box horizontal1 = Box.createHorizontalBox();
      Box vertical1 = Box.createVerticalBox();
      Box horizontal2 = Box.createHorizontalBox();
      Box vertical2 = Box.createVerticalBox();

      final int SIZE = 3; // 每个Box中的Button数目

      // 向第一个水平Box中加入Button
      for ( int count = 0; count < SIZE; count++ )
         horizontal1.add( new JButton( "Button " + count ) );

      //向第一个垂直Box中加入Button和Strut
      for ( int count = 0; count < SIZE; count++ ) {
         vertical1.add( Box.createVerticalStrut( 25 ) );
         vertical1.add( new JButton( "Button " + count ) );
      }

      //向第二个水平Box中加入Button和Glue
      for ( int count = 0; count < SIZE; count++ ) {
         horizontal2.add( Box.createHorizontalGlue() );
         horizontal2.add( new JButton( "Button " + count ) );
      }

      //向第一个垂直Box中加入Button和Rigid Area
      for ( int count = 0; count < SIZE; count++ ) {
         vertical2.add( Box.createRigidArea( new Dimension( 12, 8 ) ) );
         vertical2.add( new JButton( "Button " + count ) );
      }

      //向一个Panel中沿垂直方向加入Button和Glue
      JPanel panel = new JPanel();
      panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );

      for ( int count = 0; count < SIZE; count++ ) {
         panel.add( Box.createGlue() );
         panel.add( new JButton( "Button " + count ) );
      }

      //使标签一栏带有滚动标签： 
      JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, 
JTabbedPane.SCROLL_TAB_LAYOUT );

      tabs.addTab( "Horizontal Box1", horizontal1 );
      tabs.addTab( "Vertical Box1 with Struts", vertical1 );
      tabs.addTab( "Horizontal Box2 with Glue", horizontal2 );
      tabs.addTab( "Vertical Box2 with Rigid Areas", vertical2 );
      tabs.addTab( "Vertical Box with Glue", panel );

      getContentPane().add( tabs );

      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      setSize( 400, 300 );
      setVisible( true );

   }
   public static void main( String args[] ){
     new BoxLayoutDemo("BoxLayoutDemo");
   }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
