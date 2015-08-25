package com.center.buuluu.common.exception;




/**
 * JSON序列化异常
 * 
 */
public class JsonException extends BaseAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 125088018455402885L;
	private int errorCode = ExceptionCodeStatusEnum.JOSN_EXCEPTION.toInt();
	public JsonException() {
		super();
	}

	public JsonException(String message, Throwable cause) {
		super(message, cause);
	}

	public JsonException(String message) {
		super(message);
	}

	public JsonException(Throwable cause) {
		super(cause);
	}

	@Override
	public int getErrorCode() {
		return errorCode;
	}

}
