package com.center.buuluu.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class JsonResultModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1198044666278350225L;

	@JsonProperty("firmwareName")
	private String firmwareName;

	@JsonProperty("firmwareVersion")
	private String firmwareVersion;

	@JsonProperty("firmwareMd5")
	private String firmwareMd5;

	@JsonProperty("firmwareUrl")
	private String firmwareUrl;

	@JsonProperty("firmwareType")
	private String firmwareType;

	public String getFirmwareName() {
		return firmwareName;
	}

	public void setFirmwareName(String firmwareName) {
		this.firmwareName = firmwareName;
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public String getFirmwareMd5() {
		return firmwareMd5;
	}

	public void setFirmwareMd5(String firmwareMd5) {
		this.firmwareMd5 = firmwareMd5;
	}

	public String getFirmwareUrl() {
		return firmwareUrl;
	}

	public void setFirmwareUrl(String firmwareUrl) {
		this.firmwareUrl = firmwareUrl;
	}

	public String getFirmwareType() {
		return firmwareType;
	}

	public void setFirmwareType(String firmwareType) {
		this.firmwareType = firmwareType;
	}

}
