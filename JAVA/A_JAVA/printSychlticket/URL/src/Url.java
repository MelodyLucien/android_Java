import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Url {
     public static void main(String[] args) throws IOException {
    	 //��ȡ��ַ��Դ��Ӧ�ļ���Դ�룬ֻ�����ڴ������϶�ȡ����
		/*URL  url=new URL("http://www.baidu.com/");
		BufferedReader reader= new BufferedReader(new InputStreamReader(url.openStream()));
		String inputline;
		while((inputline=reader.readLine())!=null){
			System.out.println(inputline);
		}
		reader.close();
		*/
		
		//URLConnection��һ�������࣬����URLָ��������Դ���ж�̬����
    	 //��������post��put������http�����󷽷��������ͻط�����
    	 URL  url=new URL("http://www.baidu.com/");
    	 URLConnection connection=url.openConnection();
 		BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
 		String inputline;
 		while((inputline=reader.readLine())!=null){
 			System.out.println(inputline);
 		}
 		reader.close();
	}
}
