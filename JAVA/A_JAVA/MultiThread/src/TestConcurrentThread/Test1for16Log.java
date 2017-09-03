package TestConcurrentThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 使用线程池技术解决问题
 * @author zhouhao
 *
 */
public class Test1for16Log {
	
	public static void main(String[] args){
        
		System.out.println("begin:"+(System.currentTimeMillis()/1000));
		/*模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
		修改程序代码，开四个线程让这16个对象在4秒钟打完。
		*/
		
		ExecutorService service=Executors.newFixedThreadPool(4);
		for(int i=0;i<16;i++){  //这行代码不能改动
			final String log = ""+(i+1);//这行代码不能改动
			{
				service.execute(new Runnable() {//注意此处每个线程拿到的数据都不相同
					@Override
					public void run() {
						Test1for16Log.parseLog(log);
					}
				});
			}
		}
		
		service.shutdown();
	}
	
	//parseLog方法内部的代码不能改动
	public  static void parseLog(String log){
		System.out.println(log+":"+(System.currentTimeMillis()/1000));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
}