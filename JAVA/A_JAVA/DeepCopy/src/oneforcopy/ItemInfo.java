package oneforcopy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

/**
 * 
 * @author zhouhao
 *
 */
public class ItemInfo implements Serializable{
	
	
	private int rank=-1;
	private String name;
	private String score;
	
	public ItemInfo(int rank, String name, String score) {
		super();
		this.rank = rank;
		this.name = name;
		this.score = score;
	}
	
	public ItemInfo(String name, String score) {
		super();
		this.name = name;
		this.score = score;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	/**
	 * 使用串行化的方法进行深拷贝
	 * @return
	 * @throws IOException
	 * @throws OptionalDataException
	 * @throws ClassNotFoundException
	 */
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
