package com.center.buuluu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.buuluu.common.Context;
import com.center.buuluu.common.util.Constant;
import com.center.buuluu.common.util.DateUtil;
import com.center.buuluu.dao.UserSessionDao;
import com.center.buuluu.dao.mapper.AppUserSessionMapper;
import com.center.buuluu.model.AppUserSession;
import com.center.buuluu.service.UserSessionService;

@Service("userSessionService")
public class UserSessionServiceImpl extends Context implements UserSessionService{
	
	@Autowired
	private AppUserSessionMapper appUserSessionMapper;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	/*@Override
	@Cacheable(table=AppUserSession.class,type=TypeMode.HASH) 
	public AppUserSession getUserSessionByUserId(String userId) {
		return appUserSessionMapper.getUserSessionByUserId(userId);
	}

	@Override
	 @Cacheable(table=AppUserSession.class,type=TypeMode.HASH) 
	public boolean updateUserSession(@CacheValue AppUserSession userSession,@CacheKey String userSessionId)
			throws SQLException {
		boolean flag= appUserSessionMapper.updateByPrimaryKeySelective(userSession);
		if(flag<1) return false;
		return true;
	}*/
/*
	@Override
	public boolean addSession(AppUserSession userSession) {
		String token = Constant.getSessionId();
		userSession=new AppUserSession();
		userSession.setUserId(userId);
		userSession.setToken(token);
		userSession.setLang(lang);
		userSession.setDevice(device+"");
		userSession.setImei(imei);
		userSession.setMac(mac);
		userSession.setImsi(imsi);
		flag=appUserSessionMapper.insertSelective(userSession);
		 if(flag<1){
			 return false;
		 }
		return true;
	}

	@Override
	 @Cacheable(table=AppUserSession.class,type=TypeMode.HASH) 
	public AppUserSession checkUserSession(String userId, String token) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", token);
		AppUserSession userSession = appUserSessionMapper.checkUserSession(map);
		if (userSession==null) {
			throw new IllegalUserSessionException(I18nUtil.getMessage(CodeStatus.USER_SESSION_EXCEPTION, null, null));
		}
		return userSession ;
	}*/

	/*@Override
	 @Cacheable(table=AppUserSession.class,type=TypeMode.HASH) 
	public boolean logout(String userId, String token) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", token);
		flag=appUserSessionMapper.updateUserSession(map);
		 if(flag<1){
			 return false;
		 }
		return true;
	}*/

	@Override
	public boolean addSession(String userId, String lang, Integer device,String imei, String mac, String imsi, String token) {
		AppUserSession userSession = userSessionDao.getUserSessionByUserId(userId);
		if (userSession!=null) {
			userSession.setUpdatedBy(Constant.UPDATE_BY_API);
			userSession.setUpdatedTime(DateUtil.getCurrentDate());
			userSession.setToken(token);
			return userSessionDao.updateUserSession(userSession, userId);
		}else {
			userSession=new AppUserSession();
			userSession.setUserId(userId);
			userSession.setToken(token);
			userSession.setLang(lang);
			userSession.setDevice(device+"");
			userSession.setImei(imei);
			userSession.setMac(mac);
			userSession.setImsi(imsi);
			userSession.setIsDeleted(0);
			userSession.setCreatedBy(Constant.CREATE_BY_API);
			userSession.setCreatedTime(DateUtil.getCurrentDate());
			userSession.setStatus(1);
			return userSessionDao.add(userSession,userId)>0?true:false;
		}
	}
}
