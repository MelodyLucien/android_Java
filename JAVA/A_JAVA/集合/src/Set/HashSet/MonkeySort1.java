package Set.HashSet;

/**
 * 需要重写equals和hashcode方法和compare to（）方法，实现排序
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
		 * 注意和equals要一致，否则会出现添加时的  奇怪现象
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
