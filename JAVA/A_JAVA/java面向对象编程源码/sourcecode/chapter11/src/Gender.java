import java.io.Serializable;
import java.util.*;
public class Gender implements Serializable {
    private final Character sex;
    private final transient String description;

    public Character getSex() {
        return sex;
    }
    public String getDescription() {
        return description;
    }

    private static final Map<Character,Gender> instancesBySex = 
new HashMap<Character,Gender>();

    /**
     * 把构造方法声明为private类型，以便禁止外部程序创建Gender类的实例
     */
    private Gender(Character sex, String description) {
        this.sex = sex;
        this.description = description;
        instancesBySex.put(sex, this);
    }

    public static final Gender FEMALE =
        new Gender(new Character('F'), "Female");

    public static final Gender MALE =
        new Gender(new Character('M'), "Male");

    public static Collection getAllValues() {
        return Collections.unmodifiableCollection(instancesBySex.values());
    }

    /**
     * 按照参数指定的性别缩写查找Gender实例
     */
    public static Gender getInstance(Character sex) {
        Gender result = (Gender)instancesBySex.get(sex);
        if (result == null) {
            throw new NoSuchElementException(sex.toString());
        }
        return result;
    }

    public String toString() {
        return description;
    }

    /**
     * 保证反序列化时直接返回Gender类包含的静态实例
     */
    private Object readResolve() {
        return getInstance(sex);
    }

    public static void main(String args[]){
      Gender female=Gender.getInstance(new Character('F'));
      Gender male=Gender.getInstance(new Character('M'));
    }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
