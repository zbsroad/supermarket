package com.jou.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jou.model.Inport;
import com.jou.utils.DBUtil;
import com.jou.utils.StringUtil;

public class InportDao {

	/**
	 * 查询所有进货单
	 * @return
	 * @throws Exception
	 */
	public List<Inport> getAll() throws Exception {
		StringBuffer sb = new StringBuffer("select a.*,b.name as supplier_name,c.name as goods_name from t_inport a "
				+ "inner join t_supplier b on a.sid = b.id "
				+ "inner join t_goods c on a.gid = c.id "
				+ "order by a.create_time asc");
		return DBUtil.getQueryList(sb.toString(), Inport.class);
	}
	
	/**
	 * 查询进货单
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<Inport> search(String name) throws Exception {
		List<Object> paramList = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select a.*,b.name as supplier_name,c.name as goods_name from t_inport a "
				+ "inner join t_supplier b on a.sid = b.id "
				+ "inner join t_goods c on a.gid = c.id "
				+ "where 1=1");
		if (!StringUtil.isEmpty(name)) {
			sb.append(" and c.name like ?");
			paramList.add("%"+name+"%");
		}
		sb.append(" order by a.create_time asc");
		return DBUtil.getQueryList(sb.toString(), paramList, Inport.class);
	}
	
	/**
	 * 保存进货单信息
	 * @param inport
	 * @return
	 * @throws Exception
	 */
	public int save(Inport inport) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(inport.getCode());
		paramList.add(inport.getSid());
		paramList.add(inport.getGid());
		paramList.add(inport.getQuantity());
		paramList.add(inport.getPrice());
		paramList.add(inport.getPrice() * inport.getQuantity());
		Date date = new Date();
		paramList.add(date);
		paramList.add(date);
		return DBUtil.execute("insert into t_inport(code,sid,gid,quantity,price,amount,create_time,update_time) values(?,?,?,?,?,?,?,?)", paramList);
	}
	
	/**
	 * 更新进货单信息
	 * @param inport
	 * @return
	 * @throws Exception
	 */
	public int update(Inport inport) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(inport.getCode());
		paramList.add(inport.getSid());
		paramList.add(inport.getGid());
		paramList.add(inport.getQuantity());
		paramList.add(inport.getPrice());
		paramList.add(inport.getPrice() * inport.getQuantity());
		paramList.add(new Date());
		paramList.add(inport.getId());
		return DBUtil.execute("update t_inport set code=?,sid=?,gid=?,quantity=?,price=?,amount=?,update_time=? where id=?", paramList);
	}
	
	/**
	 * 根据id查询进货单信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Inport getById(int id) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(id);
		return DBUtil.getObject("select * from t_inport where id=?", paramList, Inport.class);
	}
		
	/**
	 * 删除进货单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(int id) throws Exception{		
		List<Object> paramList = new ArrayList<>();
		paramList.add(id);
		return DBUtil.execute("delete from t_inport where id=?", paramList);
		
	}
		
	/**
	 * 根据入库单号查询进货单信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Inport getByCode(String code) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(code);
		return DBUtil.getObject("select * from t_inport where code=?", paramList, Inport.class);
	}
	
}