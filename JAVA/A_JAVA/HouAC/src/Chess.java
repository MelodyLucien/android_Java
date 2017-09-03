public class Chess {
	int step = 1, count = 0;
	
	int imap[][];

	void check(int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && imap[x][y] == 0)// 判断此点是否在棋盘上并且没有被赋值
		{
			imap[x][y] = step;
			step++;
			if (step > 64) {
				paint();
				imap[x][y] = 0; // 将此点清零
				step--;// 将step该为64
				return; // 返回到方法调用处，判断下一个位置

			} else {
				check(x - 2, y - 1); // 此点下一步的8个位置
				check(x - 1, y - 2);
				check(x + 1, y - 2);
				check(x + 2, y - 1);
				check(x + 2, y + 1);
				check(x + 1, y + 2);
				check(x - 1, y + 2);
				check(x - 2, y + 1);
				imap[x][y] = 0;

				step--;// 在返回上一层之前，要将step减1。
				return; // 返回到上一层

			}
		} else
			return; // 此点不符合赋值条件，返回到方法调用处，判断下一个位置

	}

	void paint() {
		count++;
		System.out.println("这是第" + count + "种方法");
		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {
				System.out.print(imap[i][j] + " ");
			}
			System.out.print("\n");
		}

	}

	public static void main(String[] args) {

		Chess horse = new Chess();
		horse.imap = new int[8][8];

		horse.check(0, 0);
		System.out.println("国际象棋【8×8】棋盘\n马的遍历走法：共有" + "<" + horse.count + ">"
				+ "种");

	}

}