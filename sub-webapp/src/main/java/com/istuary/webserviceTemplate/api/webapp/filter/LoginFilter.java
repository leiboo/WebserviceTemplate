package com.istuary.webserviceTemplate.api.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Strings;
import com.istuary.webserviceTemplate.api.common.constant.ConfigConstant;
import com.istuary.webserviceTemplate.api.core.service.LoginService;
import com.istuary.webserviceTemplate.api.core.store.ConfigStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Title:sub-webapp-LoginFilter</p>
 * <p>Description:</p>
 * <p>Company:istuary</p>
 *
 * @author dulei
 * @date 2016/11/28
 */
public class LoginFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    ConfigStore configStore;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (null != getAuthenticationPassUrl() && getAuthentication()) {

            if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
                LOG.error("OncePerRequestFilter just supports HTTP requests");
                throw new ServletException("OncePerRequestFilter just supports HTTP requests");
            }

            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            HttpSession session = httpRequest.getSession(true);

            StringBuffer url = httpRequest.getRequestURL();
            LOG.info(url.toString());

            boolean authenticationPass = false;
            for (String u : getAuthenticationPassUrl()) {
                if (url.toString().contains(u)) {
                    authenticationPass = true;
                }
            }

            if (!authenticationPass) {
//                Object user = session.getAttribute("user");
//                // add keystone token check
//                if (user == null || !loginService.checkTokenFromKeyStone(((UserInfo)user).getKeystoneToken()) ) {
//                    httpResponse.getWriter().write("{\"isSuccess\":false,\"error\":\"Not logged in or session has expired\"}");
//                    httpResponse.setStatus(401); // setStatus Unauthorized
//                    return;
//                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }


    public boolean getAuthentication() {
        return Boolean.valueOf(configStore.get(ConfigConstant.LOGIN_AUTHENTICATION_SWITCH));
    }

    public String[] getAuthenticationPassUrl() {
        if (!Strings.isNullOrEmpty(configStore.get(ConfigConstant.LOGIN_AUTHENTICATION_PASS_URL))) {
            return configStore.get(ConfigConstant.LOGIN_AUTHENTICATION_PASS_URL).split(",");
        } else {
            return null;
        }
    }

}
