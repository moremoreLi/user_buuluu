package com.center.buuluu.model;

import java.io.Serializable;

public class StepReportModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8101198036035706506L;
	
	private Integer steps;
	private Double hour;
	private Integer calories;
	private Double kilometer;
	private String date;
	private String uuid;
	private Integer lightNum;
	
	public static final String STEP_REPORT_KEY ="STEP_REPORT_KEY"; 
	public Integer getSteps() {
		return steps;
	}
	public void setSteps(Integer steps) {
		this.steps = steps;
	}
	public Double getHour() {
		return hour;
	}
	public void setHour(Double hour) {
		this.hour = hour;
	}
	public Integer getCalories() {
		return calories;
	}
	public void setCalories(Integer calories) {
		this.calories = calories;
	}
	public Double getKilometer() {
		return kilometer;
	}
	public void setKilometer(Double kilometer) {
		this.kilometer = kilometer;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getLightNum() {
		return lightNum==null?0:lightNum;
	}
	public void setLightNum(Integer lightNum) {
		this.lightNum=lightNum;
	}
}
