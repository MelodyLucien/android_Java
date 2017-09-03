import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	public static void main(String[] args) throws IOException {

		// bufferreader的使用
		new Thread() {

			public void run() {
				try {
					
					InputStreamReader inputStreamReader = new InputStreamReader(
							System.in);
					char[] buffer = new char[20];
					while (true) {
						int temp = inputStreamReader.read(buffer);
						if (temp == -1)
							break;
						System.out.println(buffer);
					}
					System.out.println("读取结束");
				} catch (Exception e) {
					e.printStackTrace();

				} finally {

				}
			};

		}.start();

		// 读取文件，使用字符形式
		/*
		 * BufferedReader in=new BufferedReader(new FileReader("D://TEST.txt"));
		 * String line=null; while ((line=in.readLine())!=null) {
		 * System.out.println(line); }
		 */

		/*
		 * //读取文件，字节形式 FileInputStream inputStream=new
		 * FileInputStream("D://aa.txt"); //需要指定字节数组大小，对于大型的文件读取不方便 byte[]
		 * bytes=new byte[1024]; int i=inputStream.read(bytes); //将字节转化为字符串
		 * String string =new String(bytes);
		 * //去掉空格，防止有别的出现(byte数组可能尾部的空白转化为了不规则字符) string.trim();
		 * System.out.println(string);
		 * 
		 * FileOutputStream outputStream=new FileOutputStream("D://bb.txt");
		 * //输出到指定文件，该文件最后的格式会与数据源一样 outputStream.write(bytes);
		 */

		// 读取超大文件，字节形式
		/*
		 * FileInputStream inputStream = new FileInputStream("D://big.txt");
		 * FileOutputStream outputStream = new
		 * FileOutputStream("D://bigto.txt"); // 需要指定字节数组大小，对于大型的文件读取不方便 byte[]
		 * bytes = new byte[1024];
		 * 
		 * try { while (true)// 用循环来导出文件 { int i = inputStream.read(bytes); if(i
		 * == -1) break; // 将字节转化为字符串 String string = new String(bytes);
		 * //去掉空格，防止有别的出现(byte数组可能尾部的空白转化为了不规则字符) string.trim();
		 * System.out.println(string); // 输出到指定文件，该文件最后的格式会与数据源一样
		 * outputStream.write(bytes); } } catch (IOException e) {
		 * e.printStackTrace(); } finally { //最后不要忘记对输入输出流进行关闭 try {
		 * inputStream.close(); outputStream.close(); } catch (IOException e) {
		 * e.printStackTrace(); } }
		 */

		// 使用字符来读取文件
		/*
		 * FileReader fileReader=new FileReader("D://big.txt"); FileWriter
		 * fileWriter=new FileWriter("D://biggto.txt"); char[] buffer=new
		 * char[100]; try { while(true){ int temp=fileReader.read(buffer);
		 * if(temp!=-1){ System.out.println(buffer); fileWriter.write(buffer); }
		 * else break; } } catch (Exception e) { e.printStackTrace(); }finally{
		 * fileReader.close(); fileWriter.close(); }
		 */

		// bufferreader的真正理解，利用其readline（）方法可以一次性读取一行数据，
		// 而利用filereader，则比较麻烦

	}
}
