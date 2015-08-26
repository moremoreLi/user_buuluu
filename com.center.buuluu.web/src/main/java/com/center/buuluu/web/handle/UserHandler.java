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
	boolean flag=false;
	private static final String SMS_DOMAIN= PropertiesUtil.getProperty("SMS_DOMAIN");
	
	AppUserSession userSession=null;
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
	public UserVO register(AppUser user, String lang, Integer device, String imei, String mac,
			String imsi,int registerType, String vistorId) throws SQLException {
		UserVO vo  = new UserVO();
		boolean usersessionAddFlag=false;
		String token="";
		String userId="";
		if(registerType==1)//普通注册 将访问者信息同步到用户表中
			flag =userService.register(user,vistorId,registerType,user.getTel()+vistorId);
		else{
			userId = Constant.getUUID();
			user.setId(userId);
			flag =userService.register(user,vistorId,registerType,user.getTel()+vistorId);
		}
		if (flag) {
			token = Constant.getSessionId();
			userSession=new AppUserSession();
			userSession.setUserId(userId);
			userSession.setToken(token);
			userSession.setLang(lang);
			userSession.setDevice(device+"");
			userSession.setImei(imei);
			userSession.setMac(mac);
			userSession.setImsi(imsi);
			usersessionAddFlag = userSessionService.addSession(userSession,userSession.getUserId());//在当前活动的用户表中添加该用户信息
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
		}
		if (vo==null)
			throw new BaseAPIException();
		
		
		/*if (registerType==1) {
			flag =userService.register(user,activation,vistorId);//将访问者信息同步到用户表中
			if (flag) {
				String token = Constant.getSessionId();
				userSession.setToken(token);
				userSession.setLang(lang);
				userSession.setDevice(device+"");
				userSession.setImei(imei);
				userSession.setMac(mac);
				userSession.setImsi(imsi);
				flag = userSessionService.addSession(userSession);//在当前活动的用户表中添加该用户信息
				if (flag) {
					vo = new UserVO();
					vo.setToken(token);
					vo.setLat(user.getLat());
					vo.setLog(user.getLog());
					vo.setUserFlow(user.getUserFlow());
					vo.setFlowCoins(user.getFlowCoins());
					vo.setMakeFlow(user.getMakeFlow());
					vo.setCredit(user.getCredit());
					vo.setNickName(user.getNickName());
					vo.setIcon(user.getIcon());
					vo.setCountry(user.getCountry());
					vo.setBirthday(user.getBirthday());
					vo.setTel(user.getTel());
				}
			}
			
			if (vo==null) 
				throw new BaseAPIException();
		}else {
			String userId = Constant.getUUID();
			flag =userService.register(user,userId,vistorId);
			if (flag) {
				String token = Constant.getSessionId();
				userSession.setUserId(userId);
				userSession.setToken(token);
				userSession.setLang(lang);
				userSession.setDevice(device+"");
				userSession.setImei(imei);
				userSession.setMac(mac);
				userSession.setImsi(imsi);
				flag= userSessionService.addSession(userSession);
				if (flag) {
					vo = new UserVO();
					vo.setUserId(userId);
					vo.setToken(token);
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
				}
			}
			
			if (vo==null) 
				throw new BaseAPIException();
		}*/
		
		return vo;
	}

	public UserVO login(String lang,Integer  device,String imei,String mac,String imsi,AppUser user) throws SQLException {
		//AppUserSession userSessio=null;
		String userId = user.getId().toString();
		UserVO vo = null;
		 userSession =userSessionService.getUserSessionByUserId(userId);
		 String token = Constant.getSessionId();
		 boolean userSession_is_insert=false;
		 if(userSession==null){
			 userSession=new AppUserSession();
			 userSession.setUserId(userId);
			 userSession.setDevice(device+"");
			 userSession.setCreatedBy(Constant.CREATE_BY_API);
			 userSession.setCreatedTime(DateUtil.getCurrentDate());
			 userSession.setLoginTime(DateUtil.getCurrentDate());
			 userSession.setStatus(1);
			 userSession_is_insert=false;
		 }else{
			 userSession.setUpdatedBy(Constant.UPDATE_BY_API);
			 userSession.setUpdatedTime(DateUtil.getCurrentDate());
			 userSession.setStatus(1);
			 userSession_is_insert=true;
		 }
		userSession.setToken(token);
		userSession.setLang(lang);
		userSession.setImei(imei);
		userSession.setMac(mac);
		userSession.setImsi(imsi);
		if(!userSession_is_insert){
			flag = userSessionService.addSession(userSession,userSession.getUserId());
		}else{
			flag =userSessionService.updateUserSession(userSession,userSession.getUserId());
		}
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
			if (user!=null) {
				/*
				 * 如果用户存在(以前是游客，不是正式用户的时候)，保存验证码到user表中
				 */
				user.setTelApproveKey(autoCode);
				user.setTelValueFlag(1);//表示验证中
				user.setTel(tel);
				user.setCountryCode(countryCode);
				user.setUpdatedBy(Constant.UPDATE_BY_API);
				user.setUpdatedTime(DateUtil.getCurrentDate());
				return userService.update(user,user.getId());
			}else {
				/*
				 * 如果用户不存在(以前从来没有玩过这个游戏)，添加用户信息到user表中
				 */
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
				flag = userService.addUser(user,user.getId());
				if (flag) {
					return true;
				}else{
					return false;
				}
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
