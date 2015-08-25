package com.center.buuluu.model;

import java.io.Serializable;

/**
 * CustomerSingleJson Object
 * 
 * @author KevinWang
 *
 */
public class CustomerSingleJsonModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5187863892083873659L;

	private String customerCode;
	private String customerName;
	private Boolean sex;
	private String email;
	private String mobilePhone;
	private String officePhoneZoneCode;
	private String officePhone;
	private String extension;
	private String homePhoneZoneCode;
	private String homePhone;
	private Integer customerLevel;
	private Integer customerAges;
	private Integer childrenAges;
	private String remark;
	private String firstOrderdt;
	private String lastOrderdt;
	private Integer score;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOfficePhoneZoneCode() {
		return officePhoneZoneCode;
	}

	public void setOfficePhoneZoneCode(String officePhoneZoneCode) {
		this.officePhoneZoneCode = officePhoneZoneCode;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getHomePhoneZoneCode() {
		return homePhoneZoneCode;
	}

	public void setHomePhoneZoneCode(String homePhoneZoneCode) {
		this.homePhoneZoneCode = homePhoneZoneCode;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public Integer getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(Integer customerLevel) {
		this.customerLevel = customerLevel;
	}

	public Integer getCustomerAges() {
		return customerAges;
	}

	public void setCustomerAges(Integer customerAges) {
		this.customerAges = customerAges;
	}

	public Integer getChildrenAges() {
		return childrenAges;
	}

	public void setChildrenAges(Integer childrenAges) {
		this.childrenAges = childrenAges;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFirstOrderdt() {
		return firstOrderdt;
	}

	public void setFirstOrderdt(String firstOrderdt) {
		this.firstOrderdt = firstOrderdt;
	}

	public String getLastOrderdt() {
		return lastOrderdt;
	}

	public void setLastOrderdt(String lastOrderdt) {
		this.lastOrderdt = lastOrderdt;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
