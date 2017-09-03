package thirdforcopy;


//Student实现了Cloneable接口  
/**
 * @author zhouhao
 *
 */
public class Student implements Cloneable {
	String name;// 常量对象。
	int age;
	Persion p;

	Student(String name, int age, Persion p) {
		this.name = name;
		this.age = age;
		this.p = p;
	}

	public Object clone() {
		Student o = null;
		try {
			o = (Student) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
		try {
			o.p = (Persion) this.p.clone(); // 在student中调用p的clone方法
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void main(String[] args) {
		Persion p = new Persion("wangwu", 50);
		Student s1 = new Student("zhangsan", 18, p);
		Student s2 = (Student) s1.clone();
		s2.p.name = "lisi";
		s2.p.age = 30;
		// 学生1的教授也变成了lisi,age为30
		System.out.println("name=" + s1.p.name + "," + "age=" + s1.p.age);
		System.out.println("name=" + s2.p.name + "," + "age=" + s2.p.age);
	}
}

// 使用默认的object方法来实现field for field 的clone
class Persion implements Cloneable {
	String name;
	int age;

	public Persion(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
