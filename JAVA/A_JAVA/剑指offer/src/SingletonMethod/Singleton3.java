package SingletonMethod;


//�̰߳�ȫ�ĵ���ģʽ,�����ٶ���̷߳���Ҳֻ����һ��ʵ��
public class Singleton3 {
	
	//Ҳ�������Ϊpublic�ģ���©���ͻ��˶���,����ļ���ʱ�ͻᱻִ��
	  private static Singleton3 instance=null;			
			//other field.......
			
			//����Ϊ˽�еĹ��췽�����ͻ����޷�ʵ�ָ����ʵ��
			private Singleton3() {
				System.out.println("��������ǵĵ���ģʽ");
			}
			
			//ȫ�ַ��ʵ�
			public static synchronized Singleton3 getInstance()
			{
				if(instance==null)
				{
					instance=new Singleton3();
				}
				return instance;
				
			}
			
			//other......
		    public static void main(String[] args) {
			  
		   }
//���⣺�ڶ��̷߳���ʱ�����ܲ��á���ͬ����ķ�Χ�����ˣ��������ܵ��½��������Ż��𣿴𰸣���		   
		
}
