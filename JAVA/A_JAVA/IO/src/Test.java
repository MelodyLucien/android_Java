import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	public static void main(String[] args) throws IOException {

		// bufferreader��ʹ��
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
					System.out.println("��ȡ����");
				} catch (Exception e) {
					e.printStackTrace();

				} finally {

				}
			};

		}.start();

		// ��ȡ�ļ���ʹ���ַ���ʽ
		/*
		 * BufferedReader in=new BufferedReader(new FileReader("D://TEST.txt"));
		 * String line=null; while ((line=in.readLine())!=null) {
		 * System.out.println(line); }
		 */

		/*
		 * //��ȡ�ļ����ֽ���ʽ FileInputStream inputStream=new
		 * FileInputStream("D://aa.txt"); //��Ҫָ���ֽ������С�����ڴ��͵��ļ���ȡ������ byte[]
		 * bytes=new byte[1024]; int i=inputStream.read(bytes); //���ֽ�ת��Ϊ�ַ���
		 * String string =new String(bytes);
		 * //ȥ���ո񣬷�ֹ�б�ĳ���(byte�������β���Ŀհ�ת��Ϊ�˲������ַ�) string.trim();
		 * System.out.println(string);
		 * 
		 * FileOutputStream outputStream=new FileOutputStream("D://bb.txt");
		 * //�����ָ���ļ������ļ����ĸ�ʽ��������Դһ�� outputStream.write(bytes);
		 */

		// ��ȡ�����ļ����ֽ���ʽ
		/*
		 * FileInputStream inputStream = new FileInputStream("D://big.txt");
		 * FileOutputStream outputStream = new
		 * FileOutputStream("D://bigto.txt"); // ��Ҫָ���ֽ������С�����ڴ��͵��ļ���ȡ������ byte[]
		 * bytes = new byte[1024];
		 * 
		 * try { while (true)// ��ѭ���������ļ� { int i = inputStream.read(bytes); if(i
		 * == -1) break; // ���ֽ�ת��Ϊ�ַ��� String string = new String(bytes);
		 * //ȥ���ո񣬷�ֹ�б�ĳ���(byte�������β���Ŀհ�ת��Ϊ�˲������ַ�) string.trim();
		 * System.out.println(string); // �����ָ���ļ������ļ����ĸ�ʽ��������Դһ��
		 * outputStream.write(bytes); } } catch (IOException e) {
		 * e.printStackTrace(); } finally { //���Ҫ���Ƕ�������������йر� try {
		 * inputStream.close(); outputStream.close(); } catch (IOException e) {
		 * e.printStackTrace(); } }
		 */

		// ʹ���ַ�����ȡ�ļ�
		/*
		 * FileReader fileReader=new FileReader("D://big.txt"); FileWriter
		 * fileWriter=new FileWriter("D://biggto.txt"); char[] buffer=new
		 * char[100]; try { while(true){ int temp=fileReader.read(buffer);
		 * if(temp!=-1){ System.out.println(buffer); fileWriter.write(buffer); }
		 * else break; } } catch (Exception e) { e.printStackTrace(); }finally{
		 * fileReader.close(); fileWriter.close(); }
		 */

		// bufferreader��������⣬������readline������������һ���Զ�ȡһ�����ݣ�
		// ������filereader����Ƚ��鷳

	}
}
