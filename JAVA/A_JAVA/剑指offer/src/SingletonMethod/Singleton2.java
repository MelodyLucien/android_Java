package SingletonMethod;


//����ӳٴ���ʵ������ָ�ڵ�һ��ʹ��ʱ�ڴ����ؼ��ӳٴ���
public class Singleton2 {
	
	//Ҳ�������Ϊpublic�ģ���©���ͻ��˶���,����ļ���ʱ�ͻᱻִ��
		private static Singleton2 instance=null;
		
		//other field.......
		
		//����Ϊ˽�еĹ��췽�����ͻ����޷�ʵ�ָ����ʵ��
		private Singleton2() {
			System.out.println("��������ǵĵ���ģʽ");
		}
		
		//ȫ�ַ��ʵ�
		public static Singleton2 getInstance()
		{
			if(instance==null)
			{
				instance=new Singleton2();
			}
			return instance;
			
		}
		
		//other......
	    public static void main(String[] args) {
		  
	   }
	
	
//ע���˷����̲߳���ȫ����instance���ж����ж��̵߳Ĳ���ʱ��ȱ��
}
