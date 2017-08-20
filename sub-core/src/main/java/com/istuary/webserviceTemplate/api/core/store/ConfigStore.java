package com.istuary.webserviceTemplate.api.core.store;

import com.istuary.webserviceTemplate.api.common.constant.ConfigConstant;
import com.istuary.webserviceTemplate.api.common.helper.HttpHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by lenovo on 2016/12/9.
 */
@Component
public class ConfigStore {

    @Autowired
    HttpHelper httpHelper;

    private HashMap<String, Object> configMap;

    private String[] configFromSystemConfig = {
        ConfigConstant.LOGIN_AUTHENTICATION_SWITCH,
        ConfigConstant.LOGIN_AUTHENTICATION_PASS_URL
    };

    public ConfigStore(HashMap<String, Object> configMap) throws Exception {
        this.configMap = configMap;

        if (this.configMap == null) this.configMap = new HashMap<>();

        for (String configKey : configFromSystemConfig) {
            String envConfigValue = System.getenv(configKey);
            if (envConfigValue != null) {
                this.configMap.put(configKey, envConfigValue);
            }
        }

    }

    public HashMap<String, Object> getConfigMap() {
        return configMap;
    }


    public String get(String key) {
        if (configMap == null || configMap.isEmpty()) return null;
        return configMap.get(key).toString();
    }

    public boolean set(String key, String value) {

        if (configMap == null) configMap = new HashMap<>();
        configMap.put(key, value);

        return true;
    }

}