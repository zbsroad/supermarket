package com.jou.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jou.dao.GoodsDao;
import com.jou.model.Goods;
import com.jou.model.Supplier;
import com.jou.utils.StringUtil;

public class GoodsUpdateFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameText;
	private JTextField priceText;
	private JTextField unitText;
	private JTextField storageText;
	private GoodsDao goodsDao = new GoodsDao();
	private static int id = 0;

	/**
	 * Create the frame.
	 */
	public GoodsUpdateFrame(int gid,final GoodsQueryFrame father) {
		id = gid;
		setTitle("修改商品信息");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 351);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("商品名称：");
		lblNewLabel.setBounds(29, 34, 92, 15);
		contentPane.add(lblNewLabel);

		nameText = new JTextField();
		nameText.setBounds(100, 31, 182, 21);
		contentPane.add(nameText);
		nameText.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("零售价：");
		lblNewLabel_1.setBounds(39, 84, 64, 15);
		contentPane.add(lblNewLabel_1);

		priceText = new JTextField();
		priceText.setBounds(100, 81, 182, 21);
		contentPane.add(priceText);
		priceText.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("库存单位：");
		lblNewLabel_5.setBounds(29, 127, 74, 15);
		contentPane.add(lblNewLabel_5);

		unitText = new JTextField();
		unitText.setBounds(100, 124, 182, 21);
		contentPane.add(unitText);
		unitText.setColumns(10);
		
		JLabel label = new JLabel("库存量：");
		label.setBounds(37, 173, 54, 15);
		contentPane.add(label);
		
		storageText = new JTextField();
		storageText.setBounds(100, 170, 182, 21);
		contentPane.add(storageText);
		storageText.setColumns(10);

		// 查询信息
		Goods goods = null;
		try {
			goods = goodsDao.getById(id);
			nameText.setText(goods.getName());
			priceText.setText(goods.getPrice());
			unitText.setText(goods.getUnit());
			storageText.setText(goods.getStorage()+"");

		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JButton btnNewButton = new JButton("保存");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String name = nameText.getText();
					String price = priceText.getText();
					String unit = unitText.getText();
					String storage = storageText.getText();
					if (StringUtil.isEmpty(name)) {
						JOptionPane.showMessageDialog(contentPane, "请输入商品名称", "系统提示", JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (StringUtil.isEmpty(price)) {
						JOptionPane.showMessageDialog(contentPane, "请输入零售价", "系统提示", JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (StringUtil.isEmpty(unit)) {
						JOptionPane.showMessageDialog(contentPane, "请输入库存单位", "系统提示", JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (StringUtil.isEmpty(storage)) {
						JOptionPane.showMessageDialog(contentPane, "请输入库存量", "系统提示", JOptionPane.WARNING_MESSAGE);
						return;
					}

					Goods goods = new Goods();
					goods.setId(id);
					goods.setName(name);
					goods.setPrice(price);
					goods.setUnit(unit);
					goods.setStorage(Integer.parseInt(storage));
					goodsDao.update(goods);
					
					JOptionPane.showMessageDialog(contentPane, "保存成功!");
					dispose();
					father.fillTable(goodsDao.getAll());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "保存异常：" + e1.getMessage(), "系统提示",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			}
		});
		btnNewButton.setBounds(100, 215, 74, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(208, 215, 74, 23);
		contentPane.add(btnNewButton_1);

		

		

	}
}
