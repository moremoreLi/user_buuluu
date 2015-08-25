package com.center.buuluu.common.exception;

import com.center.buuluu.common.util.CodeStatus;

/**
 * 
 * @author carlye
 */
public class InvalidArgumentException extends BuuluuAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidArgumentException() {
		super();
	}

	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidArgumentException(String message) {
		super(message);
	}

	public InvalidArgumentException(Throwable cause) {
		super(cause);
	}

	@Override
	public int getErrorCode() {
		return CodeStatus.ILLEGAL_ARGUMENT;
	}

}
