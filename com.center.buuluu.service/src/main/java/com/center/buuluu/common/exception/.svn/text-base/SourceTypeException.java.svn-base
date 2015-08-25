package com.center.buuluu.common.exception;

import com.center.buuluu.common.util.CodeStatus;
import com.center.buuluu.common.util.I18nUtil;

public class SourceTypeException extends BuuluuAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1079977826116980538L;

	private static int errorCode = CodeStatus.SOURCE_TYPE_EXCEPTION;

	public int getErrorCode() {
		return errorCode;
	}
	
	public SourceTypeException(String langCode) {
		super(I18nUtil.getMessage(errorCode, null, langCode));
	}

}
