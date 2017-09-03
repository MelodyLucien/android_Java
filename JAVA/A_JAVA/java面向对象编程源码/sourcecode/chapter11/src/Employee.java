public class Employee {
private String name;
private int age;	

/** 当雇员的姓名和年龄都已知，就调用此构造方法 */
public Employee(String name, int age) {
  this.name = name;
  this.age=age;
}

/** 当雇员的姓名已知而年龄未知，就调用此构造方法 */
public Employee(String name) {
  this(name, -1);
}

/** 当雇员的姓名和年龄都未知，就调用此构造方法 */
public Employee() {
  this( "无名氏" );
}
public void setName(String name){this.name=name; }
public String getName(){return name; }
public void setAge(int age){this.age=age;}
public int getAge(){return age;}

public static void main(String args[]){
  Employee zhangsan=new Employee("张三",25);
  Employee lisi=new Employee("李四");
  Employee someone=new Employee();

}

}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
