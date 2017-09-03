package TestConcurrentThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * ʹ���̳߳ؼ����������
 * @author zhouhao
 *
 */
public class Test1for16Log {
	
	public static void main(String[] args){
        
		System.out.println("begin:"+(System.currentTimeMillis()/1000));
		/*ģ�⴦��16����־������Ĵ��������16����־���󣬵�ǰ������Ҫ����16����ܴ�ӡ����Щ��־��
		�޸ĳ�����룬���ĸ��߳�����16��������4���Ӵ��ꡣ
		*/
		
		ExecutorService service=Executors.newFixedThreadPool(4);
		for(int i=0;i<16;i++){  //���д��벻�ܸĶ�
			final String log = ""+(i+1);//���д��벻�ܸĶ�
			{
				service.execute(new Runnable() {//ע��˴�ÿ���߳��õ������ݶ�����ͬ
					@Override
					public void run() {
						Test1for16Log.parseLog(log);
					}
				});
			}
		}
		
		service.shutdown();
	}
	
	//parseLog�����ڲ��Ĵ��벻�ܸĶ�
	public  static void parseLog(String log){
		System.out.println(log+":"+(System.currentTimeMillis()/1000));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
}