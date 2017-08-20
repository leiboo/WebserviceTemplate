package com.istuary.webserviceTemplate.api.webapp.intercepters;

import com.istuary.webserviceTemplate.api.webapp.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingExceptionResolver extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(LoggingExceptionResolver.class);

	@Autowired
	HttpUtil httpUtil;

	ThreadLocal<Long> timeRecorder = new ThreadLocal<Long>();
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		timeRecorder.set(System.currentTimeMillis());
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		ElapsedTimeCustomLog.timeLog(httpUtil.getHttpURI(request)+"@ elapsedTime(ms)"+(System.currentTimeMillis()-timeRecorder.get()));
		timeRecorder.remove();
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
        if (null != ex) {
            logger.error("[webserviceTemplate-api error " + httpUtil.getHttpURI(request) + "]" + ex.getMessage(), ex);
        }
        if (timeRecorder.get() != null) {
            timeRecorder.remove();
        }
        super.afterCompletion(request, response, handler, ex);
    }
}
