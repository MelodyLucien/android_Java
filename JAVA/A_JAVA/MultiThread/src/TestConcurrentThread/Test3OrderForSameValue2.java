package TestConcurrentThread;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

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
public class Test3OrderForSameValue2 extends Thread {

	private TestDoo2 testDo;
	private String key;
	private String value;

	public Test3OrderForSameValue2(String key, String key2, String value) {
		this.testDo = TestDoo2.getInstance();
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
		
		Test3OrderForSameValue2 a = new Test3OrderForSameValue2("1", "", "1");
		Test3OrderForSameValue2 b = new Test3OrderForSameValue2("1", "", "2");
		Test3OrderForSameValue2 c = new Test3OrderForSameValue2("3", "", "3");
		Test3OrderForSameValue2 d = new Test3OrderForSameValue2("4", "", "1");
		Test3OrderForSameValue2 e = new Test3OrderForSameValue2("4", "", "2");
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

class TestDoo2 {
	
	//����Ϊ����ģʽ������룬ע��ʶ�𣬱�֤ʹ�õ�Ϊͬһ������Ĵ���
	private TestDoo2() {
	}

	private static TestDoo2 _instance = new TestDoo2();

	public static TestDoo2 getInstance() {
		return _instance;
	}
	
	
	//���µĽ���˼·����ͬ����set�ཫkey������
	//������ͬ��(equals()Ϊtrue)key��ʱ��ֻ��һ�����У�ʹ��key��Ϊͬ���Ķ���
	CopyOnWriteArraySet<Object> arraySet=new CopyOnWriteArraySet<>();
	
	public void doSome(Object key, String value) {
		
		
        Object object=key;
        if(!arraySet.contains(object)){
        	arraySet.add(object);
        }else{
        	for (Iterator iterator = arraySet.iterator(); iterator.hasNext();) {
				Object type = (Object) iterator.next();
				if(object.equals(type)){
				object=type;
				break;
				}
			}
        }
        
		synchronized (object) {
			
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
