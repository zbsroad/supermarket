package com.jou.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jou.model.Outport;
import com.jou.utils.DBUtil;
import com.jou.utils.StringUtil;

public class OutportDao {

	/**
	 * 查询所有出库单
	 * @return
	 * @throws Exception
	 */
	public List<Outport> getAll() throws Exception {
		StringBuffer sb = new StringBuffer("select a.*,b.name as customer_name,c.name as goods_name from t_outport a "
				+ "inner join t_customer b on a.cid = b.id "
				+ "inner join t_goods c on a.gid = c.id "
				+ "order by a.create_time asc");
		return DBUtil.getQueryList(sb.toString(), Outport.class);
	}
	
	/**
	 * 查询出库单
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<Outport> search(String name) throws Exception {
		List<Object> paramList = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select a.*,b.name as customer_name,c.name as goods_name from t_outport a "
				+ "inner join t_customer b on a.cid = b.id "
				+ "inner join t_goods c on a.gid = c.id "
				+ "where 1=1");
		if (!StringUtil.isEmpty(name)) {
			sb.append(" and c.name like ?");
			paramList.add("%"+name+"%");
		}
		sb.append(" order by a.create_time asc");
		return DBUtil.getQueryList(sb.toString(), paramList, Outport.class);
	}
	
	/**
	 * 保存出库单信息
	 * @param outport
	 * @return
	 * @throws Exception
	 */
	public int save(Outport outport) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(outport.getCode());
		paramList.add(outport.getCid());
		paramList.add(outport.getGid());
		paramList.add(outport.getQuantity());
		paramList.add(outport.getPrice());
		paramList.add(outport.getPrice() * outport.getQuantity());
		Date date = new Date();
		paramList.add(date);
		paramList.add(date);
		return DBUtil.execute("insert into t_outport(code,cid,gid,quantity,price,amount,create_time,update_time) values(?,?,?,?,?,?,?,?)", paramList);
	}
	
	/**
	 * 更新出库单信息
	 * @param outport
	 * @return
	 * @throws Exception
	 */
	public int update(Outport outport) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(outport.getCode());
		paramList.add(outport.getCid());
		paramList.add(outport.getGid());
		paramList.add(outport.getQuantity());
		paramList.add(outport.getPrice());
		paramList.add(outport.getPrice() * outport.getQuantity());
		paramList.add(new Date());
		paramList.add(outport.getId());
		return DBUtil.execute("update t_outport set code=?,cid=?,gid=?,quantity=?,price=?,amount=?,update_time=? where id=?", paramList);
	}
	
	/**
	 * 根据id查询出库单信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Outport getById(int id) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(id);
		return DBUtil.getObject("select * from t_outport where id=?", paramList, Outport.class);
	}
		
	/**
	 * 删除出库单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(int id) throws Exception{		
		List<Object> paramList = new ArrayList<>();
		paramList.add(id);
		return DBUtil.execute("delete from t_outport where id=?", paramList);	
	}
		
	/**
	 * 根据出库单号查询出库单信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Outport getByCode(String code) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(code);
		return DBUtil.getObject("select * from t_outport where code=?", paramList, Outport.class);
	}	

}