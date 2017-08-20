package com.istuary.webserviceTemplate.api.core.service;

import com.istuary.webserviceTemplate.api.common.entity.UserInfo;

/**
 * Created by zhenhua.li on 16/11/2.
 */
public interface LoginService {

    public String getPwdByName(String name);

    UserInfo getUserByName(String name);
}
