package com.center.buuluu.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.center.buuluu.annotation.CacheKey;
import com.center.buuluu.annotation.CachePut;
import com.center.buuluu.annotation.CachePut.PutTypeMode;
import com.center.buuluu.annotation.CacheValue;
import com.center.buuluu.annotation.Cacheable;
import com.center.buuluu.annotation.Cacheable.TypeMode;
import com.center.buuluu.common.Context;
import com.center.buuluu.common.util.Constant;
import com.center.buuluu.common.util.DateUtil;
import com.center.buuluu.dao.mapper.AppUserMapper;
import com.center.buuluu.model.AppUser;
import com.center.buuluu.model.AppVistorUser;
import com.center.buuluu.service.UserService;
import com.center.buuluu.service.UserSessionService;
import com.center.buuluu.service.VistorUserService;

@Service("userService")
@Transactional
public class UserServiceImpl extends Context implements UserService{
	
	@Autowired
	private AppUserMapper appUserMapper;
	
	@Autowired
	private VistorUserService vistorUserService;
	
	@Autowired
	private UserSessionService userSessionService;
	int flag=0;
	@Override
	@Cacheable(table=AppUser.class,type=TypeMode.HASH) 
	public  AppUser getUserById(@CacheKey String id) {
		 return appUserMapper.selectByPrimaryKey(id);
	}

	@Override
	@Cacheable(table=AppUser.class)
	public AppUser getByTel(String countryCode, String tel,@CacheKey String countryCodeTel) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("countryCode", countryCode);
		map.put("tel", tel);
		return appUserMapper.getByTel(map);
	}

	@Override
	@Cacheable(table=AppUser.class,type=TypeMode.HASH) 
	public boolean update(@CacheValue AppUser user,@CacheKey String userId) {
		flag=appUserMapper.updateByPrimaryKeySelective(user);
		if(flag<1)
			return false;
		return true;
	}

	@Override
	@CachePut(table=AppUser.class,type=PutTypeMode.HASH)
	public boolean addUser(@CacheValue AppUser user,@CacheKey String userId) {
		flag=appUserMapper.insertSelective(user);
		if(flag<1)
			return false;
		return true;
	}

	@Override 
	 @Cacheable(table=AppUser.class,type=TypeMode.HASH) 
	public boolean register( AppUser user,@CacheKey String vistorId,int registerType) {
		//先根据访问者的id号码查询访问者数据库，如果访问者数据库存在该用户信息，则将访问者的流量信息保存起来，同步到用户信息表中
		user.setFlowCoins(0F);
		user.setMakeFlow(0F);
		user.setUserFlow(0F);
		user.setSex(1);
		user.setCredit(0);
		user.setPoint(0);
		user.setUpdatedBy(Constant.UPDATE_BY_API);
		user.setUpdatedTime(DateUtil.getCurrentDate());
		user.setBirthday(-28800000L+"");//默认原始时间 1970-01-01
		user.setIcon("");
		user.setCountry("");
		user.setNickName("");
		if (user.getLog()==null) 
			user.setLog(0D);
		if (user.getLat()==null) 
			user.setLat(0D);
		if (StringUtils.isNotEmpty(vistorId)) {
			 AppVistorUser vistorUser=null;
			try {
				vistorUser =vistorUserService.getUserById(vistorId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 if (vistorUser!=null) {
				user.setFlowCoins(vistorUser.getFlowCoins());
				user.setMakeFlow(vistorUser.getMakeFlow());
				user.setUserFlow(vistorUser.getUserFlow());
			}
		}
		if(registerType==1){//表示普通注册
			flag =appUserMapper.updateByPrimaryKeySelective(user);
		}else{
			flag =appUserMapper.insertSelective(user);
		}
		if(flag<1)
			return false;
		return true;
	}

	@Override
	 @Cacheable(table=AppUser.class,type=TypeMode.HASH) 
	public boolean logout(String userId, String token) {
		return userSessionService.logout(userId,token);
	}
}
