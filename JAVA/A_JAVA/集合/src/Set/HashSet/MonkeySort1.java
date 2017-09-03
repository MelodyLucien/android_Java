package Set.HashSet;

/**
 * ��Ҫ��дequals��hashcode������compare to����������ʵ������
 * @author zhouhao
 */
public class MonkeySort1 implements Comparable<MonkeySort1>{
	
		private String name;
		private String sex;
	
		public MonkeySort1(String name, String sex) {
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
		
      @Override
	    public boolean equals(Object obj) {
	
	      MonkeySort1 monkey=(MonkeySort1)obj;
	      if (name.equals(monkey.name)) {
			return true;
		  }
	      else 
	    	  return false;
	    }

		
		
		@Override
		public int hashCode() {
		   int result=0;
		   result=System.identityHashCode(name);
		   return result;
		}

		/**
		 * ע���equalsҪһ�£������������ʱ��  �������
		 */
		@Override
		public int compareTo(MonkeySort1 o) {
			
		   return this.name.compareTo(o.name);
			
		}

		@Override
		public String toString() {
			return "MonkeySort1 [name=" + name + ", sex=" + sex + "]\n";
		}
}
