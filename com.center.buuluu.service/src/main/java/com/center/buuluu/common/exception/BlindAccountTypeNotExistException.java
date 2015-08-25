package com.center.buuluu.common.exception;

import com.center.buuluu.common.util.CodeStatus;
import com.center.buuluu.common.util.I18nUtil;

public class BlindAccountTypeNotExistException extends BuuluuAPIException {


	private static final long serialVersionUID = 3730032417693458386L;
	
	private static int errorCode = CodeStatus.BIIND_ACCOUNT_TYPE_NOT_EXIST_EXCEPTION;
	
	@Override
	public int getErrorCode() {
		return errorCode;
	}
	
	public BlindAccountTypeNotExistException(String langCode) {
		super(I18nUtil.getMessage(errorCode, null, langCode));
	}
}
