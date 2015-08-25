package com.center.buuluu.service;

import java.sql.SQLException;

import com.center.buuluu.model.AppUserSession;
/**
 * 
 * @author More
 *2015-7-17
 */
public interface UserSessionService {


	AppUserSession getUserSessionByUserId(String userId) throws SQLException;

	boolean updateUserSession(AppUserSession userSession,String userId) throws SQLException;

	/*
	 * 保存用户session
	 */
	boolean addSession(AppUserSession userSession,String userId);


	/*
	 * 检测用户的id 和token是否有效
	 */
	AppUserSession checkUserSession(String userId, String token);

	boolean logout(String userId, String token);

}
