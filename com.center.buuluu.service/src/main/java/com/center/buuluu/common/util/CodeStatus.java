package com.center.buuluu.common.util;

/**
 * web service 系统错误状态码
 * 
 */
public class CodeStatus {
    public static final int SUCCESS_STATUS=1;
    
    public static final int FAILED_STATUS=0;
	// success
	public static final int SUCCESS = 0;
	// internal server error
	public static final int UNKNOWN = -1;
	// Input parameter error
	public static final int ILLEGAL_ARGUMENT = -2;
	
	public static final int JOSN_EXCEPTION=-3;
	
	public static final int FILTER_EXCEPTION=-4;
	
	public static final int ACCOUNT_TYPE_EXCEPTION=-5;
	
	public static final int REGISTER_EXCEPTION=-6;
	
	public static final int USER_SESSION_EXCEPTION=-7;
	
	public static final int DMP_API_EXCEPTION=-8;
	
	public static final int PARAMETER_EXIST_EXCEPTION=-9;
	
	public static final int VERSION_UPDATE_EXCEPTION =-10;
	
	/*// page not found
	public static final int PAGE_NOT_FOUND = 404;
	// Forbidden
	public static final int FORBIDDEN = 403;
	// File Upload Type Error
	public static final int FILE_UPLOAD_TYPE_ERROR = 406;
	// Invalid designId
	public static final int INVALID_DESIGNID = 409;
	// Invalid user session.
	public static final int INVALID_USER_SESSION = 410;
	// User not found.
	public static final int USER_NOT_FOUND = 411;
	// Invalid URL
	public static final int INVALID_URL = 420;
	// Invalid deviceType
	public static final int INVALID_DEVICE_TYPE = 421;*/
	
	public static final int USER_NOTEXIST_EXCEPTION=-101;
	
	public static final int WRONG_PASSWORD_EXCEPTION=-102;
	
	public static final int EXIST_EMAIL_EXCEPTION=-103;
	
	public static final int BIND_SNS_EXCEPTION=-104;
	/*
	 * 手机账号存在，注册的时候使用
	 * EXIST_MOBILE_EXCEPTION
	 */
	public static final int EXIST_MOBILE_EXCEPTION=-105;
	
	public static final int EXIST_SNSACCOUNT_EXCEPTION=-106;
	
	public static final int USER_FOLLOW_EXCEPTION=-107;
	
	public static final int REGISTER_BIND_SNS_EXCEPTION=-108;
	
	public static final int LAST_SNAPSHOT_TIME_EXCEPTION =-109;
	
	public static final int EXIST_USER_REPORT_EXCEPTION = -110;
	
	public static final int USER_ACCOUNT_EXCEPTION = -111;
	
	public static final int USER_FOLLOW_TYPE_EXCEPTION = -112;
	
	public static final int USER_CANCEL_FOLLOW_TYPE_EXCEPTION = -113;
	
	public static final int EXIST_REDEEM_ITEM_EXCEPTION = -114;
	
	public static final int USER_ACTION_MAP_NOT_FOUND_EXCEPTION=-115;
	
	public static final int USER_ACTION_NOT_FOUND_EXCEPTION=-116;

	public static final int ACTION_NOT_FOUND_EXCEPTION=-117;
	
	public static final int TEAM_ACTION_DEL_EXCEPTION=-118;
	
	public static final int USER_ACTION_MAP_STATUS_NOT_ACTIVE_EXCEPTION=-119;
	
	public static final int ONE_OFF_TASK_ITEM_EXCEPTION=-120;
	
	public static final int MULIPLE_TASK_ITEM_EXCEPTION=-121;
	
	public static final int EVERY_DATE_COMPLETE_EXCEPTION=-122;
	
	public static final int CREATE_TASK_EXCEPTION=-123;
	
	public static final int TASK_START_AND_END_DATE_EXCEPTION=-124;
	
	public static final int TASK_START_DATE_EXCEPTION=-125;
	
	public static final int ADD_MP_EXCEPTION=-126;
	
	public static final int INVALID_EMAIL_EXCEPTION = -127;
	
	public static final int INVALID_MOBILE_EXCEPTION = -128;
	
	public static final int NICKNAME_WORDS_EXCEPTION = -129;
	
	public static final int BALANCE_ENOUGH_EXCEPTION = -130;
	
	public static final int REDEEM_EXCEPTION = -131;
	
	public static final int USER_ACTION_MAP_STATUS_EXPIRED=-132;
	
	public static final int COMPLETE_FINISHED_TASK_EXCEPTION=-133;
	
	public static final int USER_STATUS_EXCEPTION = -134;
	
	public static final int INVALID_POINT_DONATION_EXCEPTION = -135;
	
	public static final int EMAIL_OR_MOBILE_EXIST = -136;
	
	public static final int USER_PLANET_IAMGE_EXIST = -137;
	
	public static final int USER_COMPLETE_OTHERS_TASK_EXCEPTION = -138;
	
	public static final int EMAIL_OR_MOBILE_NOT_EMPTY = -139;
	
	public static final int UPLOAD_EXCEPTION = -140;
	
	public static final int UPLOAD_FILE_EXCEPTION = -141;
	
	public static final int INPUT_STEPS_EXCEPTION = -142;
	
	public static final int INPUT_DEVICE_EXCEPTION = -143;
	
	public static final int UPDATE_FAILED_EXCEPTION = -144;
	
	public static final int ROUTE_NOTEXIST_EXCEPTION = -145;
	
	public static final int POINT_NOTEXIST_EXCEPTION = -146;
	
	public static final int DUPLICATE_LINEID_EXCEPTION = -147;
	
	public static final int WRONG_TOKEN_EXCEPTION = -148;
	
	public static final int SAVE_OR_UPDATE_EXCEPTION = -149;
	
	public static final int EDIT_ORDERSTATUS_EXCEPTION = -150;
	
	public static final int SEND_CUSTOMER_DETAIL_EXCEPTION = -151;
	
	public static final int SEND_INVOICEJSON_EXCEPTION = -152;

	public static int VERIFY_ACTIVATION_PASS_EXCEPTION;

	public static int SOURCE_TYPE_EXCEPTION;

	public static int SOURCE_NOT_EXIST_EXCEPTION;

	public static int SIGN_EXIST_EXCEPTION;

	public static int NOT_VERIFY_ACTIVATION_EXCEPTION;

	public static int SCRATCH_NOT_EXIST_EXCEPTION;

	public static int INPUT_LENGTH_EXCEPTION;

	public static int FLOW_COIN_NOT_ENOUGH_EXCEPTION;

	public static int COIN_USE_NOT_MATCH_EXCEPTION;

	public static int BIIND_ACCOUNT_TYPE_NOT_EXIST_EXCEPTION;

	public static int BIIND_ACCOUNT_NOT_EXIST_EXCEPTION;

	public static int BILL_NOT_EXIST_EXCEPTION;

	public static int BILL_EXIST_EXCEPTION;

	public static int BILL_EXPIRE_EXCEPTION;

	public static int ACTCODE_EXCEPTION;
}
