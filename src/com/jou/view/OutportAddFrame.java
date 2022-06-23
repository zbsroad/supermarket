package com.jou.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jou.dao.CustomerDao;
import com.jou.dao.GoodsDao;
import com.jou.dao.InportDao;
import com.jou.dao.OutportDao;
import com.jou.dao.SupplierDao;
import com.jou.model.Customer;
import com.jou.model.Goods;
import com.jou.model.Inport;
import com.jou.model.Outport;
import com.jou.model.Supplier;
import com.jou.utils.CollectionUtils;
import com.jou.utils.DateUtil;
import com.jou.utils.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class OutportAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField codeText;
	private JTextField quantityText;

	private GoodsDao goodsDao = new GoodsDao();
	private CustomerDao customerDao = new CustomerDao();
	private OutportDao outportDao = new OutportDao();
	private JTextField priceText;


	/**
	 * Create the frame.
	 */
	public OutportAddFrame() {
		setTitle("商品出库");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 396, 351);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("出库单：");
		lblNewLabel.setBounds(23, 34, 64, 15);
		contentPane.add(lblNewLabel);
		
		codeText = new JTextField();
		codeText.setBounds(93, 31, 182, 21);
		contentPane.add(codeText);
		codeText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("客户：");
		lblNewLabel_1.setBounds(34, 84, 64, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("出库单价：");
		label.setBounds(10, 175, 77, 15);
		contentPane.add(label);
		
		priceText = new JTextField();
		priceText.setBounds(93, 172, 182, 21);
		contentPane.add(priceText);
		priceText.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("出库数量：");
		lblNewLabel_5.setBounds(10, 214, 77, 15);
		contentPane.add(lblNewLabel_5);
		
		quantityText = new JTextField();
		quantityText.setBounds(93, 211, 182, 21);
		contentPane.add(quantityText);
		quantityText.setColumns(10);
		
		JLabel lblp = new JLabel("示例：10001");
		lblp.setBounds(294, 34, 86, 15);
		contentPane.add(lblp);
		
		final JComboBox cidBox = new JComboBox();
		cidBox.setBounds(93, 81, 182, 21);
		List<Customer> customerList;
		try {
			customerList = customerDao.getAll();
			if(!CollectionUtils.isEmpty(customerList)){
				for(Customer  customer : customerList){
					cidBox.addItem(customer);
				}
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		contentPane.add(cidBox);
		
		
		JLabel label_1 = new JLabel("商品名称：");
		label_1.setBounds(10, 133, 77, 15);
		contentPane.add(label_1);
		
		final JComboBox gidBox = new JComboBox();
		gidBox.setBounds(93, 130, 182, 21);
		List<Goods> goodsList;
		try {
			goodsList = goodsDao.getAll();
			if(!CollectionUtils.isEmpty(goodsList)){
				for(Goods  goods : goodsList){
					gidBox.addItem(goods);
				}
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		contentPane.add(gidBox);
		
		JButton btnNewButton = new JButton("保存");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String code = codeText.getText();
					Customer c = cidBox.getSelectedItem() == null ? null : (Customer)cidBox.getSelectedItem();
					Goods g = gidBox.getSelectedItem() == null ? null : (Goods)gidBox.getSelectedItem();
					String quantity = quantityText.getText();
					String price = priceText.getText();
					if(StringUtil.isEmpty(code)){
						JOptionPane.showMessageDialog(contentPane, "请输入出库单号", "系统提示",JOptionPane.WARNING_MESSAGE);
						return;
					}else{
						Outport exist = outportDao.getByCode(code);;
						if(exist != null){
							JOptionPane.showMessageDialog(contentPane, "出库单号已存在，请重新输入", "系统提示",JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
					
					if(c == null){
						JOptionPane.showMessageDialog(contentPane, "请选择客户", "系统提示",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if(g == null){
						JOptionPane.showMessageDialog(contentPane, "请选择商品", "系统提示",JOptionPane.WARNING_MESSAGE);
						return;
					}
				
					if(StringUtil.isEmpty(quantity)){
						JOptionPane.showMessageDialog(contentPane, "请输入出库数量", "系统提示",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if(StringUtil.isEmpty(price)){
						JOptionPane.showMessageDialog(contentPane, "请输入出库单价", "系统提示",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					//查询商品信息
					Goods goods = goodsDao.getById(g.getId());
					Integer numbers = Integer.parseInt(quantity);
					if(goods.getStorage() < numbers){
						JOptionPane.showMessageDialog(contentPane, "库存不足！", "系统提示",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					Outport outport = new Outport();
					outport.setCode(code);
					outport.setCid(c.getId());
					outport.setGid(g.getId());
					outport.setQuantity(numbers);
					outport.setPrice(Double.valueOf(price));
					outport.setAmount(outport.getPrice()*outport.getQuantity());
					outportDao.save(outport);
					
					//更新商品数量
					goods.setStorage(goods.getStorage() - outport.getQuantity());
					goodsDao.update(goods);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "保存异常："+e1.getMessage(), "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(contentPane, "保存成功!");
				dispose();
			}
		});
		btnNewButton.setBounds(93, 254, 74, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(201, 254, 74, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		
	}
}
