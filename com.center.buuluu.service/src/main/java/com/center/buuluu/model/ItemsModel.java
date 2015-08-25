package com.center.buuluu.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class ItemsModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4774599561166848868L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("po_id")
	private String po_id;

	@JsonProperty("sku_no")
	private String sku_no;

	@JsonProperty("sku_name")
	private String sku_name;

	@JsonProperty("qty")
	private Integer qty;

	@JsonProperty("price")
	private String price;

	@JsonProperty("sale_sum")
	private Double saleSum;

	@JsonProperty("pay_sum")
	private Double paySum;

	@JsonProperty("order_discount")
	private String orderDiscount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPo_id() {
		return po_id;
	}

	public void setPo_id(String po_id) {
		this.po_id = po_id;
	}

	public String getSku_no() {
		return sku_no;
	}

	public void setSku_no(String sku_no) {
		this.sku_no = sku_no;
	}

	public String getSku_name() {
		return sku_name;
	}

	public void setSku_name(String sku_name) {
		this.sku_name = sku_name;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Double getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(Double saleSum) {
		this.saleSum = saleSum;
	}

	public Double getPaySum() {
		return paySum;
	}

	public void setPaySum(Double paySum) {
		this.paySum = paySum;
	}

	public String getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(String orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

}
