

/**
 * ���Ը��ǵ���ȫ��
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

	//���¶�������
	public SubOverrider(int a, int b) {
		super(a, b);
	}
	
	//����ʹ��super�����ø���Ĺ��췽����̫���ˣ���û����ȫ����
	@Override
	public int add() {
		return super.add();
	}
		
}
