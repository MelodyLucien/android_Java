package runtime;

public class Runtimer {
	public static void main(String[] args) {
		Runtime r=Runtime.getRuntime();
		try {
			r.exec("notepad.exe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
