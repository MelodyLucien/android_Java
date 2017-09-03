
public class Packet {
	public static void main(String[] args) {
		String s="123";  //此时java会自动装箱
		
		int i=Integer.parseInt(s);
		
		i++;
		
		System.out.println(i);
	}

}
