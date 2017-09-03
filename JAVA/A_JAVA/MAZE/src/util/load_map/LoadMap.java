package util.load_map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LoadMap {

	public static int SCALE = 0;
	private int xStart = 0;
	private int yStart = 0;
	private int xEnd = 0;
	private int yEnd = 0;

	int arr2[][] = null;
	String line = null; // 一行数据

	public LoadMap(int scale) {
		super();
		this.SCALE = scale;
		arr2 = new int[SCALE][SCALE];

	}

	public int[][] Load() throws NumberFormatException, IOException,
			URISyntaxException {

		String filename = "src/util/load_map/map_" + SCALE + ".txt";
		// URL url=LoadMap.class.getResource(filename);
		// File file=new File(url.toURI());

		File file = new File(filename);

		BufferedReader in = new BufferedReader(new FileReader(file));
		int row = 0;

		// 逐行读取地图，并将每个数组放入到数组中
		while ((line = in.readLine()) != null) {
			String[] temp = line.split("\t");
			if (row == SCALE) {
              //读取出口和入口顶点参数
				setxStart(Integer.parseInt(temp[0]));
				setyStart(Integer.parseInt(temp[1]));
				setxEnd(Integer.parseInt(temp[2]));
				setyEnd(Integer.parseInt(temp[3]));
				break;
			}

			for (int j = 0; j < temp.length; j++) {
				arr2[row][j] = Integer.parseInt(temp[j]);
			}
			row++;

		}

		in.close();

		// 显示读取出的数组
		for (int i = 0; i < SCALE; i++) {
			for (int j = 0; j < SCALE; j++) {
				System.out.print(arr2[i][j] + "\t");
			}
			System.out.println();
		}
		return arr2;
	}

	public int getxStart() {
		return xStart;
	}

	public void setxStart(int xStart) {
		this.xStart = xStart;
	}

	public int getyStart() {
		return yStart;
	}

	public void setyStart(int yStart) {
		this.yStart = yStart;
	}

	public int getxEnd() {
		return xEnd;
	}

	public void setxEnd(int xEnd) {
		this.xEnd = xEnd;
	}

	public int getyEnd() {
		return yEnd;
	}

	public void setyEnd(int yEnd) {
		this.yEnd = yEnd;
	}

}
