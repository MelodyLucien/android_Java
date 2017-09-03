package thirdforcopy;


//Studentʵ����Cloneable�ӿ�  
/**
 * @author zhouhao
 *
 */
public class Student implements Cloneable {
	String name;// ��������
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
			o.p = (Persion) this.p.clone(); // ��student�е���p��clone����
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
		// ѧ��1�Ľ���Ҳ�����lisi,ageΪ30
		System.out.println("name=" + s1.p.name + "," + "age=" + s1.p.age);
		System.out.println("name=" + s2.p.name + "," + "age=" + s2.p.age);
	}
}

// ʹ��Ĭ�ϵ�object������ʵ��field for field ��clone
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
