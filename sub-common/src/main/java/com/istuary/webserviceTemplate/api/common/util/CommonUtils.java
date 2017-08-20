package com.istuary.webserviceTemplate.api.common.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by leibo.dai on 2016/11/8.
 */

@Component
public class CommonUtils {

    public static HashMap getResultMap(){
        HashMap result = new HashMap();
        result.put("success",true);
        return result;
    }
}
