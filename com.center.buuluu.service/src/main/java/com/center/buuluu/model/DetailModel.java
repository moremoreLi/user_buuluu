package com.center.buuluu.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Details Object
 * 
 * @author KevinWang
 *
 */
public class DetailModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4532007852218846624L;

	@JsonProperty("oid")
	private String oid;

	@JsonProperty("tid")
	private String tid;

	@JsonProperty("sku")
	private String sku;

	@JsonProperty("skuName")
	private String skuName;

	@JsonProperty("saleSum")
	private Double saleSum;

	@JsonProperty("orderSum")
	private Integer orderSum;

	@JsonProperty("orderDiscount")
	private String orderDiscount;

	@JsonProperty("paySum")
	private Double paySum;

	@JsonProperty("isDestine")
	private Boolean isDestine;

	@JsonProperty("redictTime")
	private String redictTime;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Double getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(Double saleSum) {
		this.saleSum = saleSum;
	}

	public Integer getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(Integer orderSum) {
		this.orderSum = orderSum;
	}

	public String getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(String orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public Double getPaySum() {
		return paySum;
	}

	public void setPaySum(Double paySum) {
		this.paySum = paySum;
	}

	public Boolean getIsDestine() {
		return isDestine;
	}

	public void setIsDestine(Boolean isDestine) {
		this.isDestine = isDestine;
	}

	public String getRedictTime() {
		return redictTime;
	}

	public void setRedictTime(String redictTime) {
		this.redictTime = redictTime;
	}

}
