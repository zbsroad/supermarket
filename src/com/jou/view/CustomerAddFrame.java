package com.jou.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jou.dao.CustomerDao;
import com.jou.dao.SupplierDao;
import com.jou.model.Customer;
import com.jou.model.Supplier;
import com.jou.utils.DateUtil;
import com.jou.utils.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CustomerAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameText;
	private JTextField phoneText;
	private JTextField addressText;

	private CustomerDao customerDao = new CustomerDao();


	/**
	 * Create the frame.
	 */
	public CustomerAddFrame() {
		setTitle("新增客户信息");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 351);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("客户名称：");
		lblNewLabel.setBounds(29, 34, 92, 15);
		contentPane.add(lblNewLabel);
		
		nameText = new JTextField();
		nameText.setBounds(113, 31, 182, 21);
		contentPane.add(nameText);
		nameText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("联系电话：");
		lblNewLabel_1.setBounds(29, 84, 92, 15);
		contentPane.add(lblNewLabel_1);
		
		phoneText = new JTextField();
		phoneText.setBounds(113, 81, 182, 21);
		contentPane.add(phoneText);
		phoneText.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("客户地址：");
		lblNewLabel_5.setBounds(29, 148, 91, 15);
		contentPane.add(lblNewLabel_5);
		
		addressText = new JTextField();
		addressText.setBounds(113, 145, 182, 21);
		contentPane.add(addressText);
		addressText.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("保存");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				String phone = phoneText.getText();
				String address = addressText.getText();

				if(StringUtil.isEmpty(name)){
					JOptionPane.showMessageDialog(contentPane, "请输入客户名称", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(StringUtil.isEmpty(phone)){
					JOptionPane.showMessageDialog(contentPane, "请输入联系电话", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
			
				if(StringUtil.isEmpty(address)){
					JOptionPane.showMessageDialog(contentPane, "请输入客户地址", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Customer customer = new Customer();
				customer.setName(name);
				customer.setPhone(phone);
				customer.setAddress(address);
				try {
					customerDao.save(customer);
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
		btnNewButton.setBounds(113, 215, 74, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(220, 215, 74, 23);
		contentPane.add(btnNewButton_1);	
	}
	
}