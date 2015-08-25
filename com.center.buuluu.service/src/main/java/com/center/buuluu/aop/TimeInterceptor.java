package com.center.buuluu.aop;

import java.text.DecimalFormat;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 
 * @author More
 */

@Component
@Aspect
public class TimeInterceptor  {

	//private Log controllerTimelog = LogFactory.getLog("CONTROLLER_FUNCTION_TIME_LOG");
	
	private Log requestParameterLog = LogFactory.getLog("REQUEST_PARAMETER_LOG");
	
	@Around(value = "execution(public * com.center.buuluu.web.controller.webservice.*.*(..)) ")
	public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
		long procTime = System.currentTimeMillis();
		try {
			Object result = pjp.proceed();
			return result;
		} finally {
			String sec = new DecimalFormat("0.000").format(((System.currentTimeMillis() - procTime) / 1000D));
			requestParameterLog.info(sec + " sec [" + pjp.getSignature().getName() + "]");
		}
	}
}
