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
	      
	      //实例化树的根节点
	      root=makeSimpleTree();
	      
	      //实例化数的模型
	      model=new DefaultTreeModel(root);
	      
	      //实例化一棵树
	      tree=new JTree(model);
	      
	      //注册树的监听对象，监听选择不同的树节点
	      tree.addTreeSelectionListener(this);
	      //设置树的选择模式为单一节点的选择模式
	      tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	      //实例化一个面板对象，布局是一行两列
	      p=new JPanel(new GridLayout(1,2));
	      //在面板的左侧放置树
	      p.add(new JScrollPane(tree));
	      textArea =new JTextArea();
	      //在面板的右侧放置文本域
	      p.add(new JScrollPane(textArea));
	      this.add(p);
	      this.setSize(350, 400);
	      this.setLocation(100,100);
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

	private DefaultMutableTreeNode makeSimpleTree() {
	     //实例化树节点，并将节点添加到相应节点中
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("世界");
		DefaultMutableTreeNode country=new DefaultMutableTreeNode("中国");
		root.add(country);
		DefaultMutableTreeNode state1=new DefaultMutableTreeNode("山东");
		DefaultMutableTreeNode state2=new DefaultMutableTreeNode("济南");
		DefaultMutableTreeNode state3=new DefaultMutableTreeNode("江苏");
		DefaultMutableTreeNode state4=new DefaultMutableTreeNode("南京");
		DefaultMutableTreeNode state5=new DefaultMutableTreeNode("青岛");
		DefaultMutableTreeNode state6=new DefaultMutableTreeNode("安徽");
		country.add(state1);
		country.add(state2);
		country.add(state3);
		state3.add(state4);
		country.add(state5);
		country.add(state6);
		
		return root;
	}
	
	//重写树的选择事件处理方法
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		//获取选中节点的路径
		TreePath path=tree.getSelectionPath();
		if(path==null)
		{
			return ;
		}
		//获取选中的节点对象
		DefaultMutableTreeNode node=(DefaultMutableTreeNode) path.getLastPathComponent();
		//获取选中的节点的内容，并显示到文本域中
		textArea.setText(node.getUserObject().toString());
		}
	
	public static void main(String[] args) {
		new JTreeDemo().setVisible(true);
	}
     
}
