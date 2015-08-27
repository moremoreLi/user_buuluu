package com.center.buuluu.dao.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.center.buuluu.annotation.CacheDel;
import com.center.buuluu.annotation.CacheKey;
import com.center.buuluu.annotation.CachePut;
import com.center.buuluu.annotation.CacheValue;
import com.center.buuluu.annotation.Cacheable;
import com.center.buuluu.dao.UserDao;
import com.center.buuluu.dao.mapper.AppUserMapper;
import com.center.buuluu.model.AppUser;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private AppUserMapper appUserMapper;

	@Override
	@CacheDel(table=AppUser.class)
	public int updateByPrimaryKeySelective(AppUser user,@CacheKey String key,@CacheKey String countryTel) {
		return appUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	@CachePut(table=AppUser.class)
	public int insertSelective(@CacheValue AppUser user,@CacheKey String key) {
		return appUserMapper.insertSelective(user);
	}

	@Override
	@Cacheable(table=AppUser.class)
	public AppUser getUserById(@CacheKey String id) {
		return appUserMapper.selectByPrimaryKey(id);
	}

	@Override
	@Cacheable(table=AppUser.class)
	public AppUser getByTel( String countryCode,String tel,@CacheKey String countryCodeTel) {
		return appUserMapper.getByTel(countryCode,tel);
	}


	@Override
	@CachePut(table=AppUser.class)
	public boolean addUser(@CacheValue AppUser user,@CacheKey String id) {
		return appUserMapper.insertSelective(user)>0?true:false;
	}

	@Override
	@CacheDel(table=AppUser.class)
	public boolean update( AppUser user,@CacheKey String id,@CacheKey String countryCodeTel) {
		return appUserMapper.updateByPrimaryKeySelective(user)>0?true:false;
	}

}
