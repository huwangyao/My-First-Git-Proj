package cn.flydiy.core.cache;

import cn.flydiy.common.util.ExceptionUtil;
import cn.flydiy.common.util.HttpRequestUtil;
import cn.flydiy.common.util.JsonUtil;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * Created by player on 2018/2/6.
 */
public class CacheAccessToken {
    public static final String ACCESS_CACHE_PREFIX = "thirdParty";

    public static final String DINGDING_TYPE = "DING";

    public static final String WECHAT_TYPE = "WECHAT";
    /**
     * 获取需要对比的字符串数组
     * @param prefix
     * @param flag
     * @return
     */
    public static String getAccessToken(String prefix, String flag){
        String accessToken = "";
        if(CacheManager.get(prefix,flag)==null){
            if(DINGDING_TYPE.equals(flag)){
                String corpId = "ding99f9d852bdc6de9d35c2f4657eb6378f";
                String tokenResult = HttpRequestUtil.sendGet("https://oapi.dingtalk.com/gettoken", "corpid="+corpId+"&corpsecret=sjZRdRWpFZQGuO4bz6YjtRaPFv6nG4giSu0zl6Xi1PzYz9bVwZZSB7LVQYNDBqHh");
                Map accessMap = JsonUtil.getMapByJson(tokenResult);
                accessToken = MapUtils.getString(accessMap, "access_token");
            }else if(WECHAT_TYPE.equals(flag)){
                //TODO
                ExceptionUtil.throwBaseRunTimeException("微信access缓存仍未配置","微信access缓存仍未配置");
            }
            CacheManager.put(prefix,flag,accessToken,2*60*60);
        }else{
            accessToken = (String) CacheManager.get(prefix,flag);
        }
        return accessToken;
    }
}
