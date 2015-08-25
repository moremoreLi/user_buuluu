package com.center.buuluu.web.view;

import java.io.Serializable;

/**
 * 
 * @author More
 * 2015.7.15
 * 返回用户的基本的信息
 *
 */
public class UserVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 620375892224509487L;
	private String userId;//32位Md5唯一的用户id
	private String token;//32位Md5的会话token
	private Integer sex;//性别
	private String icon;//头像
	private String birthday;//出生日期
	private String country;//注册国籍
	private Double log;//经度
	private Double lat;//纬度
	private String tel;//电话号码
	private String nickName;//用户昵称
	private Float flowCoins;//用户buuluu账户的流量(1M＝1coins)
	private Boolean isRegistration;//用户是否签到(1:签到 0:未签到)
	private Float makeFlow;//奖励流量
	private Float UserFlow;//用户当前流量
	private Integer credit;//用户当前信用值
	private String helpUrl;//帮助界面的url
	
	public UserVO(){
		if(this.sex==null)
		this.sex=1;
		if(this.icon==null)
			this.icon="";
		if(this.birthday==null)
			this.birthday=System.currentTimeMillis()+"";
		if(this.country==null)
			this.country="";
		if(this.log==null)
			this.log=0.0;
		if(this.lat==null)
			this.lat=0.0;
		if(this.nickName==null)
			this.nickName="";
		if(this.flowCoins==null)
			this.flowCoins=0F;
		if(this.isRegistration==null)
			this.isRegistration=false;
		if(this.makeFlow==null)
			this.makeFlow=0F;
		if(this.UserFlow==null)
			this.UserFlow=0F;
		if(this.credit==null)
			this.credit=0;
		if(this.helpUrl==null)
			this.helpUrl="www.baidu.com";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getLog() {
		return log;
	}

	public void setLog(Double log) {
		this.log = log;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Float getFlowCoins() {
		return flowCoins;
	}

	public void setFlowCoins(Float flowCoins) {
		this.flowCoins = flowCoins;
	}

	public Boolean getIsRegistration() {
		return isRegistration;
	}

	public void setIsRegistration(Boolean isRegistration) {
		this.isRegistration = isRegistration;
	}

	public Float getMakeFlow() {
		return makeFlow;
	}

	public void setMakeFlow(Float makeFlow) {
		this.makeFlow = makeFlow;
	}

	
	public Float getUserFlow() {
		return UserFlow;
	}

	public void setUserFlow(Float userFlow) {
		this.UserFlow = userFlow;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getHelpUrl() {
		return helpUrl;
	}

	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}
}
