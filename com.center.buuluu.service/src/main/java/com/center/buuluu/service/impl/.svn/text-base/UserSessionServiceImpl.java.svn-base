package com.center.buuluu.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.center.buuluu.annotation.CacheKey;
import com.center.buuluu.annotation.CacheValue;
import com.center.buuluu.annotation.Cacheable;
import com.center.buuluu.annotation.Cacheable.TypeMode;
import com.center.buuluu.common.Context;
import com.center.buuluu.common.exception.IllegalUserSessionException;
import com.center.buuluu.common.util.CodeStatus;
import com.center.buuluu.common.util.I18nUtil;
import com.center.buuluu.dao.mapper.AppUserSessionMapper;
import com.center.buuluu.model.AppUserSession;
import com.center.buuluu.service.UserSessionService;

@Service("userSessionService")
@Transactional
public class UserSessionServiceImpl extends Context implements UserSessionService{
	
	@Autowired
	private AppUserSessionMapper appUserSessionMapper;
	int flag=0;
	@Override
	@Cacheable(table=AppUserSession.class,type=TypeMode.HASH) 
	public AppUserSession getUserSessionByUserId(String userId)
			throws SQLException {
		return appUserSessionMapper.getUserSessionByUserId(userId);
	}

	@Override
	 @Cacheable(table=AppUserSession.class,type=TypeMode.HASH) 
	public boolean updateUserSession(@CacheValue AppUserSession userSession,@CacheKey String userSessionId)
			throws SQLException {
		flag= appUserSessionMapper.updateByPrimaryKeySelective(userSession);
		if(flag<1) return false;
		return true;
	}

	@Override
	 @Cacheable(table=AppUserSession.class,type=TypeMode.HASH) 
	public boolean addSession(@CacheValue AppUserSession userSession,@CacheKey String userId) {
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
	}

	@Override
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
	}


}
