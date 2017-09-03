import java.io.*;
public class User implements Serializable {
  private String name;
  private transient String password;
  public User(String name, String password) {
    this.name=name;
    this.password=password;
  }
  public String toString() {
    return name + " " + password;
  }
  
   public static void main(String[] args) throws Exception{
    User user = new User("Tom", "123456");
    System.out.println("Before Serialization:" + user);
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
   
    //把User对象序列化到一个字节缓存中
    ObjectOutputStream o =new ObjectOutputStream(buf);
    o.writeObject(user);
      
    //从字节缓存中反序列化User对象
    ObjectInputStream in =new ObjectInputStream(
          new ByteArrayInputStream(buf.toByteArray()));
    user= (User)in.readObject();
    System.out.println("After Serialization:" + user);
   }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
