package com.jou.dao;

import java.util.ArrayList;
import java.util.List;

import com.jou.model.Admin;
import com.jou.utils.DBUtil;

public class AdminDao {
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean login(String username,String password) throws Exception{
		List<Object> paramList = new ArrayList<>();
		paramList.add(username);
		paramList.add(password);
		Admin admin = DBUtil.getObject("select * from t_admin where username=? and password=?", paramList,Admin.class);
		if(admin != null){
			return true;
		}
		return false;	
	}
	
}