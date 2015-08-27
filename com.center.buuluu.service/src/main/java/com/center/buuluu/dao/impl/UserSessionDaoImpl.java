package com.center.buuluu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.center.buuluu.annotation.CacheDel;
import com.center.buuluu.annotation.CacheKey;
import com.center.buuluu.annotation.CachePut;
import com.center.buuluu.annotation.CacheValue;
import com.center.buuluu.annotation.Cacheable;
import com.center.buuluu.dao.UserSessionDao;
import com.center.buuluu.dao.mapper.AppUserSessionMapper;
import com.center.buuluu.model.AppUserSession;

@Repository
public class UserSessionDaoImpl implements UserSessionDao {
	
	@Autowired
	private AppUserSessionMapper appUserSessionMapper;

	@Override
	@CachePut(table=AppUserSession.class)
	public int add(@CacheValue AppUserSession userSession,@CacheKey String userId) {
		return appUserSessionMapper.insert(userSession);
	}

	@Override
	@Cacheable(table=AppUserSession.class)
	public AppUserSession getUserSessionByUserId(@CacheKey String userId) {
		return appUserSessionMapper.getUserSessionByUserId(userId);
	}

	@Override
	@CacheDel(table=AppUserSession.class)
	public boolean updateUserSession( @CacheValue AppUserSession userSession, @CacheKey String userId) {
		return appUserSessionMapper.updateUserSession(userSession)>0?true:false;
	}

	@Override
	public AppUserSession checkUserSession(String userId, String token) {
		return null;
	}

	@Override
	public boolean logout(String userId, String token) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
