<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CARTIER API JSP</title>
</head>
<Body>
<!-- 1 -->
<form name="EventForm" action="../test.do" method="post">
		<h2>API test</h2>
		<input type="submit" style="width: 200px" value="Test"/>
</form>

<form name="EventForm" action="../login.do" method="post">
		<h2>API login.do</h2>
		tel:<input name="tel" type="text"/>
		pwd:<input name="pwd" type="text"/>
		lang:<input name="lang" type="text"/>
		countryCode:<input name="countryCode" type="text"/>
		device:<input name="device" type="text"/>
		imei:<input name="imei" type="text"/>
		mac:<input name="mac" type="text"/>
		imsi:<input name="imsi" type="text"/>
		<input type="submit" style="width: 200px" value="login"/>
</form>

<form name="EventForm" action="../register.do" method="post">
		<h2>API register.do</h2>
		lang:<input name="lang" type="text"/>
		device:<input name="device" type="text"/>
		deviceVerNum:<input name="deviceVerNum" type="text"/>
		imei:<input name="imei" type="text"/>
		mac:<input name="mac" type="text"/>
		imsi:<input name="imsi" type="text"/>
		tel:<input name="tel" type="text"/>
		pwd:<input name="pwd" type="text"/>
		countryCode:<input name="countryCode" type="text"/>
		pushStatus:<input name="pushStatus" type="text"/>
		log:<input name="log" type="text"/>
		lat:<input name="lat" type="text"/>
		activation:<input name="activation" type="text"/>
		<input type="submit" style="width: 200px" value="register"/>
</form>

<form name="EventForm" action="../quickRegister.do" method="post">
		<h2>API quickRegister.do</h2>
		lang:<input name="lang" type="text"/>
		device:<input name="device" type="text"/>
		deviceVerNum:<input name="deviceVerNum" type="text"/>
		imei:<input name="imei" type="text"/>
		mac:<input name="mac" type="text"/>
		imsi:<input name="imsi" type="text"/>
		tel:<input name="tel" type="text"/>
		pwd:<input name="pwd" type="text"/>
		countryCode:<input name="countryCode" type="text"/>
		pushStatus:<input name="pushStatus" type="text"/>
		log:<input name="log" type="text"/>
		lat:<input name="lat" type="text"/>
		<input type="submit" style="width: 200px" value="quickRegister"/>
</form>

<form name="EventForm" action="../sendActivation.do" method="post">
		<h2>API sendActivation.do</h2>
		lang:<input name="lang" type="text"/>
		device:<input name="device" type="text"/>
		deviceVerNum:<input name="deviceVerNum" type="text"/>
		imei:<input name="imei" type="text"/>
		mac:<input name="mac" type="text"/>
		imsi:<input name="imsi" type="text"/>
		countryCode:<input name="countryCode" type="text"/>
		tel:<input name="tel" type="text"/>
		type:<input name="type" type="text"/>
		<input type="submit" style="width: 200px" value="sendActivation"/>
</form>

<form name="EventForm" action="../verifyActivation.do" method="post">
		<h2>API verifyActivation.do</h2>
		lang:<input name="lang" type="text"/>
		device:<input name="device" type="text"/>
		deviceVerNum:<input name="deviceVerNum" type="text"/>
		imei:<input name="imei" type="text"/>
		mac:<input name="mac" type="text"/>
		imsi:<input name="imsi" type="text"/>
		countryCode:<input name="countryCode" type="text"/>
		tel:<input name="tel" type="text"/>
		activation:<input name="activation" type="text"/>
		<input type="submit" style="width: 200px" value="verifyActivation"/>
</form>

<form name="EventForm" action="../autoLogin.do" method="post">
		<h2>API autoLogin.do</h2>
		lang:<input name="lang" type="text"/>
		device:<input name="device" type="text"/>
		deviceVerNum:<input name="deviceVerNum" type="text"/>
		imei:<input name="imei" type="text"/>
		mac:<input name="mac" type="text"/>
		imsi:<input name="imsi" type="text"/>
		userId:<input name="userId" type="text"/>
		token:<input name="token" type="text"/>
		<input type="submit" style="width: 200px" value="autoLogin"/>
</form>

<form name="EventForm" action="../resetPassword.do" method="post">
		<h2>API resetPassword.do</h2>
		lang:<input name="lang" type="text"/>
		device:<input name="device" type="text"/>
		deviceVerNum:<input name="deviceVerNum" type="text"/>
		imei:<input name="imei" type="text"/>
		mac:<input name="mac" type="text"/>
		imsi:<input name="imsi" type="text"/>
		userId:<input name="userId" type="text"/>
		token:<input name="token" type="text"/>
		oldPwd:<input name="oldPwd" type="text"/>
		newPwd:<input name="newPwd" type="text"/>
		<input type="submit" style="width: 200px" value="resetPassword"/>
</form>

<form name="EventForm" action="../recoverPassword.do" method="post">
		<h2>API recoverPassword.do</h2>
		lang:<input name="lang" type="text"/>
		device:<input name="device" type="text"/>
		deviceVerNum:<input name="deviceVerNum" type="text"/>
		imei:<input name="imei" type="text"/>
		mac:<input name="mac" type="text"/>
		imsi:<input name="imsi" type="text"/>
		countryCode:<input name="countryCode" type="text"/>
		tel:<input name="tel" type="text"/>
		email:<input name="email" type="text"/>
		<input type="submit" style="width: 200px" value="recoverPassword"/>
</form>
</BODY>
</html>