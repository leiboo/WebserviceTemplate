package com.istuary.webserviceTemplate.api.webapp.controller;

import com.istuary.webserviceTemplate.api.common.entity.DefaultServiceResult;
import com.istuary.webserviceTemplate.api.core.service.HeartbeatService;
import com.istuary.webserviceTemplate.api.dal.generated.DemoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by zhenhua.li on 16/3/6.
 */
@Controller
public class HeartbeatAPIController {

    @Autowired
    private HeartbeatService heartbeatService;


    @RequestMapping(value = "/heartbeat")
    @ResponseBody
    public String getHeartbeat() {
        String content = "is working";
        content = heartbeatService.getContent();

        return content;
    }

    @RequestMapping(value = "/demo/{demo_id}", method = RequestMethod.GET)
    @ResponseBody
    public DefaultServiceResult getDemo(
            @PathVariable(value = "demo_id") Integer demoId) {
        if (demoId == null || StringUtils.isEmpty(demoId)) {
            return new DefaultServiceResult(
                    "demoId is empty!");
        }


        DemoDO demoDO = heartbeatService.getDemoById(demoId);
        if (demoDO ==null) {
            return new DefaultServiceResult(
                    "demo(id:"+demoId+") is not exist!");

        }
        return new DefaultServiceResult(demoDO);
    }

    @RequestMapping(value = "/demos", method = RequestMethod.GET)
    @ResponseBody
    public DefaultServiceResult getDemos() {

        List<DemoDO> demoDOs = heartbeatService.getDemos();
        return new DefaultServiceResult(demoDOs);
    }
}
