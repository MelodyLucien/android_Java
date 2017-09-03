
public class Queen {
	int num; // ��¼������
	int[] queenline = new int[8]; // ��¼8���ʺ���ռ�õ��к�
	boolean[] col = new boolean[8]; // �а�ȫ��־
	boolean[] diagonal = new boolean[16]; // �Խ��߰�ȫ��־
	boolean[] undiagonal = new boolean[16]; // ���Խ��߰�ȫ��־

	void solve(int i) {
		for (int j = 0; j < 8; j++) {
			if (col[j] && diagonal[i - j + 7] && undiagonal[i + j]) {
				// ��ʾ��i�е�j���ǰ�ȫ�Ŀ��ԷŻʺ�
				queenline[i - 1] = j + 1;
				col[j] = false; // �޸İ�ȫ��־
				diagonal[i - j + 7] = false;
				undiagonal[i + j] = false;
				if (i < 8) // �ж��Ƿ����8���ʺ�
				{
					solve(i + 1); // δ����8���ʺ����������һ��
				} else // �Ѿ�����8���ʺ�
				{
					num++;
					System.out.println("\n�ʺ�ڷŵ�" + num + "�ַ���:");
					System.out.println("�зֱ�Ϊ1 2 3 4 5 6 7 8 ");
					System.out.print("�зֱ�Ϊ");
					for (int i1 = 0; i1 < 8; i1++)
						System.out.print(queenline[i1] + " ");
				}
				col[j] = true; // �޸İ�ȫ��־������
				diagonal[i - j + 7] = true;
				undiagonal[i + j] = true;
			}
		}
	}

	public static void main(String[] args) {
		Queen q = new Queen();
		System.out.println("////�˻ʺ�����////");
		q.num = 0; // ������ʼ��
		for (int i = 0; i < 8; i++)
			// ��������Ϊ��ȫ
			q.col[i] = true;
		for (int i0 = 0; i0 < 16; i0++)
			// �����жԽ���Ϊ��ȫ
			q.diagonal[i0] = q.undiagonal[i0] = true;
		q.solve(1);
	}

}
