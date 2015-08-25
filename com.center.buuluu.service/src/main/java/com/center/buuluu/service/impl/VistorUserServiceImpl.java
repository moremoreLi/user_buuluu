package com.center.buuluu.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.center.buuluu.annotation.CacheValue;
import com.center.buuluu.annotation.Cacheable;
import com.center.buuluu.annotation.Cacheable.TypeMode;
import com.center.buuluu.dao.mapper.AppVistorUserMapper;
import com.center.buuluu.model.AppVistorUser;
import com.center.buuluu.service.VistorUserService;
@Service("VistorUserService")
@Transactional
public class VistorUserServiceImpl implements VistorUserService{
	
	@Autowired
	private AppVistorUserMapper appVistorUserMapper;
	@Override
	 @Cacheable(table=AppVistorUser.class,type=TypeMode.HASH) 
	public boolean add(@CacheValue AppVistorUser user,@CacheValue String userId) throws SQLException {
		int flag=appVistorUserMapper.insertSelective(user);
		if(flag<1)
			return false;
		return true;
	}

	@Override
	 @Cacheable(table=AppVistorUser.class,type=TypeMode.HASH) 
	public AppVistorUser getUserById(String userId)
			throws SQLException {
		return appVistorUserMapper.getUserById(userId);
	}

	@Override
	 @Cacheable(table=AppVistorUser.class,type=TypeMode.HASH) 
	public boolean update(com.center.buuluu.model.AppVistorUser vistorUser)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
