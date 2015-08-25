package com.center.buuluu.common.util;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author kelvin.tie
 */
public class Constant {
	
	public static final String 	UPLOAD_PATH ="PATH";
	public static final String CREATE_BY_API="API_CREATE";
	public static final String UPDATE_BY_API="API_UPDATE";
	public static final String CREATE_BY_SCHEDULE_API="API_SCHEDULE_CREATE";
	public static final String UPDATE_BY_SCHEDULE_API="API_SCHEDULE_UPDATE";
	public static final String TASK_UPLOAD_IMAGE_PATH="TASK_UPLOAD_IMAGE_PATH";
	public static final String API1_LINK_IMAGE_PATH="API1_LINK_IMAGE_PATH";
	public static final String API2_LINK_IMAGE_PATH="API2_LINK_IMAGE_PATH";
	public static final String CMS_LINK_IMAGE_PATH="CMS_LINK_IMAGE_PATH";
	public static final String USER_ICON_UPLOAD_PATH="USER_ICON_UPLOAD_PATH";
	public static final String IMAGE_SERVER_LINK_IMAGE_PATH="IMAGE_SERVER_LINK_IMAGE_PATH";
	public static final String API_APP_DB_PATH ="API_APP_DB_PATH";
	
	
	public static final String DMP_API_KEY="DMP_API_KEY";
	public static final String DMP_CAMPAIGN_SERVICE_URL="DMP_CAMPAIGN_SERVICE_URL";
	public static final String DMP_POINT_SERVICE_URL="DMP_POINT_SERVICE_URL";
	public static final String DMP_USER_SERVICE_URL="DMP_USER_SERVICE_URL";
	
	public static final String 	EXECUTE_SCHEDULE="EXECUTE_SCHEDULE";

	public static final String VALIDATION_EMAIL="[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
	
	public static final String VALIDATION_MOBILE="^[1-9]{1}[0-9]{10}$";
	
	public static final String IOS_VERSION_NUM = "1.2.0";
	
	public static final String AOS_VERSION_NUM ="1.2.3";

	
	
	public static final int BANNER_SIZE = 0;
	public static final int PAGE_NUM = 0;
	public static String getImageURL(Date createTime,String appUrl,String imageUrl){
		//5min 取得image serverPath ,否則 cep api path
		if(createTime==null){
			return appUrl;
		}
		if(DateUtil.getCurrentDate().compareTo(DateUtil.getAfterMin(createTime, 10))==-1){
			return appUrl;
		}else{
			return imageUrl;
		}
	}
	
	public static String getAPIImageUrl(String imageUrl){
		
		if(StringUtils.isBlank(imageUrl)){
			return null;
		}
		String imagePath= PropertiesUtil.getProperty(API1_LINK_IMAGE_PATH);
		if (imagePath.indexOf("app1")!=-1) {
			imagePath=PropertiesUtil.getProperty(API1_LINK_IMAGE_PATH);
		}else if (imagePath.indexOf("app2")!=-1) {
			imagePath=PropertiesUtil.getProperty(API2_LINK_IMAGE_PATH);
		}
		
		return imagePath + imageUrl;

	}
	
	public static String getCMSImageUrl(String imageUrl){
		
		if(StringUtils.isBlank(imageUrl)){
			return null;
		}
		
		String imagePath=PropertiesUtil.getProperty(CMS_LINK_IMAGE_PATH);
		
		return imagePath + imageUrl;

	}
	
	public static String getImageServerImageUrl(String imageUrl){
		
		if(StringUtils.isBlank(imageUrl)){
			return null;
		}
		
		String imagePath=PropertiesUtil.getProperty(IMAGE_SERVER_LINK_IMAGE_PATH);
		
		return imagePath + imageUrl;

	}

	public static String getUUID(){
		String pkId = UUID.randomUUID().toString().replace("-", "");
		return pkId;
	}

	public static String getTempUserId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").replace(uuid.toString().substring(0, 6), "000000");
	}

	public static String getSessionId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

}
