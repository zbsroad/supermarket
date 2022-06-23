package com.jou.dao;

import java.util.ArrayList;
import java.util.List;

import com.jou.model.Customer;
import com.jou.utils.DBUtil;
import com.jou.utils.StringUtil;

public class CustomerDao {

	/**
	 * 查询所有客户
	 * @return
	 * @throws Exception
	 */	
	public List<Customer> getAll() throws Exception {
		return DBUtil.getQueryList("select * from t_customer order by id asc", Customer.class);
	}
	
	/**
	 * 条件查询
	 * @param name
	 * @return
	 * @throws Exception
	 */	
	public List<Customer> search(String name) throws Exception {
		List<Object> paramList = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select * from t_customer where 1=1");
		if (!StringUtil.isEmpty(name)) {
			sb.append(" and name like ?");
			paramList.add("%"+name+"%");
		}
		sb.append(" order by id asc");
		return DBUtil.getQueryList(sb.toString(), paramList, Customer.class);
	}
	
	/**
	 * 保存客户信息
	 * @param student
	 * @return
	 * @throws Exception
	 */	
	public int save(Customer Customer) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(Customer.getName());
		paramList.add(Customer.getPhone());
		paramList.add(Customer.getAddress());
		return DBUtil.execute("insert into t_customer(name,phone,address) values(?,?,?)", paramList);
	}
	
	/**
	 * 更新客户信息
	 * @param student
	 * @return
	 * @throws Exception
	 */	
	public int update(Customer Customer) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(Customer.getName());
		paramList.add(Customer.getPhone());
		paramList.add(Customer.getAddress());
		paramList.add(Customer.getId());
		return DBUtil.execute("update t_customer set name=?,phone=?,address=? where id=?", paramList);
	}
	
	/**
	 * 根据id查询客户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Customer getById(int id) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(id);
		return DBUtil.getObject("select * from t_customer where id=?", paramList, Customer.class);
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
		return DBUtil.execute("delete from t_customer where id=?", paramList);		
	}
	
}