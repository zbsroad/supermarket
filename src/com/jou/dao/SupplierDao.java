package com.jou.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jou.model.Supplier;
import com.jou.utils.DBUtil;
import com.jou.utils.StringUtil;

public class SupplierDao {

	/**
	 * 查询所有供应商
	 * @return
	 * @throws Exception
	 */
	public List<Supplier> getAll() throws Exception {
		return DBUtil.getQueryList("select * from t_supplier order by id asc", Supplier.class);
	}
	
	/**
	 * 条件查询
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<Supplier> search(String name) throws Exception {
		List<Object> paramList = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select * from t_supplier where 1=1");
		if (!StringUtil.isEmpty(name)) {
			sb.append(" and name like ?");
			paramList.add("%"+name+"%");
		}
		sb.append(" order by id asc");
		return DBUtil.getQueryList(sb.toString(), paramList, Supplier.class);
	}
	
	/**
	 * 保存供应商信息
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public int save(Supplier supplier) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(supplier.getName());
		paramList.add(supplier.getPhone());
		paramList.add(supplier.getAddress());
		return DBUtil.execute("insert into t_supplier(name,phone,address) values(?,?,?)", paramList);
	}
	
	/**
	 * 更新供应商信息
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public int update(Supplier supplier) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(supplier.getName());
		paramList.add(supplier.getPhone());
		paramList.add(supplier.getAddress());
		paramList.add(supplier.getId());
		return DBUtil.execute("update t_supplier set name=?,phone=?,address=? where id=?", paramList);
	}
	
	/**
	 * 根据id查询供应商信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Supplier getById(int id) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(id);
		return DBUtil.getObject("select * from t_supplier where id=?", paramList, Supplier.class);
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
		return DBUtil.execute("delete from t_supplier where id=?", paramList);		
	}

}