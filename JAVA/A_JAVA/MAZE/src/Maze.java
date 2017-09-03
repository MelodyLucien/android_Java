import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.URISyntaxException;
import java.util.Stack;

import util.assistant.Helper;
import util.load_map.LoadMap;

public class Maze {
	/*
	 * ��ʼ���Թ�,1��ʾͨ��,0��ʾǽ
	 */

	public static int map[][] =null;

	/*{ { 1, 1, 0, 1, 1, 1, 0, 1 }, { 1, 1, 0, 1, 1, 1, 0, 1 },
			{ 1, 1, 1, 1, 0, 0, 1, 1 }, { 1, 0, 0, 0, 1, 1, 1, 1 },
			{ 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 0, 0, 0, 1, 0, 0, 1 }, { 0, 1, 1, 1, 1, 1, 1, 1 } };*/

	public void addPath(int map[][], Node start, Node end, Stack<Node> realP,
			Stack<Node> tryP) {
		Node cur = start;
		do {
			// �����ǰ��û���߹�
			if (unpass(cur, tryP)) {
				tryP.push(cur);
				realP.push(cur);
				cur = getNext(cur, tryP);
				if (cur.equals(end)) {
					tryP.push(cur);
					realP.push(cur);
					return;
				} else if (cur.arc == -1) {
					realP.pop();
					cur = realP.peek();
				}
			} else {// �߹���һ�����Է���
				cur = getNext(cur, tryP);
				if (cur.arc == -1) {
					realP.pop();
					cur = realP.peek();
				}
			}

		} while (!cur.equals(end));
	}

	// �ж��Ƿ��߹�
	public boolean unpass(Node node, Stack<Node> tryP) {
		boolean flag = true;
		// ע��contains������ʹ��
		while (tryP.contains(node)) {
			flag = false;
			break;
		}
		return flag;
	}

	// �õ���һ��λ�ã��Ӷ��濪ʼ˳ʱ�����
	public Node getNext(Node cur, Stack<Node> tryP) {
		Node temp = new Node();
		temp.x = temp.y = temp.arc = -1;
		if (getEast(cur).arc != 0 && unpass(getEast(cur), tryP))
			temp = getEast(cur);
		else if (getSouth(cur).arc != 0 && unpass(getSouth(cur), tryP)) {
			temp = getSouth(cur);
		} else if (getWest(cur).arc != 0 && unpass(getWest(cur), tryP)) {
			temp = getWest(cur);
		} else if (getNorth(cur).arc != 0 && unpass(getNorth(cur), tryP)) {
			temp = getNorth(cur);
		}

		// ������߹�����temp��arcֵΪ-1��
		return temp;
	}

	public Node getEast(Node cur) {
		Node next = null;
		if (cur.y != Edge.width) {
			next = new Node();
			next.x = cur.x;
			next.y = cur.y + 1;
			next.arc = map[next.x][next.y];
		} else {
			next = cur;
		}
		return next;
	}

	public Node getSouth(Node cur) {
		Node next = null;
		if (cur.x != Edge.height) {
			next = new Node();
			next.x = cur.x + 1;
			next.y = cur.y;
			next.arc = map[next.x][next.y];
		} else {
			next = cur;
		}
		return next;
	}

	public Node getWest(Node cur) {
		Node next = null;
		if (cur.y != 0) {
			next = new Node();
			next.x = cur.x;
			next.y = cur.y - 1;
			next.arc = map[next.x][next.y];
		} else {
			next = cur;
		}
		return next;
	}

	public Node getNorth(Node cur) {
		Node next = null;
		if (cur.x != 0) {
			next = new Node();
			next.x = cur.x - 1;
			next.y = cur.y;
			next.arc = map[next.x][next.y];
		} else {
			next = cur;
		}
		return next;
	}

	public void printPath(Stack<Node> realP) {
		if (realP.isEmpty()) {
			System.out.println("no path!!");
		} else {
			Node temp;
			System.out.println("the maze's path is:\n");
			while (!realP.isEmpty()) {
				temp = realP.pop();
				System.out.println(temp.x + "  " + temp.y);
			}

		}
	}

	public static void initiate(int scale, LoadMap loadMap) {
		if (scale != 0) {
			Edge.width = scale - 1;
			Edge.height = scale - 1;
			loadMap = new LoadMap(scale);
		} else {
			System.out.println("�޷���������");
		}
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException, URISyntaxException {
		Stack<Node> realP = new Stack<>();
		Stack<Node> tryP = new Stack<>();
		Maze maze = new Maze();
		// LoadMap loadMap=null;
		// ��ȡ��ͼ�Ĺ�ģ
		int scale = Helper.getScale();

		
		// initiate(scale, loadMap);
		Edge.width = scale - 1;
		Edge.height = scale - 1;
		LoadMap loadMap = new LoadMap(scale);
		Node start = new Node();
		Node end = new Node();
		
		
		map = loadMap.Load();
		// ��ʼ�ͽ������Թ�λ��
		start.x = loadMap.getxStart();
		start.y = loadMap.getyStart();

		end.x = loadMap.getxEnd();
		end.y = loadMap.getyEnd();
		
		start.arc = map[start.x][start.y];
		end.arc = map[end.x][end.y];
		
		// ��ʼ����

		maze.addPath(map, start, end, realP, tryP);
		start = maze.getNext(start, tryP);
		maze.printPath(realP);
	}
}
