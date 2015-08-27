package com.center.buuluu.dao;

import com.center.buuluu.model.AppUserSession;

public interface UserSessionDao {

	/**
	 * 添加用户session
	 * @param userSession
	 * @param userId
	 * @return
	 */
	int add(AppUserSession userSession, String userId);
	
	/**
	 * 获取用户session
	 * @param userId
	 * @return
	 */
	AppUserSession getUserSessionByUserId(String userId);

	/**
	 * 更新用户session
	 * @param userSession
	 * @param userId
	 * @return
	 */
	boolean updateUserSession(AppUserSession userSession,String userId);

	/*
	 * 检测用户的id 和token是否有效
	 */
	AppUserSession checkUserSession(String userId, String token);

	/**
	 * 登出
	 * @param userId
	 * @param token
	 * @return
	 */
	boolean logout(String userId, String token);

}
