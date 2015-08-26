package com.center.buuluu.service;

import com.center.buuluu.model.AppUser;


public interface UserService {

	/**
	       * 添加用户
	      * @param user
	      */
		boolean addUser(AppUser user,String userId);
	    
	    /**
	      * 根据用户id获取用户
	      * @param userId
	      * @return
	      */
	    AppUser getUserById(String userId,String cache);

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
		boolean update(AppUser user,String userId);
		
		boolean register( AppUser user,String vistorId,int registerType,String cache);

		boolean logout(String userId, String token);

}
