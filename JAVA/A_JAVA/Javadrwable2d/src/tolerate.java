import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class tolerate extends Panel implements KeyListener{
	int x;
	int y;
	public tolerate(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		System.out.println("PAINT ");
		g.setColor(Color.green);
		g.fillOval(this.getX(),this.getY(),60,80);
		
		g.drawOval(this.getX()+17, this.getY()-10, 20, 20);
		g.drawOval(this.getX()-2, this.getY()+7, 10, 10);
		g.drawOval(this.getX()+50, this.getY()+7, 10, 10);
		g.drawOval(this.getX()-2, this.getY()+60, 10, 10);
		g.drawOval(this.getX()+50, this.getY()+60, 10, 10);
		g.setColor(Color.BLACK);
		g.drawArc(this.getX()+27, this.getY()+80,10,10,90,180);
	}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			y--;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			y++;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			x--;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			x++;
		}
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    
	

	
	

}
