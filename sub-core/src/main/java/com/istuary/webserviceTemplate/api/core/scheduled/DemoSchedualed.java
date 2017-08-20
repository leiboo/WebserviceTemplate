package com.istuary.webserviceTemplate.api.core.scheduled;

import com.istuary.webserviceTemplate.api.core.service.HeartbeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2017/3/12.
 */
@Component
public class DemoSchedualed {
    private static final Logger LOG = LoggerFactory.getLogger(DemoSchedualed.class);

    @Autowired
    HeartbeatService heartbeatService;

    private void obtainAllStatus() {
        heartbeatService.getContent();
    }

    public void pollingJob() {
        this.obtainAllStatus();
    }
}
