import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


public class JTreeDemo extends JFrame implements TreeSelectionListener{
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private JTree tree;
	private JTextArea textArea;
	private JPanel p;
	public JTreeDemo() {
	      super("TreeBrowerTest");
	      
	      //ʵ�������ĸ��ڵ�
	      root=makeSimpleTree();
	      
	      //ʵ��������ģ��
	      model=new DefaultTreeModel(root);
	      
	      //ʵ����һ����
	      tree=new JTree(model);
	      
	      //ע�����ļ������󣬼���ѡ��ͬ�����ڵ�
	      tree.addTreeSelectionListener(this);
	      //��������ѡ��ģʽΪ��һ�ڵ��ѡ��ģʽ
	      tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	      //ʵ����һ�������󣬲�����һ������
	      p=new JPanel(new GridLayout(1,2));
	      //��������������
	      p.add(new JScrollPane(tree));
	      textArea =new JTextArea();
	      //�������Ҳ�����ı���
	      p.add(new JScrollPane(textArea));
	      this.add(p);
	      this.setSize(350, 400);
	      this.setLocation(100,100);
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

	private DefaultMutableTreeNode makeSimpleTree() {
	     //ʵ�������ڵ㣬�����ڵ���ӵ���Ӧ�ڵ���
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("����");
		DefaultMutableTreeNode country=new DefaultMutableTreeNode("�й�");
		root.add(country);
		DefaultMutableTreeNode state1=new DefaultMutableTreeNode("ɽ��");
		DefaultMutableTreeNode state2=new DefaultMutableTreeNode("����");
		DefaultMutableTreeNode state3=new DefaultMutableTreeNode("����");
		DefaultMutableTreeNode state4=new DefaultMutableTreeNode("�Ͼ�");
		DefaultMutableTreeNode state5=new DefaultMutableTreeNode("�ൺ");
		DefaultMutableTreeNode state6=new DefaultMutableTreeNode("����");
		country.add(state1);
		country.add(state2);
		country.add(state3);
		state3.add(state4);
		country.add(state5);
		country.add(state6);
		
		return root;
	}
	
	//��д����ѡ���¼�������
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		//��ȡѡ�нڵ��·��
		TreePath path=tree.getSelectionPath();
		if(path==null)
		{
			return ;
		}
		//��ȡѡ�еĽڵ����
		DefaultMutableTreeNode node=(DefaultMutableTreeNode) path.getLastPathComponent();
		//��ȡѡ�еĽڵ�����ݣ�����ʾ���ı�����
		textArea.setText(node.getUserObject().toString());
		}
	
	public static void main(String[] args) {
		new JTreeDemo().setVisible(true);
	}
     
}
