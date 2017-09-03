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
 利用串行化来做深复制
把对象写到流里的过程是串行化（Serilization）过程，又叫对象序列化，而把对象从流中读出来的（Deserialization）过程叫反序列化。
应当指出的是，写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面，
因此在Java语言里深复制一个对象，常常可以先使对象实现Serializable接口，
然后把对象（实际上只是对象的一个拷贝）写到一个流里，再从流里读出来便可以重建对象。
此流可以是文件流，也可以是字节流

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
        //将对象写到流里  
        ByteArrayOutputStream bo=new ByteArrayOutputStream();  
        ObjectOutputStream oo=new ObjectOutputStream(bo);  
        oo.writeObject(this);// object of studnet  
        //从流里读出来  
        ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());  
        ObjectInputStream oi=new ObjectInputStream(bi);  
        return(oi.readObject());  
    }

}
