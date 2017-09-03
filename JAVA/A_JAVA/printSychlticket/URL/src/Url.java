import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Url {
     public static void main(String[] args) throws IOException {
    	 //读取网址资源对应文件的源码，只可用于从网络上读取数据
		/*URL  url=new URL("http://www.baidu.com/");
		BufferedReader reader= new BufferedReader(new InputStreamReader(url.openStream()));
		String inputline;
		while((inputline=reader.readLine())!=null){
			System.out.println(inputline);
		}
		reader.close();
		*/
		
		//URLConnection是一个抽象类，可与URL指定的数据源进行动态链接
    	 //器允许用post或put和其他http的请求方法将数据送回服务器
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
