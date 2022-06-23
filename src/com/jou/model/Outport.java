package com.jou.model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 出库单实体类
 * @author zj223
 *
 */
public class Outport {
	
	/**
	 * 出库单id
	 */
	private Integer id;
	
	/**
	 * 出库单号
	 */
	private String code;
	
	/**
	 * 客户id
	 */
	private Integer cid;
	
	/**
	 * 商品id
	 */
	private Integer gid;
	
	/**
	 * 出库数量
	 */
	private Integer quantity;
	
	/**
	 * 出库单价
	 */
	private Double price;
	
	/**
	 * 出库总价
	 */
	private Double amount;
	
	/**
	 * 出库时间
	 */
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	private Date updateTime;
	
	/**
	 * 冗余字段 -- 客户姓名
	 */
	private String customerName;
	
	/**
	 * 冗余字段 -- 商品名称
	 */
	private String goodsName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

}