package com.jou.model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 进货实体类
 * @author zj223
 *
 */
public class Inport {
	
	/**
	 * 进货单id
	 */
	private Integer id;
	
	/**
	 * 进货单号
	 */
	private String code;
	
	/**
	 * 供应商id
	 */
	private Integer sid;
	
	/**
	 * 商品id
	 */
	private Integer gid;
	
	/**
	 * 进货数量
	 */
	private Integer quantity;
	
	/**
	 * 进货单价
	 */
	private Double price;
	
	/**
	 * 进货总价
	 */
	private Double amount;
	
	/**
	 * 进货时间
	 */
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	private Date updateTime;
	
	/**
	 * 冗余字段 -- 供应商名称
	 */
	private String supplierName;
	
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

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
}