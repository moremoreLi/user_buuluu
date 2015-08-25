package com.center.buuluu.model;

import java.io.Serializable;

public class GroupWalkingDataModel implements Serializable{

	/**
	 */
	private static final long serialVersionUID = -7861042220032814408L;
	private Double sumHours;
	private Double sumKilometers;
	private Integer sumCalories;
	private  Integer sumSteps;
	public Double getSumHours() {
		return sumHours;
	}
	public void setSumHours(Double sumHours) {
		this.sumHours = sumHours;
	}
	public Double getSumKilometers() {
		return sumKilometers;
	}
	public void setSumKilometers(Double sumKilometers) {
		this.sumKilometers = sumKilometers;
	}
	public Integer getSumCalories() {
		return sumCalories;
	}
	public void setSumCalories(Integer sumCalories) {
		this.sumCalories = sumCalories;
	}
	public Integer getSumSteps() {
		return sumSteps;
	}
	public void setSumSteps(Integer sumSteps) {
		this.sumSteps = sumSteps;
	}
}
