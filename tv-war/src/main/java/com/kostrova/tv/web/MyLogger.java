package com.kostrova.tv.web;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Loggable
@Interceptor
public class MyLogger implements Serializable {
	private static final long serialVersionUID = 1L;

	@AroundInvoke
	public Object addLog(InvocationContext context) throws Exception {
		final Logger LOGGER = LogManager.getLogger(context.getTarget().getClass().getName()); 
		
		LOGGER.info(context.getMethod().getName() + " procedure started");

		Object ret = null;
		try {
			ret = context.proceed();
			LOGGER.info(context.getMethod().getName() + " procedure ended");
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace()); 
			throw e;
		}			
		return ret;
	}
}
