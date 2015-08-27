package com.center.buuluu.service;

import java.sql.SQLException;

import com.center.buuluu.model.AppUserSession;
/**
 * 
 * @author More
 *2015-7-17
 */
public interface UserSessionService {


/*	AppUserSession getUserSessionByUserId(String userId);

	boolean updateUserSession(AppUserSession userSession,String userId) throws SQLException;*/

	/*
	 * 检测用户的id 和token是否有效
	 
	AppUserSession checkUserSession(String userId, String token);

	boolean logout(String userId, String token);*/

	/**
	 * 保存用户session
	 * @param userId
	 * @param lang
	 * @param device
	 * @param imei
	 * @param mac
	 * @param imsi
	 * @param token 
	 * @return
	 */
	boolean addSession(String userId, String lang, Integer device, String imei,
			String mac, String imsi, String token);

}
