package com.center.buuluu.common.exception;

import com.center.buuluu.common.util.CodeStatus;
import com.center.buuluu.common.util.I18nUtil;

public class NotVerifyActivationException extends BuuluuAPIException {


	private static final long serialVersionUID = 3730032417693458386L;
	
	private static int errorCode = CodeStatus.NOT_VERIFY_ACTIVATION_EXCEPTION;
	
	@Override
	public int getErrorCode() {
		return errorCode;
	}
	
	public NotVerifyActivationException(String langCode) {
		super(I18nUtil.getMessage(errorCode, null, langCode));
	}
}
