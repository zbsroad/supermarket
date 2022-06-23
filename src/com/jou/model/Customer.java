package com.jou.model;

/**
 * 客户实体类
 * @author zj223
 *
 */
public class Customer {
	
	/**
	 * 主键id
	 */
	private Integer id;
	
	
	/**
	 * 供应商名称
	 */
	private String name;
	
	/**
	 * 联系电话
	 */
	private String phone;
	
	/**
	 * 地址
	 */
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return name;
	}

}