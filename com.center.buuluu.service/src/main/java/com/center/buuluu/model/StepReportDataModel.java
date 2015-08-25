package com.center.buuluu.model;

import java.io.Serializable;

public class StepReportDataModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6288646566598437260L;

	private Integer steps;
	private Float hour;
	private Integer calories;
	private Float kilometer;
	private String date;
	private String uuid;
	private String wristbandId;

	public Integer getSteps() {
		return steps==null?0:steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}

	public Float getHour() {
		return hour;
	}

	public void setHour(Float hour) {
		this.hour = hour;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Float getKilometer() {
		return kilometer;
	}

	public void setKilometer(Float kilometer) {
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

	public String getWristbandId() {
		return wristbandId;
	}

	public void setWristbandId(String wristbandId) {
		this.wristbandId = wristbandId;
	}
}
