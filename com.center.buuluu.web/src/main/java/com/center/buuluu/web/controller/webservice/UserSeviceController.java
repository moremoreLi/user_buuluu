package com.center.buuluu.web.controller.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.druid.util.StringUtils;
import com.center.buuluu.common.exception.ActCodeException;
import com.center.buuluu.common.exception.BaseAPIException;
import com.center.buuluu.common.exception.ExistMobileException;
import com.center.buuluu.common.exception.NotVerifyActivationException;
import com.center.buuluu.common.exception.UserNotExistException;
import com.center.buuluu.common.exception.VerifyActivationPassException;
import com.center.buuluu.common.exception.WrongPasswordException;
import com.center.buuluu.common.util.Constant;
import com.center.buuluu.common.util.DateUtil;
import com.center.buuluu.common.util.I18nUtil;
import com.center.buuluu.common.util.Json;
import com.center.buuluu.common.util.PropertiesUtil;
import com.center.buuluu.model.AppUser;
import com.center.buuluu.model.AppVistorUser;
import com.center.buuluu.service.UserService;
import com.center.buuluu.service.UserSessionService;
import com.center.buuluu.service.VistorUserService;
import com.center.buuluu.util.HttpClientUtils;
import com.center.buuluu.util.ResultUtil;
import com.center.buuluu.web.handle.UserHandler;
import com.center.buuluu.web.view.UserVO;

/**
 * 
 * @author More
 * 登录的相关接口的开发
 * 2015.7.14
 *
 */
@Controller
public class UserSeviceController {

	private Log log = LogFactory.getLog(this.getClass());


	@Autowired
	private UserService userService;

	@Autowired
	private UserSessionService userSessionService;
	
	@Autowired
	private UserHandler userHandler;
	
	@Autowired
	private VistorUserService vistorUserService;
	
	private static final String EMAIL_DOMAIN= PropertiesUtil.getProperty("EMAIL_DOMAIN");	
	/**
	 * 获取验证码
	 * @param request
	 * @param model
	 * @param lang  返回的数量语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @param countryCode  手机电话号码的国家编号
	 * @param tel  手机电话号码的国家编号
	 * @param type  验证码类型(1:注册 2:重置 3:修改)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendActivation.do",method=RequestMethod.POST)
	@Transactional
	public String sendActivation(HttpServletRequest request, ModelMap model, String lang, Integer device,String deviceVerNum,
			String imei,String mac,String imsi , String countryCode, String tel, Integer type) throws Exception {
		Assert.hasText(lang, null);
		Assert.notNull(device,null);
		Assert.hasText(deviceVerNum, null);
		Assert.hasText(imei, null);
		Assert.hasText(mac, null);
		Assert.hasText(imsi,null);
		Assert.hasText(countryCode,null);
		Assert.hasText(tel,null);
		Assert.notNull(type);
		String jsonStr = null;
		AppUser user = null;
		user = userService.getByTel(countryCode,tel,countryCode+Constant.STRING_SPLIT+tel);
		/*
		 * 用户存在，则提示用户已经存在，验证码已经发送过了，等待验证中的状态
		 */
		if (user!=null&&user.getTelValueFlag()==1) {
			throw new ExistMobileException(lang);
		}
		/*
		 * 如果没有发送过验证码，，则添加用户信息到user数据表，如果有发
		 */
		boolean flag =userHandler.update(user,lang,countryCode,tel);
		//TODO:发送短信给用户
		if (flag) {
			jsonStr = ResultUtil.getResultJson("");
		}else {
			throw new BaseAPIException();
		}
		
