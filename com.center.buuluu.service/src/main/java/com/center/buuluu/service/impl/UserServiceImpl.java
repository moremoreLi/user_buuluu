package com.center.buuluu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.center.buuluu.common.Context;
import com.center.buuluu.common.exception.IllegalUserSessionException;
import com.center.buuluu.common.util.Constant;
import com.center.buuluu.common.util.DateUtil;
import com.center.buuluu.dao.UserDao;
import com.center.buuluu.dao.UserSessionDao;
import com.center.buuluu.model.AppUser;
import com.center.buuluu.model.AppUserSession;
import com.center.buuluu.service.UserService;


@Service("userService")
@Transactional
public class UserServiceImpl extends Context implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	
	@Override
	public  AppUser getUserById(String id) {
		 return userDao.getUserById(id);
	}

	@Override
	public AppUser getByTel(String countryCode, String tel,String countryCodeTel) {
		return userDao.getByTel(countryCode,tel,countryCodeTel);
	}

	@Override
	public boolean update( AppUser user) {
		return userDao.update( user,user.getId(),user.getCountryCode()+Constant.STRING_SPLIT+user.getTel());
	}

	@Override
	public boolean addUser(AppUser user) {
		return userDao.addUser(user,user.getId());
	}

	@Override 
	public boolean register( AppUser user, String countryCode,
			String tel, String pwd, Integer pushStatus, Double log,
			Double lat, Float flowCoins, int registerType) {
		int resultSize=0;
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
		user.setLog(log==null?0D:log);
		user.setLat(lat==null?0D:lat);
		user.setTelValueFlag(2);
	    user.setCountryCode(countryCode);
	    user.setTel(tel);
	    user.setPwd(pwd);
	    user.setPushStatus(pushStatus);
	    user.setIsDeleted(0);
		if(registerType==1){//表示普通注册
			resultSize =userDao.updateByPrimaryKeySelective(user,user.getId(),countryCode+Constant.STRING_SPLIT+tel);
		}else{
			resultSize =userDao.insertSelective(user,user.getId());
		}
		if(resultSize<1)
			return false;
		return true;
	}

	@Override
	public boolean logout(String userId, String token) {
		AppUserSession userSession = userSessionDao.getUserSessionByUserId(userId);
		if (userSession!=null) {
			userSession.setStatus(0);
			userSession.setUpdatedBy(Constant.UPDATE_BY_API);
			userSession.setUpdatedTime(DateUtil.getCurrentDate());
			return userSessionDao.updateUserSession(userSession, userId);
		}else {
			throw new IllegalUserSessionException(null);
		}
	}
}
