package com.istuary.webserviceTemplate.api.webapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.istuary.webserviceTemplate.api.common.entity.DefaultServiceResult;
import com.istuary.webserviceTemplate.api.common.entity.UserInfo;
import com.istuary.webserviceTemplate.api.core.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zhenhua.li on 17/3/6.
 */
@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/auth/login")
    @ResponseBody
    public DefaultServiceResult login(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws IOException {

    	HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60 * 60 * 18);
		
		try {

			JSONObject requestBodyJson = JSONObject.parseObject(data);
			String name = requestBodyJson.getString("name");
			String pwd = requestBodyJson.getString("pwd");

			if (name != null && pwd != null ) {
				UserInfo userInfo = loginService.getUserByName(name);
				if (userInfo != null) {
					session.setAttribute("user", userInfo);
					DefaultServiceResult defaultServiceResult = new DefaultServiceResult(true);
					JSONObject jsonData = new JSONObject();
					jsonData.put("name", name);
					jsonData.put("id",userInfo.getUid());
					jsonData.put("roles",userInfo.getRoles());
					jsonData.put("opers",userInfo.getOpers());
					defaultServiceResult.setData(jsonData);
					return defaultServiceResult;
				}
			}

			// if not get Token from keystone
			session.invalidate();
			return new DefaultServiceResult(false,"User name or password is incorrect");

		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}

		return new DefaultServiceResult(false);
    }
    
    @RequestMapping(value = "/auth/logout")
    @ResponseBody
    public DefaultServiceResult logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	LOG.info("logout ....");
    	
    	HttpSession session = request.getSession();

		Object user = session.getAttribute("user");
		if (user != null) {
//			String keystoneToken = ((UserInfo)user).getKeystoneToken();
//			loginService.revokeTokenFromKeyStone(keystoneToken);
		}

		session.invalidate();

		return new DefaultServiceResult(true);
    }
    
}
