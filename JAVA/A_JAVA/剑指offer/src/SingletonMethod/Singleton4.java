package SingletonMethod;

//ʵ�ֶ���ͬ����������С

public class Singleton4 {
	
	//ע��  voliate  ��������synchronized�Ŀɼ��ԣ�һ���и��±�ɼ�ʱ����
	  private volatile static Singleton4 instance=null;			
			//other field.......
			
			//����Ϊ˽�еĹ��췽�����ͻ����޷�ʵ�ָ����ʵ��
			private Singleton4() {
				System.out.println("��������ǵĵ���ģʽ");
			}
			
			//ȫ�ַ��ʵ㡣ע��˫����ĺ���
			public static Singleton4 getInstance()
			{
				if(instance==null)
					
				  synchronized (Singleton4.class) {
					  
					if(instance==null)//double check if it is created
						instance=new Singleton4();
				}
				
				return instance;
				
			}
			
			//other......
		    public static void main(String[] args) {
			  
		   }

}
