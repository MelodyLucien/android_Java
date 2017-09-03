public class CreatRandom {
	public static void main(String[] args) {

		String str = null;
		int x = (int) (Math.random() * 4) + 1;
		if (x == 1) {
			str = "A";
		}
		if (x == 2) {
			str = "B";
		}
		if (x == 3) {
			str = "C";
		}
		if (x == 4) {
			str = "D";
		}
		System.out.println(str + ((int) (Math.random() * 5) + 1));
	}
}
