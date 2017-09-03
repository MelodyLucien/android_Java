package o4TraditionalThreadCommunication;

/**
 * 由于只有两个线程进行交互，相当于在队列中排队 队列中只有一个在等待，之后一个一个取出执行，因此解决问题
 * 如果不是只有两个进程之间的通信，有可能出现问题
 * @author zhouhao
 */
public class Client {

	public static void main(String[] args) {
		
        final Business business=new Business();  //内部类只能访问final变量
		
        new Thread(new Runnable() {               //第一个启动的线程

			@Override
			public void run() {
				
					for (int i = 0; i < 50; i++) {
						business.print1(i);
				}
			}
		}).start();

		
		new Thread(new Runnable() {               //第二个启动的线程

			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
				   business.print2(i);
				}
			}
		}).start();
	}
}

class Business{
	
	private boolean isPrint1=true;
	private  int count=10000;
	private StringBuffer buffer=new StringBuffer("");
	
	public synchronized void print1(int i){  //同步监听器为this对象
		
		while(!isPrint1){   //or if  此处用while是会再次检查变量的值是否改变，防止虚假唤醒
			try {
			  //**************************测试队列
				if((count&1)==0){
					buffer.append("0");
					count--;
					System.out.println("小弟进入 ——");
				}
				//*************************测试队列
				
				this.wait();                 //使用this对象的同步队列
				                             //唤醒之后会从此处继续进行
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (int j = 0; j < 10; j++) {
			System.out.println(Thread.currentThread()
					.getName()
					+ "--"
					+ j
					+ "---loop of"
					+ i);
		}
		isPrint1=false;
		notify();
	}
	
	
	public synchronized void print2(int i){
		
		while(isPrint1){  // or if
			try {
				//**************************测试队列
				if((count&1)==1){
					count--;
					buffer.append("1");
					System.out.println("大哥进入 ————————————");
				}
				//**************************测试队列
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int j = 0; j < 100; j++) {
			System.out.println(Thread.currentThread().getName()
					+ "--" + j + "---loop of" + i);
		}
		
		isPrint1=true;
		notify();
		
	}
	
}
