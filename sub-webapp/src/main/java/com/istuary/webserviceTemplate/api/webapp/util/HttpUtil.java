package com.istuary.webserviceTemplate.api.webapp.util;

import com.istuary.webserviceTemplate.api.common.entity.HttpResponseResult;
import org.apache.http.Header;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by zhenhua.li on 2016/11/23.
 */
public class HttpUtil {

    public String getHttpBody(HttpServletRequest request ){
        String requestBody = "";
        try {
            BufferedReader br = request.getReader();
            if(br != null) {
                String str;
                while ((str = br.readLine()) != null) {
                    requestBody += str;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return requestBody;
    }

    public String getHttpURI(HttpServletRequest request ){
        String uri = "";
//        try {
//            uri = URLEncoder.encode(request.getServletPath(),"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        uri = request.getRequestURI();
        return uri;
    }

    public void setApiResponse(HttpServletResponse response, HashMap responseInfo) {
        if (responseInfo.get("headers") != null) {
            Header[] allHeader = (Header[]) responseInfo.get("headers");
            for (Header header : allHeader) {
                if (header.getName().equals("Transfer-Encoding")) {      //Transfer-Encoding: chunked 会导致数据无法正常返回。
                    continue;
                }
                response.setHeader(header.getName(), header.getValue());
            }
        }
        response.setStatus(Integer.parseInt(responseInfo.get("statusCode").toString()));
    }

    public void setApiResponse(HttpServletResponse response, HttpResponseResult responseInfo) {
        if (responseInfo.getHeaders() != null) {
            Header[] allHeader = responseInfo.getHeaders();
            for (Header header : allHeader) {
                if (header.getName().equals("Transfer-Encoding")) {      //Transfer-Encoding: chunked 会导致数据无法正常返回。
                    continue;
                }
                response.setHeader(header.getName(), header.getValue());
            }
        }
        response.setStatus(responseInfo.getStatusCode());
    }
}
