import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SelectionModDemo extends JFrame implements ActionListener, ListSelectionListener{
	  private JPanel panel;
	  private JButton btnSin,btnSinIn,btnMul;
	  private JTable table;
	  //声明表格选择模式对象
	  private ListSelectionModel selectionModel;
	  private JScrollPane jScrollPane; 
	  private JLabel label;
	  public SelectionModDemo() {
		super("表格选择模式");
		panel=new JPanel();
		btnSin= new JButton("单一选择模式");
		btnSinIn=new JButton("连续区间选择");
		btnMul=new JButton("多重选择模式");
		btnMul.addActionListener(this);
		btnSin.addActionListener(this);
		btnSinIn.addActionListener(this);
		panel.add(btnSin);
		panel.add(btnSinIn);
		panel.add(btnMul);
		this.add(panel,BorderLayout.NORTH);
		String[] name={"字段1","字段2","字段3","字段4","字段5"};
		String[][] data=new String[5][5];
		int value=1;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				data[i][j]=String.valueOf(value++);
			}
			
		}
		table=new JTable(data, name);
		table.setPreferredScrollableViewportSize(new Dimension(400, 80));
		//设置表格的选择一cell为单位
		table.setCellSelectionEnabled(true);
		//获取表格的模式对象
		selectionModel=table.getSelectionModel();
		//注册选择事件监听
		selectionModel.addListSelectionListener(this);
		jScrollPane=new JScrollPane(table);
		this.add(jScrollPane);
		label=new JLabel("你选取了");
		this.add(jScrollPane,BorderLayout.CENTER);
		this.setSize(450,300);
		this.setLocation(100, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	  

	//处理按钮事件，设置选择模式
	@Override
	public void actionPerformed(ActionEvent e) {
	   Object source=e.getSource();
	  if(source==btnSin)
	  {
		  selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	  }
	  if(source==btnSinIn)
	{
		  selectionModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	}
	  if(source==btnMul)
	  {
		  selectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	  }
	  table.revalidate();
	}
	//当用户选取表格式数据时触发此方法
   @Override
	public void valueChanged(ListSelectionEvent e) {
		String temp="";
		//获取所有选中行的下标
		int[] rows=table.getSelectedRows();
		int[] colums=table.getSelectedColumns();
		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < colums.length; j++) {
				temp=temp+"  "+table.getValueAt(rows[i],colums[j]);
			}
		}
		label.setText(temp);
		this.add(label,BorderLayout.SOUTH);
		
	}
	public static void main(String[] args) {
		SelectionModDemo modDemo=new SelectionModDemo();
		modDemo.setVisible(true);
		
	}
}
