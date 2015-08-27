package com.center.buuluu.service;

import com.center.buuluu.model.AppUser;


public interface UserService {

	/**
	       * 添加用户
	      * @param user
	      */
		boolean addUser(AppUser user);
	    
	    /**
	      * 根据用户id获取用户
	      * @param userId
	      * @return
	      */
	    AppUser getUserById(String userId);

	    /**
	     * 根据国家编号和电话查找
	     * @param countryCode
	     * @param tel
	     * @return
	     */
		AppUser getByTel(String countryCode, String tel,String countryCodeTel);
		/**
		 * 更新用户信息
		 * @param user
		 * @return
		 */
		boolean update(AppUser user);
		
		/**
		 * 用户登出
		 * @param userId
		 * @param token
		 * @return
		 */
		boolean logout(String userId, String token);

		/**
		 * 注册用户信息
		 * @param user
		 * @param id
		 * @param countryCode
		 * @param tel
		 * @param pwd
		 * @param pushStatus
		 * @param log
		 * @param lat
		 * @param flowCoins
		 * @param registerType
		 * @return
		 */
		boolean register(AppUser user, String countryCode,
				String tel, String pwd, Integer pushStatus, Double log,
				Double lat, Float flowCoins, int registerType);

}
