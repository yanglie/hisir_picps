package com.hisir.picps.aop;

import java.util.Arrays;

//import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Web层日志切面
 */
@Aspect
@Order(5)
@Component
public class WebLogAspect {
	// private Logger logger = Logger.getLogger(getClass());
	private static final Log logger = LogFactory.getLog(WebLogAspect.class);

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut("execution(public * com.hisir.picps..hclient.*Controller.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		//startTime.set(System.currentTimeMillis());
		logger.info(joinPoint.getSignature().getDeclaringType().getSimpleName()
				+ "." + joinPoint.getSignature().getName() + ", ARGS: " + Arrays.toString(joinPoint.getArgs()));
	}

//	@AfterReturning(returning = "ret", pointcut = "webLog()")
//	public void doAfterReturning(Object ret) throws Throwable {
//		// 处理完请求，返回内容
//		logger.info("RESPONSE : " + ret);
//		logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
//	}
}
