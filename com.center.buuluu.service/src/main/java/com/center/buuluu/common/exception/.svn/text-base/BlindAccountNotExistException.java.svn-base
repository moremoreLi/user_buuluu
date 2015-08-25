package com.center.buuluu.common.exception;

import com.center.buuluu.common.util.CodeStatus;
import com.center.buuluu.common.util.I18nUtil;

public class BlindAccountNotExistException extends BuuluuAPIException {


	private static final long serialVersionUID = 3730032417693458386L;
	
	private static int errorCode = CodeStatus.BIIND_ACCOUNT_NOT_EXIST_EXCEPTION;
	
	@Override
	public int getErrorCode() {
		return errorCode;
	}
	
	public BlindAccountNotExistException(String langCode) {
		super(I18nUtil.getMessage(errorCode, null, langCode));
	}
}
