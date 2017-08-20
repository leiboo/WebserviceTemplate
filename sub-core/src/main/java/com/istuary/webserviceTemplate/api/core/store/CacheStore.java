package com.istuary.webserviceTemplate.api.core.store;


/**
 * Created by lenovo on 2016/12/29.
 */
public class CacheStore {

//    @Autowired
//    HttpHelper httpHelper;
//
//    public JSONObject getMasterInfoCache(String key) throws ExecutionException {
//        return masterInfoCache.get(key);
//    }
//
//    // 缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
//    LoadingCache<String, JSONObject> masterInfoCache
//            //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
//            = CacheBuilder.newBuilder()
//            //设置写缓存后60秒钟过期
//            .expireAfterWrite(60, TimeUnit.SECONDS)
//            .maximumSize(1000)
//            //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
//            .build(
//                    new CacheLoader<String, JSONObject>() {
//                        @Override
//                        public JSONObject load(String key) throws Exception {
//                            if (key == "masterInfo") {
//
//                                return getMasterInfoJson();
//                            } else {
//                                return null;
//                            }
//                        }
//
//                        private JSONObject getMasterInfoJson() {
//                            JSONObject masterInfoJson = new JSONObject();
//
//                            String mesosMasterUrl = configStore.get(ConfigConstant.MASTER_URL_MESOS);
//
//                            String url = mesosMasterUrl + "master/state";
//                            final HttpResponseResult masterInfo = httpHelper.getResponseInfo("GET", url, "");
//
//                            String responseBody = String.valueOf(masterInfo.getResponseBody());
//                            if (Strings.isNullOrEmpty(responseBody)) return masterInfoJson;
//
//                            masterInfoJson = JSON.parseObject(responseBody);
//
//                            return masterInfoJson;
//                        }
//                    }
//            );


}