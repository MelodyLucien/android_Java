/**    
* @Title: ServerSocketTest.java 
* @Description: TODO(该文件为”Socket中，流关闭后，发生什么事“的Sever测试端) 
* @author 慢跑学Android 
* @date 2011-11-12 上午11:31:05 
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
        // 初始化一个ServeSocket端  
        try {  
            myServerSocket = new ServerSocket(PORT);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public void run(){  
        while(true){  
            System.out.println("我是服务器，我在9999端口监听....");  
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