		model.put("message", jsonStr);
		return "message.json";
	}
	
	/**
	 * 注册
	 * @param request
	 * @param model
	   @param lang  返回的语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @param countryCode  手机电话号码的国家编号
	 * @param tel  手机电话号码的国家编号
	 * @param pwd   注册用户密码，后续再讨论加密
	 * @param pushStatus  推送状态，0是不推送，1是推送
	 * @param deviceId   手机的deviceId(用来后期服务器注册push）
	 * @param log 经度位置
	 * @param lat  纬度位置
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register.do",method=RequestMethod.POST)
	public  String register(HttpServletRequest request, ModelMap model, String lang, Integer device,String deviceVerNum,
			String imei,String mac,String imsi , String countryCode, String tel, Double log,Double lat,String pwd,Integer pushStatus,String activation,String vistorId) throws Exception {
		String jsonStr = null;
		AppUser user = null;
		UserVO userVO = null;
		user = userService.getByTel(countryCode,tel,countryCode+Constant.STRING_SPLIT+tel);
		if (user == null) {
			throw new NotVerifyActivationException(lang);
		}
		if(activation.equals(user.getTelApproveKey())){
			//普通注册的时候判断验证码，看验证码是否和发送的验证码是否相同，如果相同，
			if (user.getTelValueFlag()==0) {//不需要验证，提示用户不用验证
				throw new NotVerifyActivationException(lang);
			}else if (user.getTelValueFlag()==1) {
				//表示验证进行中，把用户信息更新到user表，根据访问者表(vistoruser)查到用户id，把用户信息保存到用户活动表（usersession）中
				user.setTelValueFlag(2);
				user.setCountryCode(countryCode);
				user.setTel(tel);
				user.setPwd(pwd);
				user.setPushStatus(pushStatus);
				user.setLog(log);
				user.setLat(lat);
//				user.setTelApproveKey(activation);//因为在获取验证码的时候保存到了 数据库，验证验证码的时候修改了验证码的值，所以在此不需要修改验证码了
				userVO = userHandler.register(user,lang,device,imei,mac,imsi,1,vistorId);
			}else if (user.getTelValueFlag()==2) {//表示已经验证通过了，不用验证了
				throw new VerifyActivationPassException(lang);
			}else {
				throw new BaseAPIException();
			}
				
		}else{
			throw new ActCodeException(lang);
		}
		jsonStr = ResultUtil.getResultJson(userVO);
		model.put("message", jsonStr);
		return "message.json";
	}
	
	/**
	 * 快速注册
	 * @param request
	 * @param model
	   @param lang  返回的语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @param countryCode  手机电话号码的国家编号
	 * @param tel  手机电话号码的国家编号
	 * @param pwd   注册用户密码，后续再讨论加密
	 * @param pushStatus  推送状态，0是不推送，1是推送
	 * @param log 经度位置
	 * @param lat  纬度位置
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/quickRegister.do",method=RequestMethod.POST)
	public  String quickRegister(HttpServletRequest request, ModelMap model, String lang, Integer device,String deviceVerNum,
			String imei,String mac,String imsi , String countryCode, String tel, Double log,Double lat,String pwd,Integer pushStatus,String vistorId) throws Exception {
		String jsonStr = null;
		AppUser user = null;
		UserVO userVO = null;
		user = userService.getByTel(countryCode,tel,countryCode+Constant.STRING_SPLIT+tel);
		/*
		 * 快速注册的时候，如果查询到该用户，表示该用户存在，如果没有查到该用户，则生成一个用户对象，保存到数据库中
		 */
		if (user != null) {
			throw new ExistMobileException(lang);
		}else{
			user=new AppUser();
		}
		user.setCountryCode(countryCode);
		user.setTel(tel);
		user.setPwd(pwd);
		user.setPushStatus(pushStatus);
		user.setLog(log);
		user.setLat(lat);
		userVO = userHandler.register(user, lang, device, imei, mac, imsi, 2,vistorId);
