package com.center.buuluu.common.exception;

import com.center.buuluu.common.util.CodeStatus;
import com.center.buuluu.common.util.I18nUtil;

public class ExistMobileException extends BuuluuAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1079977826116980538L;

	private static int errorCode = CodeStatus.EXIST_MOBILE_EXCEPTION;

	public int getErrorCode() {
		return errorCode;
	}
	
	public ExistMobileException(String langCode) {
		super(I18nUtil.getMessage(errorCode, null, langCode));
	}

}
