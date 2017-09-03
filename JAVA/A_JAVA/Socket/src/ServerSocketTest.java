/**    
* @Title: ServerSocketTest.java 
* @Description: TODO(���ļ�Ϊ��Socket�У����رպ󣬷���ʲô�¡���Sever���Զ�) 
* @author ����ѧAndroid 
* @date 2011-11-12 ����11:31:05 
* @version V1.0    
*/  
  
import java.io.*;  
import java.net.*;  
  
public class ServerSocketTest extends Thread{  
    private ServerSocket myServerSocket;  
    private final int PORT = 9999;  
    public static void main(String[] args){  
        ServerSocketTest sst = new ServerSocketTest();  
        sst.start();  
    }  
      
    public ServerSocketTest(){  
        // ��ʼ��һ��ServeSocket��  
        try {  
            myServerSocket = new ServerSocket(PORT);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public void run(){  
        while(true){  
            System.out.println("���Ƿ�����������9999�˿ڼ���....");  
            try {  
                Socket socket = myServerSocket.accept();  
                DataInputStream din = new DataInputStream(new BufferedInputStream(socket.getInputStream()));  
                String msgIn = din.readUTF();  
                System.out.println(msgIn.trim());  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}  