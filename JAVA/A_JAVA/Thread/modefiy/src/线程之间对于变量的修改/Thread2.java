package 线程之间对于变量的修改;

public class Thread2 {
	
	public static void main(String[] args) {
		new Threadd().start();
		new Threadd3().start();
	}

}
