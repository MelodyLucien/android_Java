package ������ʽ;

/**
 * �����ַ�����ʾ������ʽ��
 * ���ȱ�ʾ����ȷ��������ʽ���ٿ������ʹ���ַ�����ʾ�Ļ���Ӧ�����ʲô����
 * @author zhouhao
 */
public class Ff{
	
	public static final String email="^\\w*@\\w*\\.com$";
	public static final String mobile="^1\\d{10}";
	public static final String telp="^\\d{8}";
	public static final String file="^log\\.txt(\\.\\d){0,1}";
	
	public static boolean tEmail(String str)
	{
		if(str.matches(email))
		  return true;
		else
		  return false;
	}
	
	public static boolean tMobile(String str)
	{
		if(str.matches(mobile))
		  return true;
		else
		  return false;
	}
	
	public static boolean tTelp(String str)
	{
		if(str.matches(telp))
		  return true;
		else
		  return false;
	}
	
	public static boolean tFile(String str)
	{
		if(str.matches(file)){
		  return true;
		}
		else
		  return false;
	}
	
	public static void main(String[] args) {
		
		
		//��������
		String email="uu9923@126.com";
		System.out.println(Ff.tEmail(email));
		
		
		//�����ֻ�
		String mobile="13484813457";
		System.out.println(Ff.tMobile(mobile));
		
		
		//���Ե绰����
		String telp="87349056";
		System.out.println(Ff.tTelp(telp));
		System.out.println(String.class);
		
		String[] files={"log.txt","log.txt.1","log.txt.2","log.txt.3","log.txt.4","log.txt.q"};
		for (int i = 0; i < files.length; i++) {
			System.out.println(tFile(files[i]));
		}
	}
	
	
}
