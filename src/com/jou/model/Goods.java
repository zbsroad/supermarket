package com.jou.model;

/**
 * 商品实体类
 * @author zj223
 *
 */
public class Goods {
	
	/**
	 * 商品id
	 */
	private Integer id;
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 商品价格
	 */
	
	private String price;
	
	/**
	 * 商品库存单位
	 */
	private String unit;
	
	/**
	 * 库存量
	 */
	private Integer storage;
	
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getStorage() {
		return storage;
	}

	public void setStorage(Integer storage) {
		this.storage = storage;
	}
	
	public String toString() {
		return name;
	}
	
}