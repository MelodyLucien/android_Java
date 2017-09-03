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
	  //�������ѡ��ģʽ����
	  private ListSelectionModel selectionModel;
	  private JScrollPane jScrollPane; 
	  private JLabel label;
	  public SelectionModDemo() {
		super("���ѡ��ģʽ");
		panel=new JPanel();
		btnSin= new JButton("��һѡ��ģʽ");
		btnSinIn=new JButton("��������ѡ��");
		btnMul=new JButton("����ѡ��ģʽ");
		btnMul.addActionListener(this);
		btnSin.addActionListener(this);
		btnSinIn.addActionListener(this);
		panel.add(btnSin);
		panel.add(btnSinIn);
		panel.add(btnMul);
		this.add(panel,BorderLayout.NORTH);
		String[] name={"�ֶ�1","�ֶ�2","�ֶ�3","�ֶ�4","�ֶ�5"};
		String[][] data=new String[5][5];
		int value=1;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				data[i][j]=String.valueOf(value++);
			}
			
		}
		table=new JTable(data, name);
		table.setPreferredScrollableViewportSize(new Dimension(400, 80));
		//���ñ���ѡ��һcellΪ��λ
		table.setCellSelectionEnabled(true);
		//��ȡ����ģʽ����
		selectionModel=table.getSelectionModel();
		//ע��ѡ���¼�����
		selectionModel.addListSelectionListener(this);
		jScrollPane=new JScrollPane(table);
		this.add(jScrollPane);
		label=new JLabel("��ѡȡ��");
		this.add(jScrollPane,BorderLayout.CENTER);
		this.setSize(450,300);
		this.setLocation(100, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	  

	//����ť�¼�������ѡ��ģʽ
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
	//���û�ѡȡ���ʽ����ʱ�����˷���
   @Override
	public void valueChanged(ListSelectionEvent e) {
		String temp="";
		//��ȡ����ѡ���е��±�
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
