package com.center.buuluu.common.exception;

import com.center.buuluu.common.util.CodeStatus;
import com.center.buuluu.common.util.I18nUtil;

public class ActCodeException extends BuuluuAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5144552833232708385L;
	
	
	private static int errorCode = CodeStatus.ACTCODE_EXCEPTION;
	
	@Override
	public int getErrorCode() {
		return errorCode;
	}
	
	public ActCodeException(String langCode) {
		super(I18nUtil.getMessage(errorCode, null, langCode));
	}

}