//		userVO = userHandler.register(countryCode, tel, pwd, pushStatus, log, lat, lang, device, imei, mac, imsi, user, "", 2,vistorId);
		jsonStr = ResultUtil.getResultJson(userVO);
		model.put("message", jsonStr);
		return "message.json";
	}
	
	/**
	 * 验证验证码
	 * @param request
	 * @param model
	 *@param lang  返回的语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @param countryCode  手机电话号码的国家编号
	 * @param tel  手机电话号码的国家编号
	 * @param activation 验证码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/verifyActivation.do",method=RequestMethod.POST)
	public String verifyActivation(HttpServletRequest request, ModelMap model, String lang,Integer device,String deviceVerNum,
			String imei,String mac,String imsi , String countryCode, String tel, String activation) throws Exception {
		String jsonStr = null;
		AppUser user = null;
		user = userService.getByTel(countryCode,tel,countryCode+Constant.STRING_SPLIT+tel);
		if (user == null) {
			throw new UserNotExistException(lang);
		}
		//TODO:目前暂时测试阶段，使用默认的1234作为验证码
		if(activation.equals(user.getTelApproveKey())){//将发送过来的验证码和数据库中保存的验证码对比
			if (user.getTelValueFlag()==0) {
				throw new NotVerifyActivationException(lang);
			}else if (user.getTelValueFlag()==1) {//表示在进行验证码验证的状态
				user.setTelValueFlag(2);//修改验证码验证状态，表示验证码验证完成
				user.setUpdatedBy(Constant.UPDATE_BY_API);
				user.setUpdatedTime(DateUtil.getCurrentDate());
				boolean flag =userService.update(user,user.getId()+user.getTel());
				if (!flag) 
					throw new BaseAPIException();
				jsonStr = ResultUtil.getResultJson(user);
			}else if (user.getTelValueFlag()==2) {
				throw new VerifyActivationPassException(lang);
			}else {
				throw new BaseAPIException();
			}
		}else{
			throw new ActCodeException(lang);
		}

		model.put("message", jsonStr);
		return "message.json";
	}
	
	/**
	 * 登录
	@param lang  返回的语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @param countryCode  手机电话号码的国家编号
	 * @param tel  手机电话号码的国家编号
	 * @param pwd   用户密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login.do",method=RequestMethod.POST)
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String login(HttpServletRequest request, ModelMap model, String lang, Integer device,String deviceVerNum,
			String imei,String mac,String imsi , String countryCode, String tel, String pwd) throws Exception {
		String jsonStr = null;
		if (!StringUtils.isEmpty(tel)&& !StringUtils.isEmpty(pwd)) {
			AppVistorUser user = new AppVistorUser();
			user.setId(Constant.getTempUserId());
			boolean flag = vistorUserService.add(user,user.getId());
			if (!flag) 
				throw new UserNotExistException(lang);
			
			jsonStr = returnJsonStr2(user.getId().toString(), Constant.getSessionId(), user);
		}else {
			AppUser user = null;
			user = userService.getByTel(countryCode,tel,countryCode+Constant.STRING_SPLIT+tel);
			if (user == null) {
				throw new UserNotExistException(lang);
			}
			if(pwd.equals(user.getPwd())){
				UserVO userVO =userHandler.login(lang, device, imei, mac, imsi, user);
				jsonStr = ResultUtil.getResultJson(userVO);
			}else{
				throw new WrongPasswordException(lang);
			}
		}
		model.put("message", jsonStr);
		return "message.json";
	}
	
	/**
	 * 自动登录
	 * @param request
	 * @param model
	 * @param lang  返回的语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @param pwd   用户密码
	 * @param userId   用户的唯一的用户id
	 * @param token   单点登录的token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/autoLogin.do",method=RequestMethod.POST)
	public String autoLogin(HttpServletRequest request, ModelMap model, String lang, Integer device,String deviceVerNum,
			String imei,String mac,String imsi , String userId, String token) throws Exception {
		String jsonStr = null;
		AppUser user = null;
		user = userService.getUserById(userId,userId+token);
		
		if (user==null) 
			throw new UserNotExistException(lang);
		
		jsonStr = returnJsonStr(userId, token, user);
		model.put("message", jsonStr);
		return "message.json";
	}
	
	
	/**
	 * 游客登录
	 * @param request
	 * @param model
	 * @param lang  返回的语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/vistorLogin.do",method=RequestMethod.POST)
	public String vistorLogin(HttpServletRequest request, ModelMap model, String lang, Integer device,String deviceVerNum,
			String imei,String mac,String imsi) throws Exception {
		String jsonStr = null;
		AppVistorUser user = new AppVistorUser();
		user.setId(Constant.getTempUserId());
		boolean flag = vistorUserService.add(user,user.getId());
		
		if (!flag) 
			throw new UserNotExistException(lang);
		
		jsonStr = returnJsonStr2(user.getId().toString(), Constant.getSessionId(), user);
		model.put("message", jsonStr);
		return "message.json";
	}

	/**
	 * 重置密码
	 * @param request
	 * @param model
	 * @param lang  返回的语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @param pwd   用户密码
	 * @param userId   用户的唯一的用户id
	 * @param token   单点登录的token
	 * @param oldPwd 用户的旧密码
	 * @param newPwd 用户的新密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetPassword.do",method=RequestMethod.POST)
	public String resetPassword(HttpServletRequest request, ModelMap model, String lang, Integer device,String deviceVerNum,
			String imei,String mac,String imsi , String userId, String token,String oldPwd,String newPwd) throws Exception {
		String jsonStr = null;
		AppUser user = null;
		user = userService.getUserById(userId,userId+token);
		
		if (!user.getPwd().equals(oldPwd)) {//验证旧密码是否正确
			throw new WrongPasswordException(lang);
		}
		
		user.setPwd(newPwd);
		user.setUpdatedBy(Constant.UPDATE_BY_API);
		user.setUpdatedTime(DateUtil.getCurrentDate());
		boolean flag =userService.update(user,user.getId()+user.getTel());
		if (flag) {
			jsonStr = ResultUtil.getResultJson(true);
		}else {
			throw new BaseAPIException();
		}
		jsonStr = ResultUtil.getResultJson(false);

		model.put("message", jsonStr);
		return "message.json";
	}
	
	/**
	 * 忘记密码
	 * @param request
	 * @param model
	* @param lang  返回的语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @param countryCode 电话的国家编码
	 * @param tel 电话号码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/recoverPassword.do",method=RequestMethod.POST)
	public String recoverPassword(HttpServletRequest request, ModelMap model, String lang, Integer device,String deviceVerNum,
			String imei,String mac,String imsi,String countryCode,String tel,String email) throws Exception {
		String jsonStr = null;
		
		if (!StringUtils.isEmpty(email)) {
			/*
			 * subject（必选）：发送邮件主题
               recipient（必选）：下发收件人列表，多个以,分隔
               cc（可选）：抄送人列表，多个以,分隔
               msg（必选）：下发正文, 需要对内容进行urlencode
               templateid（必选）：下发使用的模板id，请联系管理员获取可使用ID
			 */
			Map<String,String> params = new HashMap<String, String>();
			ResourceBundleMessageSource s = new ResourceBundleMessageSource();
			s.setBasename("i18n/message");
			String subject = s.getMessage("email_subject", null,I18nUtil.getLocale(lang));
			String msg = s.getMessage("email_msg", null,I18nUtil.getLocale(lang));
			msg = msg.replaceAll("newPwd", getAutCode(6));
			params.put("subject", subject);
			params.put("recipient", email);
			params.put("cc", "");
			params.put("msg", msg);
			params.put("templateid", "system");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			try {
				String result = HttpClientUtils.postMethodRequest(EMAIL_DOMAIN, params);
				System.out.println("result="+result);
				resultMap = Json.toMap(result);
			} catch (Exception e) {
				//因为国内发送不了email，暂时定到都成功
				resultMap.put("status", "200");
			}
			if (resultMap.get("status").toString().equals("200")) {
				//将数据库中的密码清除
				AppUser user = null;
				user = userService.getByTel(countryCode,tel,countryCode+Constant.STRING_SPLIT+tel);
				user.setPwd("");
				boolean flag =userService.update(user,user.getId()+tel);
				jsonStr = ResultUtil.getResultJson("");
			}else {
				throw new BaseAPIException();
			}
		}else {
			jsonStr = ResultUtil.getResultJson("");
		}

		model.put("message", jsonStr);
		return "message.json";
	}
	
	/**
	 * 获取用户信息
	 * @param request
	 * @param model
	 * @param lang  返回的语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @param userId  用户的userId
	 * @param token 用户的token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserProfile.do",method=RequestMethod.POST)
	public String getUserProfile(HttpServletRequest request, ModelMap model, String lang, Integer device,String deviceVerNum,
			String imei,String mac,String imsi,
			String userId,String token) throws Exception {
		String jsonStr = null;
		
		AppUser user = null;
		user = userService.getUserById(userId,userId+token);
		
		if (user==null) 
			throw new UserNotExistException(lang);
		
		
		jsonStr = returnJsonStr(userId, token, user);
		
		model.put("message", jsonStr);
		return "message.json";
	}
	
	/**
	 * 更改用户信息
	 * @param request
	 * @param model
	 * @param lang  返回的语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @param sex 性别
	 * @param icon 头像
	 * @param birthday  出生日期
	 * @param country  国籍
	 * @param nickName  用户昵称
	 * @param log  经度
	 * @param lat  纬度
	 * @param userId  用户的userId
	 * @param token 用户的token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateUserProfile.do",method=RequestMethod.POST)
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String updateUserProfile(HttpServletRequest request, ModelMap model, String lang,Integer device,String deviceVerNum,
			String imei,String mac,String imsi,
			Integer sex,String icon,Long birthday,String country,String nickName,Double log,
			Double lat,String userId,String token,String tel) throws Exception {
//		Assert.hasText(lang, null);
//		Assert.notNull(device,null);
//		Assert.hasText(deviceVerNum, null);
//		Assert.hasText(imei, null);
//		Assert.hasText(mac, null);
//		Assert.hasText(imsi,null);
//		Assert.hasText(userId);
//		Assert.hasText(token);
		String jsonStr = null;
		
		AppUser user = null;
		user = userService.getUserById(userId,userId+token);
		
		if (user==null) 
			throw new BaseAPIException();
		
		if (sex!=null) 
			user.setSex(sex);
		if (birthday!=null) 
			user.setBirthday(birthday+"");
		if (!StringUtils.isEmpty(country)) 
			user.setCountry(country);
		if (!StringUtils.isEmpty(nickName)) 
			user.setNickName(nickName);
		if (log!=null) 
			user.setLat(log);
		if (lat!=null) 
			user.setLat(lat);
		if (tel!=null) 
			user.setTel(tel);
		
		boolean flag =userService.update(user,user.getId());
		if (flag) {
			jsonStr = ResultUtil.getResultJson("");
		}else {
			throw new BaseAPIException();
		}

		model.put("message", jsonStr);
		return "message.json";
	}
	
	/**
	 * 登出
	 * @param request
	 * @param model
	 * @param lang  返回的语言类型
	 * @param device  设备类型，1是IOS，2是AOS
	 * @param deviceVerNum  App版本的控制，如1.0.0
	 * @param imei  用户标识码(没有时为” 00000000”)
	 * @param mac   用户mac地址
	 * @param imsi   用户SIM卡号(没有时为”00000000”)
	 * @param userId
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout.do",method=RequestMethod.POST)
	public String logout(HttpServletRequest request, ModelMap model, String lang, Integer device,String deviceVerNum,
			String imei,String mac,String imsi,
			String userId,String token) throws Exception {
//		log.info("logout.do?" + "lang=" + lang + "&device=" + device + "&deviceVerNum=" + deviceVerNum + "&userId=" + userId + "&session=" + session);
//		Assert.hasText(lang, "lang " + I18nUtil.getMessage(-2, null, lang));
//		Assert.hasText(device, "device " + I18nUtil.getMessage(-2, null, lang));
//		Assert.hasText(deviceVerNum, "deviceVerNum " + I18nUtil.getMessage(-2, null, lang));
//		Assert.hasText(userId, "userId " + I18nUtil.getMessage(-2, null, lang));
//		Assert.hasText(session, "session " + I18nUtil.getMessage(-2, null, lang));
		String jsonStr = null;

		boolean  flag = userService.logout(userId,token);
		if (flag) {
			jsonStr = ResultUtil.getResultJson("");
		}else {
			throw new  BaseAPIException();
		}
		model.put("message", jsonStr);
		return "message.json";
	}

	//整理userId，token，user返回数据
	private String returnJsonStr(String userId,String token,AppUser user){
		String jsonStr = null;
		UserVO userVO = new UserVO();
		userVO.setUserId(userId);
		userVO.setToken(token);
		userVO.setBirthday(user.getBirthday()==null?System.currentTimeMillis()+"":user.getBirthday());
		userVO.setLat(user.getLat());
		userVO.setLog(user.getLog());
		userVO.setNickName(user.getNickName());
		userVO.setSex(user.getSex()==null?1:user.getSex());
		userVO.setTel(user.getTel());
		userVO.setFlowCoins(user.getFlowCoins());
		userVO.setMakeFlow(user.getMakeFlow());
		userVO.setUserFlow(user.getUserFlow());
		userVO.setCredit(user.getCredit());
		jsonStr = ResultUtil.getResultJson(userVO);
		return jsonStr;
	}
	
	
	//整理访客userId，token，user返回数据
	private String returnJsonStr2(String userId,String token,AppVistorUser user){
		String jsonStr = null;
		UserVO userVO = new UserVO();
		userVO.setUserId(userId);
		userVO.setToken(token);
		userVO.setBirthday(user.getBirthday()==null?System.currentTimeMillis()+"":user.getBirthday());
		userVO.setLat(user.getLat());
		userVO.setLog(user.getLog());
		userVO.setNickName(user.getNickName());
		userVO.setSex(user.getSex()==null?1:user.getSex());
		userVO.setTel(user.getTel());
		userVO.setFlowCoins(user.getFlowCoins());
		userVO.setMakeFlow(user.getMakeFlow());
		userVO.setUserFlow(user.getUserFlow());
		userVO.setCredit(user.getCredit());
		jsonStr = ResultUtil.getResultJson(userVO);
		
		return jsonStr;
	}
	/*
	 * 随机生成6位数字
	 */
	private  String getAutCode(int size){
		String str ="";
	        Random random = new Random();
	       List<Integer> list = new ArrayList<Integer>();
	        Integer temp = 0;
	       out:for (int i = 0; i < size; i++) {
	        	temp=random.nextInt(10);
	        	list.add(temp);
		   }

	        for (Integer integer : list) {
				str+=integer;
			}
	        
	        return str;
	}
}
