package com.istuary.webserviceTemplate.api.core.service;

import com.istuary.webserviceTemplate.api.common.entity.UserInfo;
import com.istuary.webserviceTemplate.api.common.helper.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhenhua.li on 16/11/2.
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    HttpHelper httpHelper;

    @Override
    public String getPwdByName(String name) {

//        List<UserInfo> users = new ArrayList<>();
//        String userinfos = configStore.get(ConfigConstant.USER_INFO);
//        try {
//            if (!Strings.isNullOrEmpty(userinfos)) {
//                for (String info : userinfos.split(",")) {
//                    String[] infos = info.split(":");
//                    users.add(new UserInfo(infos[0], infos[1]));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error(e.getMessage());
//        }
//
//        for (UserInfo userInfo : users) {
//            if (userInfo.getName().equals(name)) {
//                return userInfo.getPassword();
//            }
//        }
        return null;
    }

    @Override
    public UserInfo getUserByName(String name) {

        UserInfo userInfo = new UserInfo("111","222");

        return userInfo;
    }

}