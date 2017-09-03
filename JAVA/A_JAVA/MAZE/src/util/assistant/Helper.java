package util.assistant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Helper {
	static int scale;

	public static int getScale() {
		// BufferedReader reader=new BufferedReader(new
		// InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);

		do {

			scale = scanner.nextInt();

		} while (scale> 16 || scale < 4);

		return scale;
	}
}
