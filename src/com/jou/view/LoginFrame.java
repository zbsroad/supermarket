package com.jou.view;
import java.util.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jou.dao.AdminDao;
import com.jou.utils.StringUtil;

import java.awt.Color;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField unameText;
	private JPasswordField pwdText;
	
	private AdminDao userDao = new AdminDao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("宠物超市信息管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("系统登录");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel.setBounds(177, 32, 108, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("账号：");
		lblNewLabel_1.setBounds(98, 89, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("密码：");
		lblNewLabel_2.setBounds(98, 152, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		unameText = new JTextField();
		unameText.setBounds(148, 86, 166, 21);
		contentPane.add(unameText);
		unameText.setColumns(10);
		
		pwdText = new JPasswordField();
		pwdText.setBounds(148, 149, 166, 21);
		contentPane.add(pwdText);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = unameText.getText();
				String password = pwdText.getText();
				if(StringUtil.isEmpty(username)){
					JOptionPane.showMessageDialog(contentPane, "请输入账号", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(StringUtil.isEmpty(password)){
					JOptionPane.showMessageDialog(contentPane, "请输入密码", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				try {
					boolean flag = userDao.login(username,password);
					if(flag){
						//跳转主界面
						JOptionPane.showMessageDialog(contentPane, "登录成功!");
						MainFrame main = new MainFrame();
						main.setVisible(true);
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane, "用户名密码错误!", "系统提示",JOptionPane.WARNING_MESSAGE);
						return;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "登录异常："+e1.getMessage(), "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			}
		});
		btnNewButton.setBounds(146, 202, 76, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(237, 202, 76, 23);
		contentPane.add(btnNewButton_1);		
	}
	
}