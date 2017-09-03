import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AudioPlayer extends JApplet {
   private AudioClip[] sounds;
   private AudioClip currentSound;
   private JButton playSound, loopSound, stopSound;
   private JComboBox chooseSound;

   public void init(){
      Container c = getContentPane();
      c.setLayout( new FlowLayout() );

      String choices[] = { "saimaqu.mid", "moon.wav","say.wav","change.wav","eagle.wav" };
      chooseSound = new JComboBox( choices );
      chooseSound.addItemListener(
         new ItemListener() {
            public void itemStateChanged( ItemEvent e ){
               currentSound.stop();
               currentSound=sounds[chooseSound.getSelectedIndex()];
            }
         }
      );
      c.add( chooseSound );

      ButtonHandler handler = new ButtonHandler();
      playSound = new JButton( "Play" );
      playSound.addActionListener( handler );
      c.add( playSound );
      loopSound = new JButton( "Loop" );
      loopSound.addActionListener( handler );
      c.add( loopSound );
      stopSound = new JButton( "Stop" );
      stopSound.addActionListener( handler );
      c.add( stopSound );

      sounds=new AudioClip[choices.length];
      for(int i=0;i<choices.length;i++)
        sounds[i]= getAudioClip(getDocumentBase(), choices[i] );
      currentSound=sounds[0];
   }

   public void stop(){
      currentSound.stop();
   }

   private class ButtonHandler implements ActionListener {
      public void actionPerformed( ActionEvent e ){
         if ( e.getSource() == playSound )
            currentSound.play();
         else if ( e.getSource() == loopSound )
            currentSound.loop();
         else if ( e.getSource() == stopSound )
            currentSound.stop();
      }
   }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
