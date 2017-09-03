package o4TraditionalThreadCommunication;

/**
 * ����ֻ�������߳̽��н������൱���ڶ������Ŷ� ������ֻ��һ���ڵȴ���֮��һ��һ��ȡ��ִ�У���˽������
 * �������ֻ����������֮���ͨ�ţ��п��ܳ�������
 * @author zhouhao
 */
public class Client {

	public static void main(String[] args) {
		
        final Business business=new Business();  //�ڲ���ֻ�ܷ���final����
		
        new Thread(new Runnable() {               //��һ���������߳�

			@Override
			public void run() {
				
					for (int i = 0; i < 50; i++) {
						business.print1(i);
				}
			}
		}).start();

		
		new Thread(new Runnable() {               //�ڶ����������߳�

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
	
	public synchronized void print1(int i){  //ͬ��������Ϊthis����
		
		while(!isPrint1){   //or if  �˴���while�ǻ��ٴμ�������ֵ�Ƿ�ı䣬��ֹ��ٻ���
			try {
			  //**************************���Զ���
				if((count&1)==0){
					buffer.append("0");
					count--;
					System.out.println("С�ܽ��� ����");
				}
				//*************************���Զ���
				
				this.wait();                 //ʹ��this�����ͬ������
				                             //����֮���Ӵ˴���������
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
				//**************************���Զ���
				if((count&1)==1){
					count--;
					buffer.append("1");
					System.out.println("������ ������������������������");
				}
				//**************************���Զ���
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
