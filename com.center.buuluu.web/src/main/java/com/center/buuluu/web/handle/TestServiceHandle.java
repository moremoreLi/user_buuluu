package com.center.buuluu.web.handle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.center.buuluu.service.UserService;

public class TestServiceHandle {

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private UserService userService;

	public void test() throws Exception {
		
		log.info("-------test start-------");
		
		
	 userService.getUserById("1939db63ce5746d78e2c5dd87d4366bb","z");
		

		log.info("-------test end-------");
	}
}
