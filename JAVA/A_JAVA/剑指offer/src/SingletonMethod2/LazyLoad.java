package SingletonMethod2;

//ʹ�þ�̬�ڲ�����,������ֲ����Ե�����

public class LazyLoad {
	
	private LazyLoad() {
		
	}
	
	
   private static class Holder{
	   private static final LazyLoad LOAD=new LazyLoad();
   }
   
   
   //ֻ���ڵ���getinstance����ʱ�Ż����holder�࣬���Ҽ���holder��ʱ�����Ȼ��ʼ������static������
   public static LazyLoad getInstance(){
	   return Holder.LOAD;
   }
   
}
