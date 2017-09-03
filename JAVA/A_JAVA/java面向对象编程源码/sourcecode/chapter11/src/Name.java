import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.lang.ref.*;

public class Name {
  private final String firstname;
  private final String lastname;

  public Name(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
  }

  public String getFirstname(){
      return firstname;
  }
  public String getLastname(){
      return lastname;
  }
  public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Name)) return false;

       final Name name = (Name) o;
       if(!firstname.equals(name.firstname)) return false;
       if(!lastname.equals(name.lastname)) return false;
       return true;
  }

  public int hashCode(){
       int result;
       result= (firstname==null?0:firstname.hashCode());
       result = 29 * result + (lastname==null?0:lastname.hashCode());
       return result;
  }

  public String toString(){
       return lastname+" "+firstname;
  }
  
  //实例缓存，存放Name对象的软引用
  private static final Set<SoftReference<Name>> names=
                           new  HashSet<SoftReference<Name>>();  
  
  public static Name valueOf(String firstname, String lastname){  //静态工厂方法
      Iterator<SoftReference<Name>> it=names.iterator(); 
      while(it.hasNext()){
        SoftReference<Name> ref=it.next();
        Name name=ref.get();
        if(name!=null 
           && name.firstname.equals(firstname)
           && name.lastname.equals(lastname))
          return name;
      }
      Name name=new Name(firstname,lastname);
      names.add(new SoftReference<Name>(name));
      return name;
  }

  public static void main(String args[]){
    Name n1=Name.valueOf("小红","王");
    Name n2=Name.valueOf("小红","王");
    Name n3=Name.valueOf("小东","张");
    System.out.println(n1);
    System.out.println(n2);
    System.out.println(n3);
    System.out.println(n1==n2);
  } 
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
