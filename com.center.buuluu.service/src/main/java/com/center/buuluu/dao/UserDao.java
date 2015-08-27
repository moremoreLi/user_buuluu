package com.center.buuluu.dao;

import com.center.buuluu.model.AppUser;

public interface UserDao {

	/**
	 * 更新用户表
	 * @param user
	 * @return
	 */
	int updateByPrimaryKeySelective(AppUser user,String key,String countryTel);

	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	int insertSelective(AppUser user,String key);

	/**
	 * 根据id获取用户信息
	 * @param id
	 * @return
	 */
	AppUser getUserById(String id);

	/**
	 * 根据电话和国家编号获取用户信息
	 * @param countryCode
	 * @param tel
	 * @param countryCodeTel
	 * @return
	 */
	AppUser getByTel(String countryCode, String tel, String countryCodeTel);


	/**
	 * 添加用户信息
	 * @param user
	 * @param id
	 * @return
	 */
	boolean addUser(AppUser user, String id);

	/**更改用户信息
	 * countryCode+Constant.STRING_SPLIT+tel
	 * @param user
	 * @param id
	 * @param string
	 * @return
	 */
	boolean update(AppUser user, String id, String countryCodeTel);

}
