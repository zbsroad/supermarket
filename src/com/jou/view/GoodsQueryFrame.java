package com.jou.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.jou.dao.GoodsDao;
import com.jou.dao.SupplierDao;
import com.jou.model.Goods;
import com.jou.model.Supplier;

public class GoodsQueryFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameText;
	private JScrollPane scrollPane;
	private JTable table;
	
	private GoodsDao goodsDao = new GoodsDao();
	private JButton button;
	private JButton button_1;
	
	private GoodsQueryFrame father =this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodsQueryFrame frame = new GoodsQueryFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GoodsQueryFrame() {
		setTitle("查询商品信息");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 672, 383);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品名称：");
		lblNewLabel.setBounds(10, 10, 89, 15);
		contentPane.add(lblNewLabel);
		
		nameText = new JTextField();
		nameText.setBounds(94, 7, 106, 21);
		contentPane.add(nameText);
		nameText.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = nameText.getText();
				List<Goods> list;
				try {
					list = goodsDao.search(name);
					fillTable(list);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "查询异常："+e1.getMessage(), "系统提示",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(210, 6, 69, 23);
		contentPane.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 636, 268);
		contentPane.add(scrollPane);
		
		Object[] columns={"ID","商品名称","零售价","库存量","单位"};//字段
		Object[][] data= null;//需要展示的数据，一般是二维数组
		DefaultTableModel model=new DefaultTableModel(data, columns);
		table = new JTable(model);
	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		table.setDefaultRenderer(Object.class, tcr);
		try {
			fillTable(goodsDao.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
		
		button = new JButton("删除");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取选中行
				int row = table.getSelectedRow();
				if(row < 0){
					JOptionPane.showMessageDialog(contentPane, "请选择要删除的行数据", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				int result = JOptionPane.showConfirmDialog(contentPane, "确认删除？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
				if(result == 0){
					int id = Integer.valueOf(table.getValueAt(row, 0).toString());
					try {
						int rows = goodsDao.delete(id);
						if(rows >=1){
							JOptionPane.showMessageDialog(contentPane, "删除成功!");
							fillTable(goodsDao.getAll());
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(contentPane, "删除失败", "系统提示",JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}
		});
		button.setBounds(570, 6, 76, 23);
		contentPane.add(button);
		
		button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//获取选中行
				int row = table.getSelectedRow();
				if(row < 0){
					JOptionPane.showMessageDialog(contentPane, "请选择要修改的行数据", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				int id = Integer.valueOf(table.getValueAt(row, 0).toString());
				GoodsUpdateFrame update = new GoodsUpdateFrame(id,father);
				update.setVisible(true);
			}
		});
		button_1.setBounds(478, 6, 76, 23);
		contentPane.add(button_1);
	}
	
	
	public void fillTable(List<Goods> list){
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);// 清除原有行
		// 填充数据
		for(Goods item : list){
			String[] arr=new String[8];
			arr[0]= item.getId()+"";
			arr[1]= item.getName();
			arr[2]= item.getPrice();
			arr[3]= item.getStorage()+"";
			arr[4]= item.getUnit()+"";
			// 添加数据到表格
			tableModel.addRow(arr);
		}
	}
}
