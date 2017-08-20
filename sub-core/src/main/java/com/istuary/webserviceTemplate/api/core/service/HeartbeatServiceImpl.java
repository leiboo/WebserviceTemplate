package com.istuary.webserviceTemplate.api.core.service;

import com.istuary.webserviceTemplate.api.dal.custom.DemoCustomMapper;
import com.istuary.webserviceTemplate.api.dal.generated.DemoDO;
import com.istuary.webserviceTemplate.api.dal.generated.DemoDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhenhua.li on 16/11/2.
 */
@Service
public class HeartbeatServiceImpl implements HeartbeatService {


    @Autowired
    private DemoDOMapper demoDOMapper;

    @Autowired
    private DemoCustomMapper demoCustomMapper;

    @Override
    public String getContent() {
        return "working";
    }



    @Override
    public DemoDO getDemoById(Integer demoId) {

//        DemoDOCriteria example = new DemoDOCriteria();
//        example.createCriteria().andIdEqualTo(demoId);
//        return demoDOMapper.selectByExample(example);
        DemoDO demoDO = demoCustomMapper.findDemoById(demoId);
//        DemoDO demoDO = demoDOMapper.selectByPrimaryKey(demoId);
        return demoDO;
    }

    @Override
    public List<DemoDO> getDemos(){

        List<DemoDO> demoDO = demoCustomMapper.getCustomDemoByUserId(1);
        return demoDO;
    }
}