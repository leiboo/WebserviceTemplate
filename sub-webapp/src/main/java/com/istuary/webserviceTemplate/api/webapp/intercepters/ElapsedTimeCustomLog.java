package com.istuary.webserviceTemplate.api.webapp.intercepters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElapsedTimeCustomLog{


	private static final Logger logger = LoggerFactory.getLogger(ElapsedTimeCustomLog.class);

	public static void timeLog(String objLogInfo){
		logger.info(objLogInfo);
	}

}
