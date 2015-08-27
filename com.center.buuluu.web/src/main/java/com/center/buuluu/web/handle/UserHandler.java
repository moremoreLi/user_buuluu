package com.center.buuluu.web.handle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.center.buuluu.common.exception.BaseAPIException;
import com.center.buuluu.common.util.Constant;
import com.center.buuluu.common.util.DateUtil;
import com.center.buuluu.common.util.I18nUtil;
import com.center.buuluu.common.util.Json;
import com.center.buuluu.common.util.PropertiesUtil;
import com.center.buuluu.model.AppUser;
import com.center.buuluu.model.AppUserSession;
import com.center.buuluu.service.UserService;
import com.center.buuluu.service.UserSessionService;
import com.center.buuluu.util.HttpClientUtils;
import com.center.buuluu.web.view.UserVO;

/**
 * user用户表的相关
 * @author More
 *2015-7-17
 */
@Component
public class UserHandler {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSessionService userSessionService;
	
//	@Autowired
//	private SmsService smsService;
//	
//	boolean flag=false;
	private static final String SMS_DOMAIN= PropertiesUtil.getProperty("SMS_DOMAIN");
	
//	AppUserSession userSession=null;
	//registerType 快速注册类型，1是普通注册，2是快速注册
	/**
	 * @param user
	 * @param lang
	 * @param device
	 * @param imei
	 * @param mac
	 * @param imsi
	 * @param registerType
	 * @param vistorId
	 * @return
	 * @throws SQLException
	 */
	public UserVO register(AppUser user, String countryCode, String tel,
			String pwd, String activation, Float flowCoins, Integer pushStatus,
			Double lat, Double log, String lang, Integer device, String imei,
			String imsi, String mac, int  registerType) throws SQLException {
		
		UserVO vo  = null;
		boolean usersessionAddFlag=false;
		String token="";
		String userId=user.getId();
		  //注册用户信息
		boolean flag =userService.register(user,countryCode,tel,pwd,pushStatus,log,lat,flowCoins,registerType);
		if (flag) {
			token =Constant.getSessionId();
			usersessionAddFlag = userSessionService.addSession(userId,lang,device,imei,mac,imsi,token);//在当前活动的用户表中添加该用户信息
		}
		if (usersessionAddFlag) {
			vo = new UserVO();
			vo.setToken(token);
			vo.setUserId(userId);
			vo.setUserFlow(user.getUserFlow());
			vo.setFlowCoins(user.getFlowCoins());
			vo.setMakeFlow(user.getMakeFlow());
			vo.setCredit(user.getCredit());
			vo.setNickName(user.getNickName());
			vo.setIcon(user.getIcon());
			vo.setCountry(user.getCountry());
			vo.setBirthday(user.getBirthday());
			vo.setLat(user.getLat());
			vo.setLog(user.getLog());
			vo.setTel(user.getTel());
		}else{
			throw new BaseAPIException();
		}
		
		return vo;
	}
	
	public UserVO login(String lang,Integer  device,String imei,String mac,String imsi,AppUser user) throws SQLException {
		String userId = user.getId().toString();
		UserVO vo = null;
		String token =Constant.getSessionId();
		boolean flag =userSessionService.addSession(userId,lang,device,imei,mac,imsi,token);
		if (flag) {
			vo = new UserVO();
			vo.setUserId(userId);
			vo.setToken(token);
			vo.setBirthday(user.getBirthday());
			vo.setIcon(user.getIcon());
			vo.setLat(user.getLat());
			vo.setLog(user.getLog());
			vo.setNickName(user.getNickName());
			vo.setTel(user.getTel());
			vo.setSex(user.getSex());
			vo.setFlowCoins(user.getFlowCoins());
			vo.setMakeFlow(user.getMakeFlow());
			vo.setUserFlow(user.getUserFlow());
			vo.setCredit(user.getCredit());
		}else {
			throw new BaseAPIException();
		}
		return vo;
	}

	/*
	 * msisdn（必选）： 下发手机号码，可以为一个或多个，多个用逗号隔开。以国家码开头，形如 66880889858, 0880889858. 如果手机号以0开头，则countrycode一定为必选。

        countrycode（可选）： 国家电话号码区号，以非0非+数字开头。

        msg（必选）：下发内容本身，需要对内容进行urlencode，例如msg=Test%20Msg。
        http://58.96.173.161:8081/notifyCentreServer/sms?countrycode=66&msisdn=0805723615&msg=Test%20Msg
	 */
	public boolean update(AppUser user,String lang,String countryCode,String tel) throws Exception {
		 boolean flag = false;
		ResourceBundleMessageSource s = new ResourceBundleMessageSource();
		s.setBasename("i18n/message");
		String msg = s.getMessage("subject", null,I18nUtil.getLocale(lang));
		String autoCode = getAutCode(6);
		
		msg = msg.replaceAll("autoCode", autoCode);
		Map<String,String> params = new HashMap<String, String>();
		params.put("countrycode", countryCode);
		params.put("msg", msg);
		params.put("msisdn",tel );
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String result = HttpClientUtils.postMethodRequest(SMS_DOMAIN, params);
			System.out.println("result="+result);
			resultMap = Json.toMap(result);
		} catch (Exception e) {
			//因为国内发送不了sms，暂时定到都成功
			resultMap.put("status", "200");
			//因为发送短信机制用不了，设定短信为1234
			autoCode = "1234";
		}
		
		if(resultMap.get("status").toString().equals("200")){//表示发送验证码成功
			if(!countryCode.equals("66")){//因为发送短信机制用不了，设定短信为1234
				autoCode = "1234";
			}
				user = new AppUser();
				user.setTelApproveKey(autoCode);
				user.setTelValueFlag(1);
				user.setTel(tel);
				user.setCountryCode(countryCode);
				user.setCreatedBy(Constant.CREATE_BY_API);
				user.setCreatedTime(DateUtil.getCurrentDate());
				user.setId(Constant.getUUID());
				user.setFlowCoins(0F);
				user.setMakeFlow(0F);
				user.setUserFlow(0F);
				user.setSex(1);
				user.setCredit(0);
				user.setPoint(0);
				user.setBirthday(-28800000L+"");//默认原始时间 1970-01-01
				user.setIcon("");
				user.setCountry("");
				user.setNickName("");
				user.setLog(0D);
				user.setLat(0D);
				flag = userService.addUser(user);
				if (flag) {
					return true;
				}else{
					return false;
				}
		}
		return false;
	}
	
	private  String getAutCode(int size){
		String str ="";
	        Random random = new Random();
	        List<Integer> list = new ArrayList<Integer>();
	        Integer temp = 0;
	       out:for (int i = 0; i < size; i++) {
	        	temp=random.nextInt(10);
	        	list.add(temp);
		   }

	        for (Integer integer : list) {
				str+=integer;
			}
	        return str;
	}
}
