package listVSmap;

/**
 * ��Ҫ��дequals��hashcode�������ſɱ�֤set������������ͬ
 * @author zhouhao
 */
public class Monkey {
	
		private String name;
		private String sex;
	
		public Monkey(String name, String sex) {
			this.name = name;
			this.sex = sex;
		}
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public void setSex(String sex) {
			this.sex = sex;
		}
	
		public String getSex() {
			return sex;
		}
		
		
		
}
