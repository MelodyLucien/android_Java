package final用法;

public class Final {
	
	//public static  GrandSon grandSon;
	 
   public static void main(String[] args) {
	final Son son=new Son(13,"zhouyaho");
	//son=new Son(34, "袁国政");//error
	GrandSon grandSon=new GrandSon(13, "jiji");
	
}
}
