package com.istuary.webserviceTemplate.api.core.service;


import com.istuary.webserviceTemplate.api.dal.generated.DemoDO;

import java.util.List;

/**
 * Created by zhenhua.li on 16/11/2.
 */
public interface HeartbeatService {


    String getContent();

    DemoDO getDemoById(Integer demoId);

    List<DemoDO> getDemos();

}
