package SingletonMethod;
//��򵥵ĵ���ģ��

public class Deserialible {
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
	
	//other......
    public static void main(String[] args) {
	   
    }
}
//���⣬�޷�ʵ����ֻ�е�һ��ʹ��ʱ��ȥʵ��������Ϊ����static ����������  ���ڼ�����ʱ�ͻᴴ��
//ע�⣬Jvm�ڼ��ش���ʱ������static���Եĳ�ʼ��ֻ����һ���߳�ִ���ҽ���һ��,���Ա��������̰߳�ȫ��

