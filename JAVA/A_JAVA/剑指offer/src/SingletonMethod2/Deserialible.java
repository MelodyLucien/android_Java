package SingletonMethod2;

import java.io.Serializable;

public class Deserialible implements Serializable{
	//Ҳ�������Ϊpublic�ģ���©���ͻ��˶���,����ļ���ʱ�ͻᱻִ��
		private static Deserialible instance=new Deserialible();
		
		//other field.......
		
		
		//����Ϊ˽�еĹ��췽�����ͻ����޷�ʵ�ָ����ʵ��
		private Deserialible() {
			System.out.println("��������ǵĵ���ģʽ");
		}
		
		//ȫ�ַ��ʵ�
		public static Deserialible getInstance()
		{
			return instance;
			
		}
	  
		//**********************************
		//�ڷ����л�ʱ����Ϊͬһ���ڴ��е�ʵ��
		private Object readResolve()
		{
			return instance;
		}
		//**********************************
		//other......
		
		
		
	    public static void main(String[] args) {
		   
	    }

}
