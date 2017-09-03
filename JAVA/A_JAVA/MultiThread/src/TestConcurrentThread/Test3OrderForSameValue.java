package TestConcurrentThread;

/**
 * ���г���ͬʱ������4���߳�ȥ����TestDo.doSome(key, value)������ ����TestDo.doSome(key,
 * value)�����ڵĴ���������ͣ1�룬Ȼ�����������Ϊ��λ�ĵ�ǰʱ��ֵ�� ���ԣ����ӡ��4����ͬ��ʱ��ֵ��������ʾ�� 
 * 4:4:1258199615
 * 1:1:1258199615 
 * 3:3:1258199615 
 * 1:2:1258199615
 * ���޸Ĵ��룬����м����̵߳���TestDo.doSome(key, value)����ʱ��
 * ���ݽ�ȥ��key��ȣ�equals�Ƚ�Ϊtrue�������⼸���߳�Ӧ�����Ŷ���������
 * �����������̵߳�key����"1"ʱ�������е�һ��Ҫ�����������߳���1����������������ʾ�� 
 * 4:4:1258199615 
 * 1:1:1258199615
 * 3:3:1258199615 
 * 1:2:1258199616
 * ��֮����ÿ���߳���ָ����key���ʱ����Щ���key���߳�Ӧÿ��һ���������ʱ��ֵ��Ҫ�û��⣩�� ���key��ͬ������ִ�У��໥֮�䲻���⣩��
 * 
 * @author zhouhao
 * 
 */
// ���ܸĶ���Test��
public class Test3OrderForSameValue extends Thread {

	private TestDoo testDo;
	private String key;
	private String value;

	public Test3OrderForSameValue(String key, String key2, String value) {
		this.testDo = TestDoo.getInstance();
		/*
		 * ����"1"��"1"��ͬһ�������������д������Ҫ��"1"+""�ķ�ʽ�����µĶ���
		 * ��ʵ������û�иı䣬��Ȼ��ȣ�����Ϊ"1"����������ȴ������ͬһ����Ч��
		 */
		this.key = key + key2;
		this.value = value;
	}

	public static void main(String[] args) throws InterruptedException {
		/*
		 * ����˼·��������ʹ����һ��Ͷ��ȡ�ɵķ�����ʹ��string��intern()����
		 */
		
		Test3OrderForSameValue a = new Test3OrderForSameValue("1", "", "1");
		Test3OrderForSameValue b = new Test3OrderForSameValue("1", "", "2");
		Test3OrderForSameValue c = new Test3OrderForSameValue("3", "", "3");
		Test3OrderForSameValue d = new Test3OrderForSameValue("4", "", "1");
		Test3OrderForSameValue e = new Test3OrderForSameValue("4", "", "2");
		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		a.start();
		b.start();
		c.start();
		d.start();
		e.start();

	}

	public void run() {

		testDo.doSome(key, value);

	}
}

class TestDoo {

	private TestDoo() {
	}

	private static TestDoo _instance = new TestDoo();

	public static TestDoo getInstance() {
		return _instance;
	}

	
	public void doSome(Object key, String value) {

		String string = (String) key;

		synchronized (string.intern()) {
			
			// �Դ������ڵ�����Ҫ�ֲ�ͬ���Ĵ��룬���ܸĶ�!
			{
				try {
					Thread.sleep(1000);
					System.out.println(key + ":" + value + ":"
							+ (System.currentTimeMillis() / 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
