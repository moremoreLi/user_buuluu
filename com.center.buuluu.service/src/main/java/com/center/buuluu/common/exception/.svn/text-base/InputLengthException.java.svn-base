package com.center.buuluu.common.exception;

import com.center.buuluu.common.util.CodeStatus;
import com.center.buuluu.common.util.I18nUtil;

public class InputLengthException extends BuuluuAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8892098598871221441L;

	private static int errorCode = CodeStatus.INPUT_LENGTH_EXCEPTION;

	public int getErrorCode() {
		return errorCode;
	}
	
	public InputLengthException(String langCode,String errorLength) {
		super(I18nUtil.getMessage(errorCode, new String[]{errorLength}, langCode));
	}
}
