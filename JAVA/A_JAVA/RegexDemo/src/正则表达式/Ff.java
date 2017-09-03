package 正则表达式;

/**
 * 对于字符串表示正则表达式，
 * 首先表示出正确的正则表达式，再考虑如果使用字符串显示的话，应该添加什么即可
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
		
		
		//测试邮箱
		String email="uu9923@126.com";
		System.out.println(Ff.tEmail(email));
		
		
		//测试手机
		String mobile="13484813457";
		System.out.println(Ff.tMobile(mobile));
		
		
		//测试电话号码
		String telp="87349056";
		System.out.println(Ff.tTelp(telp));
		System.out.println(String.class);
		
		String[] files={"log.txt","log.txt.1","log.txt.2","log.txt.3","log.txt.4","log.txt.q"};
		for (int i = 0; i < files.length; i++) {
			System.out.println(tFile(files[i]));
		}
	}
	
	
}
