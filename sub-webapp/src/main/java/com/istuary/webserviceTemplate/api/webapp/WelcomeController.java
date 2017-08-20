package com.istuary.webserviceTemplate.api.webapp;

import com.istuary.webserviceTemplate.api.common.helper.HttpHelper;
import com.istuary.webserviceTemplate.api.webapp.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by istuary on 2015/5/29.
 */
@Controller
public class WelcomeController {


    @Autowired
    HttpHelper httpHelper;

    @Autowired
    HttpUtil httpUtil;

    @RequestMapping(value="/", method= RequestMethod.GET)
    @ResponseBody
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
        return "Hello WebService template !";
    }
}
