package com.jou.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("宠物超市信息管理系统");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 444);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("商品管理");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("查询商品");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoodsQueryFrame queryFrame = new GoodsQueryFrame();
				queryFrame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem menuItem = new JMenuItem("新增商品");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoodsAddFrame addFrame = new GoodsAddFrame();
				addFrame.setVisible(true);
			}
		});
		mnNewMenu.add(menuItem);
		
		JMenu mnNewMenu_1 = new JMenu("出库管理");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem menuItem_7 = new JMenuItem("出库查询");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutportQueryFrame queryFrame = new OutportQueryFrame();
				queryFrame.setVisible(true);
			}
		});
		mnNewMenu_1.add(menuItem_7);
		
		JMenuItem menuItem_1 = new JMenuItem("商品出库");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutportAddFrame frame = new OutportAddFrame();
				frame.setVisible(true);
			}
		});
		mnNewMenu_1.add(menuItem_1);
		
		JMenu menu = new JMenu("入库管理");
		menuBar.add(menu);
		
		JMenuItem menuItem_6 = new JMenuItem("入库查询");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InportQueryFrame queryFrame = new InportQueryFrame();
				queryFrame.setVisible(true);
			}
		});
		menu.add(menuItem_6);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("商品入库");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InportAddFrame addFrame = new InportAddFrame();
				addFrame.setVisible(true);
			}
		});
		menu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_3 = new JMenu("客户管理");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem menuItem_4 = new JMenuItem("查询客户");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerQueryFrame queryFrame = new CustomerQueryFrame();
				queryFrame.setVisible(true);
			}
		});
		mnNewMenu_3.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("新增客户");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerAddFrame addFrame = new CustomerAddFrame();
				addFrame.setVisible(true);
			}
		});
		mnNewMenu_3.add(menuItem_5);
		
		JMenu mnNewMenu_2 = new JMenu("供应商管理");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem menuItem_2 = new JMenuItem("查询供应商");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierQueryFrame queryFrame = new SupplierQueryFrame();
				queryFrame.setVisible(true);
			}
		});
		mnNewMenu_2.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("新增供应商");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierAddFrame addFrame = new SupplierAddFrame();
				addFrame.setVisible(true);
			}
		});
		mnNewMenu_2.add(menuItem_3);
		
		JMenu mnNewMenu_4 = new JMenu("关于我们");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("关于我们");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutUs us = new AboutUs();
				us.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_2);
		
		JMenuItem menuItem_8 = new JMenuItem("退出系统");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
			
		mnNewMenu_4.add(menuItem_8);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("宠物超市信息管理系统");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(161, 143, 276, 82);
		contentPane.add(lblNewLabel);
		
	}
}