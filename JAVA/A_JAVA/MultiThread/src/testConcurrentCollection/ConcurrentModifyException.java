package testConcurrentCollection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * 在itertor迭代的过程中如果出现了对集合的修改则回报出ConcurrentModificationException
 * @author zhouhao
 *
 */
public class ConcurrentModifyException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
      
		ConcurrentModifyException exception=new ConcurrentModifyException();
		
		//初始化对象
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
		
		//启动线程进行修改
		  new Thread(new Runnable() {
				
				@Override
				public void run() {
					set.remove(third.getString());
					
				}
			}).start();
		  
		  //同时进行迭代
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
