package com.center.buuluu.model;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 2727612503629396628L;
	
	protected Object id;
	
	protected Date createdTime;

	protected String createdBy;
	
	protected Date updatedTime;

	protected String updatedBy;
	
	protected Boolean isDeleted = false;
	
/*	public Object getKey() {
		return id;
	}*/
	
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
