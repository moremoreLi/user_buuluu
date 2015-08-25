package com.center.buuluu.model;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * OrderSingleJson Object
 * 
 * @author KevinWang
 *
 */
@JsonIgnoreProperties(value = { "tid", "customerCode", "accountName", "orderWay", "receiver", "receiverProvince", "receiverCity", "receiverDistrict", "receiverAddress", "receiverMobile", "receiverrTel", "receiverPostCode", "buyerRemark", "sellerrRemark", "orderTime", "payTime", "confirmTime",
		"orderDiscount", "ship", "payType", "orderSum", "realSum", "transPortType", "predictTime", "orderStatus", "statusTime", "details" })
public class OrderSingleJsonModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7375875843512657813L;

	@JsonProperty("TID")
	private String tid;

	@JsonProperty("CustomerCode")
	private String customerCode;

	@JsonProperty("AccountName")
	private String accountName;

	@JsonProperty("OrderWay")
	private String orderWay;

	@JsonProperty("Receiver")
	private String receiver;

	@JsonProperty("ReceiverProvince")
	private String receiverProvince;

	@JsonProperty("ReceiverCity")
	private String receiverCity;

	@JsonProperty("ReceiverDistrict")
	private String receiverDistrict;

	@JsonProperty("ReceiverAddress")
	private String receiverAddress;

	@JsonProperty("ReceiverMobile")
	private String receiverMobile;

	@JsonProperty("ReceiverrTel")
	private String receiverrTel;

	@JsonProperty("ReceiverPostCode")
	private String receiverPostCode;

	@JsonProperty("BuyerRemark")
	private String buyerRemark;

	@JsonProperty("SellerrRemark")
	private String sellerrRemark;

	@JsonProperty("OrderTime")
	private String orderTime;

	@JsonProperty("PayTime")
	private String payTime;

	@JsonProperty("ConfirmTime")
	private String confirmTime;

	@JsonProperty("OrderDiscount")
	private String orderDiscount;

	@JsonProperty("Ship")
	private String ship;

	@JsonProperty("PayType")
	private String payType;

	@JsonProperty("OrderSum")
	private Double orderSum;

	@JsonProperty("RealSum")
	private Double realSum;

	@JsonProperty("TransPortType")
	private String transPortType;

	@JsonProperty("PredictTime")
	private String predictTime;

	@JsonProperty("OrderStatus")
	private String orderStatus;

	@JsonProperty("StatusTime")
	private String statusTime;

	@JsonProperty("Details")
	private List<DetailModel> details;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOrderWay() {
		return orderWay;
	}

	public void setOrderWay(String orderWay) {
		this.orderWay = orderWay;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverrTel() {
		return receiverrTel;
	}

	public void setReceiverrTel(String receiverrTel) {
		this.receiverrTel = receiverrTel;
	}

	public String getReceiverPostCode() {
		return receiverPostCode;
	}

	public void setReceiverPostCode(String receiverPostCode) {
		this.receiverPostCode = receiverPostCode;
	}

	public String getBuyerRemark() {
		return buyerRemark;
	}

	public void setBuyerRemark(String buyerRemark) {
		this.buyerRemark = buyerRemark;
	}

	public String getSellerrRemark() {
		return sellerrRemark;
	}

	public void setSellerrRemark(String sellerrRemark) {
		this.sellerrRemark = sellerrRemark;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(String orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Double getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(Double orderSum) {
		this.orderSum = orderSum;
	}

	public Double getRealSum() {
		return realSum;
	}

	public void setRealSum(Double realSum) {
		this.realSum = realSum;
	}

	public String getTransPortType() {
		return transPortType;
	}

	public void setTransPortType(String transPortType) {
		this.transPortType = transPortType;
	}

	public String getPredictTime() {
		return predictTime;
	}

	public void setPredictTime(String predictTime) {
		this.predictTime = predictTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}

	public List<DetailModel> getDetails() {
		return details;
	}

	public void setDetails(List<DetailModel> details) {
		this.details = details;
	}

}
