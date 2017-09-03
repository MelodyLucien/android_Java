import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


/** 
* @ClassName: SocketTest 
* @Description: ����Socket�У����رպ�socket�Ƿ�رգ��Ƿ���ؿ���������������������Ƿ��ͳ�ȥ�����Ƕ����� 
* @author ����ѧAndroid 
* @date 2011-11-12 ����11:15:21 
*  
*/  
public class SocketTest {  
    Socket mySocket;  
    DataOutputStream dout;  
    public static void main(String[] args){  
        new SocketTest();  
    }  
      
    public SocketTest(){  
        // ������رյĲ���һ��socket�ر���  
        //test1();  
        // ������رղ��Զ��������Ƿ�������¿�����  
        test2();  
        // ������رղ������������������������Ƕ��������Ƿ��ͣ�  
      //  test3();  
    }  
  
    private void test1() {  
        // ������رյĲ���һ��socket�ر���  
        System.out.println("\n****2�ַ�ʽ�ر��������Socket�Ƿ�رգ�***\n");  
        try {  
            mySocket = new Socket("localhost",9999);  
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        try {  
            dout = new DataOutputStream(new BufferedOutputStream(mySocket.getOutputStream()));  
            //������һ����Ҫ������֤��socketȷʵ���ڿ���״̬  
            System.out.println("������մ�,Socket�Ƿ�رգ�" + mySocket.isClosed());  
            mySocket.shutdownOutput();  
            System.out.println("ʹ��shutdownOutput�ر��������Socket�Ƿ�رգ�" + mySocket.isClosed());  
            dout.close();  
            System.out.println("ʹ��close�ر��������Socket�Ƿ�رգ�" + mySocket.isClosed());  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    private void test2() {  
        // ������رղ��Զ���ʹ��shutdownOutputStream��������Ƿ�������¿�����  
        System.out.println("\n****ʹ��shutdownOutputStream��������Ƿ�������¿�����***\n");  
        try {  
            mySocket = new Socket("localhost",9999);  
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        try {  
            dout = new DataOutputStream(new BufferedOutputStream(mySocket.getOutputStream()));  
            mySocket.shutdownOutput();  
            // �ؿ������  
            dout = new DataOutputStream(mySocket.getOutputStream());  
            dout.writeUTF("�Ƿ��������ؿ���");  
            // ���������棬ȷ����doutͨ��û����ʱ����Ϣ���Ե��������  
            dout.flush();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                mySocket.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
    private void test3(){  
        // ������رղ������������������������Ƕ��������Ƿ��ͣ�  
        System.out.println("\n***�����������������Ƕ��������Ƿ��ͣ�****\n");  
        try {  
            mySocket = new Socket("27.154.122.233",9999);  
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        try {  
            dout = new DataOutputStream(new BufferedOutputStream(mySocket.getOutputStream()));  
            dout.writeUTF("shutdownOutput�����ݷ��õó�ȥ��");  
            mySocket.shutdownOutput();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  