

/**
 * 测试覆盖的完全性
 * @author zhouhao
 *
 */
public class Overrider {
	
	int a;
	int b;
	
	public Overrider(int a,int b){
		this.a=a;
		this.b=b;
	}
	
	public int add(){
		return a+b;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
        Overrider overloader=new SubOverrider(1, 2);
        int result=overloader.add();
        System.out.println(result);
	}

	
}

class SubOverrider extends Overrider{

	//重新定义属性
	public SubOverrider(int a, int b) {
		super(a, b);
	}
	
	//可以使用super来调用父类的构造方法，太棒了，并没有完全覆盖
	@Override
	public int add() {
		return super.add();
	}
		
}
