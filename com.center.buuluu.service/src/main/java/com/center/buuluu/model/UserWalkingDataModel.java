package com.center.buuluu.model;

import java.io.Serializable;

public class UserWalkingDataModel implements Serializable{

	/**
	 */
	private static final long serialVersionUID = 7749997415079245717L;
	private Integer allTotalSteps;
	private  Double allTotalHours;
	private Integer allTotalCalories;
	private Double allTotalKilometers;
	public Integer getAllTotalSteps() {
		return allTotalSteps;
	}
	public void setAllTotalSteps(Integer allTotalSteps) {
		this.allTotalSteps = allTotalSteps;
	}
	public Double getAllTotalHours() {
		return allTotalHours;
	}
	public void setAllTotalHours(Double allTotalHours) {
		this.allTotalHours = allTotalHours;
	}
	public Integer getAllTotalCalories() {
		return allTotalCalories;
	}
	public void setAllTotalCalories(Integer allTotalCalories) {
		this.allTotalCalories = allTotalCalories;
	}
	public Double getAllTotalKilometers() {
		return allTotalKilometers;
	}
	public void setAllTotalKilometers(Double allTotalKilometers) {
		this.allTotalKilometers = allTotalKilometers;
	}
}
