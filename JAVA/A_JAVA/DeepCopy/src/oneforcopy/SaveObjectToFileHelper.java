package oneforcopy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;


/**
 * 
 * @author zhouhao
 ���ô��л��������
�Ѷ���д������Ĺ����Ǵ��л���Serilization�����̣��ֽж������л������Ѷ�������ж������ģ�Deserialization�����̽з����л���
Ӧ��ָ�����ǣ�д��������Ƕ����һ����������ԭ������Ȼ������JVM���棬
�����Java���������һ�����󣬳���������ʹ����ʵ��Serializable�ӿڣ�
Ȼ��Ѷ���ʵ����ֻ�Ƕ����һ��������д��һ������ٴ����������������ؽ�����
�����������ļ�����Ҳ�������ֽ���

*/
public class SaveObjectToFileHelper {
	
	public  static void save(String outFile,Class class1,Object object){   
		
        FileOutputStream fos = null;  
        ObjectOutputStream oos = null;  
        File f = new File(outFile);  
        try {  
            fos = new FileOutputStream(f);  
            oos = new ObjectOutputStream(fos);  
            oos.writeObject(object);    
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                oos.close();  
                fos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }     
    }  
      
    public static Object open(String path,Class class1){  
    	
        FileInputStream fis = null;  
        ObjectInputStream ois = null;     
        File f = new File(path);  
        Object object=null;
        try {  
            fis = new FileInputStream(f);  
            ois = new ObjectInputStream(fis);  
            object = ois.readObject();
         
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                ois.close();  
                fis.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
		return object;  
    }  
    
    
    public Object deepClone() throws IOException,OptionalDataException,ClassNotFoundException{  
        //������д������  
        ByteArrayOutputStream bo=new ByteArrayOutputStream();  
        ObjectOutputStream oo=new ObjectOutputStream(bo);  
        oo.writeObject(this);// object of studnet  
        //�����������  
        ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());  
        ObjectInputStream oi=new ObjectInputStream(bi);  
        return(oi.readObject());  
    }

}
