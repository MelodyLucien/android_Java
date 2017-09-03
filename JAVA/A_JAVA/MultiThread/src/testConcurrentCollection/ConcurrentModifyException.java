package testConcurrentCollection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * ��itertor�����Ĺ�������������˶Լ��ϵ��޸���ر���ConcurrentModificationException
 * @author zhouhao
 *
 */
public class ConcurrentModifyException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
      
		ConcurrentModifyException exception=new ConcurrentModifyException();
		
		//��ʼ������
		final ShareData third=exception.new ShareData();
		
		final Set<String> set=new HashSet<>();
		
		for (int i = 0; i <5; i++) {
			
			String string=new String("i am    "+i+"");
			if(i==3){
				third.setString(string);
			}
			set.add(string);
		}
		
		Iterator<String> iterator=set.iterator();
		
		//�����߳̽����޸�
		  new Thread(new Runnable() {
				
				@Override
				public void run() {
					set.remove(third.getString());
					
				}
			}).start();
		  
		  //ͬʱ���е���
        while(iterator.hasNext()){
        	String string=iterator.next();
        	System.out.println(string);
        }
        
	}
	
	private class ShareData{

		  String string;
		  
		public ShareData() {
			
		}

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}
		  
		
	}

}
