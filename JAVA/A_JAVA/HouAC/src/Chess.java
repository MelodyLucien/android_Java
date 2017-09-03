public class Chess {
	int step = 1, count = 0;
	
	int imap[][];

	void check(int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && imap[x][y] == 0)// �жϴ˵��Ƿ��������ϲ���û�б���ֵ
		{
			imap[x][y] = step;
			step++;
			if (step > 64) {
				paint();
				imap[x][y] = 0; // ���˵�����
				step--;// ��step��Ϊ64
				return; // ���ص��������ô����ж���һ��λ��

			} else {
				check(x - 2, y - 1); // �˵���һ����8��λ��
				check(x - 1, y - 2);
				check(x + 1, y - 2);
				check(x + 2, y - 1);
				check(x + 2, y + 1);
				check(x + 1, y + 2);
				check(x - 1, y + 2);
				check(x - 2, y + 1);
				imap[x][y] = 0;

				step--;// �ڷ�����һ��֮ǰ��Ҫ��step��1��
				return; // ���ص���һ��

			}
		} else
			return; // �˵㲻���ϸ�ֵ���������ص��������ô����ж���һ��λ��

	}

	void paint() {
		count++;
		System.out.println("���ǵ�" + count + "�ַ���");
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
		System.out.println("�������塾8��8������\n��ı����߷�������" + "<" + horse.count + ">"
				+ "��");

	}

}