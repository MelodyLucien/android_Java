import javax.swing.JFrame;


public class animals extends JFrame {

	tolerate t;
	public animals(tolerate t) {
		this.t=t;
		this.add(t);
		
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.addKeyListener(t);
	}
	public static void main(String[] args) {
        tolerate t= new tolerate(150,100);
        animals animal=new animals(t);
	}
	
	

}
