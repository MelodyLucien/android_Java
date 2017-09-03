package final”√∑®;

public class Son {
	private int age;
	private String name;
	
	
	public Son(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	final void smoke(){
		System.out.println("i cant be inherit by my offspring");
	}
	

}
