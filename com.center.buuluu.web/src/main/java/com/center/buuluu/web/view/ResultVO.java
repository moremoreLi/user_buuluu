package com.center.buuluu.web.view;

import com.center.buuluu.common.util.CodeStatus;

public class ResultVO<T> {

	private String status = CodeStatus.SUCCESS;
	private String errorMessage = "";
	/*JSON 返回做了+ -分數 主鍵ID*/
	private T result;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
