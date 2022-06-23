package com.jou.dao;

import java.util.ArrayList;
import java.util.List;

import com.jou.model.Goods;
import com.jou.utils.DBUtil;
import com.jou.utils.StringUtil;

public class GoodsDao {

	/**
	 * 查询所有商品
	 * @return
	 * @throws Exception
	 */	
	public List<Goods> getAll() throws Exception {
		return DBUtil.getQueryList("select * from t_goods order by id asc", Goods.class);
	}
	
	/**
	 * 条件查询
	 * @param name
	 * @return
	 * @throws Exception
	 */	
	public List<Goods> search(String name) throws Exception {
		List<Object> paramList = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select * from t_goods where 1=1");
		if (!StringUtil.isEmpty(name)) {
			sb.append(" and name like ?");
			paramList.add("%"+name+"%");
		}
		sb.append(" order by id asc");
		return DBUtil.getQueryList(sb.toString(), paramList, Goods.class);
	}
	
	/**
	 * 保存商品信息
	 * @param student
	 * @return
	 * @throws Exception
	 */	
	public int save(Goods goods) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(goods.getName());
		paramList.add(goods.getPrice());
		paramList.add(goods.getUnit());
		paramList.add(goods.getStorage());
		return DBUtil.execute("insert into t_goods(name,price,unit,storage) values(?,?,?,?)", paramList);
	}
	
	/**
	 * 更新商品信息
	 * @param student
	 * @return
	 * @throws Exception
	 */	
	public int update(Goods goods) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(goods.getName());
		paramList.add(goods.getPrice());
		paramList.add(goods.getUnit());
		paramList.add(goods.getStorage());
		paramList.add(goods.getId());
		return DBUtil.execute("update t_goods set name=?,price=?,unit=?,storage=? where id=?", paramList);
	}
	
	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 * @throws Exception
	 */	
	public Goods getById(int id) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(id);
		return DBUtil.getObject("select * from t_goods where id=?", paramList, Goods.class);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(int id) throws Exception{		
		List<Object> paramList = new ArrayList<>();
		paramList.add(id);
		return DBUtil.execute("delete from t_goods where id=?", paramList);	
	}	

}