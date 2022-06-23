package com.jou.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class AboutUs extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUs frame = new AboutUs();
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
	public AboutUs() {
		setTitle("关于我们");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPowerBy = new JLabel("宠物超市管理系统");
		lblPowerBy.setFont(new Font("楷体", Font.PLAIN, 20));
		lblPowerBy.setBounds(62, 49, 221, 45);
		contentPane.add(lblPowerBy);
		
		JLabel lblWwwxiaoniucrcom = new JLabel("地址：青岛科技大学崂山校区");
		lblWwwxiaoniucrcom.setFont(new Font("楷体", Font.PLAIN, 20));
		lblWwwxiaoniucrcom.setBounds(62, 102, 298, 35);
		contentPane.add(lblWwwxiaoniucrcom);
		
		JLabel lblNewLabel = new JLabel("联系方式：15066960727");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		lblNewLabel.setBounds(62, 158, 239, 22);
		contentPane.add(lblNewLabel);

		JLabel lblName = new JLabel("联系人：张斌");
		lblName.setFont(new Font("楷体", Font.PLAIN, 20));
		lblName.setBounds(62, 200, 239,22 );
		contentPane.add(lblName);
	}
}