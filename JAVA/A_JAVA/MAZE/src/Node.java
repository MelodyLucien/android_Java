public class Node {
	int x;
	int y;
	int arc;

	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof Node){
		Node old = (Node) arg0;
		if ((this.x == old.x) && (this.y == old.y) && (this.arc == old.arc)) {
			return true;
		} else
			return false;
		}else
			return false;
		

	}
  //ֻ����ʹ��hashcode�ı����в�Ӧ��дhashcode()����������ʹ֮�ȶԳɹ�
	

}
