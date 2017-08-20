package com.istuary.webserviceTemplate.api.webapp.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * not Used
 *
 * Created by zhenhua.li on 2016/11/15.
 */
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                                     throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
//        res.setHeader("Access-Control-Allow-Origin", "*"); //TODO allow all domain
//        res.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");
//        res.setHeader("Access-Control-Max-Age", "0");
//        res.setHeader("Access-Control-Allow-Headers", "Origin, accept,No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
//        res.setHeader("Access-Control-Allow-Credentials", "true");
//        res.setHeader("XDomainRequestAllowed","1");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
