package cn.flydiy.core.controller;


import cn.flydiy.common.util.ExceptionUtil;
import cn.flydiy.common.util.HttpClientUtil;
import cn.flydiy.common.util.JsonUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.common.ResourceUtils;
import cn.flydiy.core.web.ResponseData;
import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * esb统一请求接口
 * Created by flying on 16-12-2.
 */
@WebController
public class EsbRequestController extends BaseController {

    private static final Logger logger = LogManager.getLogger();

    /**
     * esb请求
     */
    public void send(){

        String esbRoot = ResourceUtils.getProperty("esb.request.url");
        if (StringUtil.isEmpty(esbRoot)) {
            ExceptionUtil.throwBaseRunTimeException("sys.esb.request.url.notset");
        }

        Map<String, Object> paramMap = getParamMap();

        String key_url = "service";
        String key_method = "method";
        String key_params = "params";

        String service = MapUtils.getString(paramMap, key_url);
        String url;

        if (service.startsWith("/")) {
            url = esbRoot + service;
        } else {
            url = esbRoot + "/" +service;
        }

        String method = MapUtils.getString(paramMap, key_method);
        Map<String,Object> tempMap = (Map<String, Object>) MapUtils.getMap(paramMap, key_params);

//        Map<String,String> map = new HashMap<>();
//        for (String key : tempMap.keySet()) {
//            String value = MapUtils.getString(tempMap, key);
//            map.put(key, value);
//        }


        String retVal;
        if (HttpClientUtil.METHOD_GET.equalsIgnoreCase(method)) {
            retVal = HttpClientUtil.getInstance().sendHttpGet(url);
        } else {
            Map postMap = new HashMap();
            postMap.put(key_params, JsonUtil.toJson(tempMap));
            retVal = HttpClientUtil.getInstance().sendHttpPost(url,postMap);
        }

        ResponseData responseData = new ResponseData(retVal);
        super.render(responseData);

    }

    /**
     * 请求外部资源,url是完整的httpURL
     */
    public void sendHttp(){
        Map<String, Object> paramMap = getParamMap();

        String key_url = "service";
        String key_method = "method";
        String key_params = "params";

        String url = MapUtils.getString(paramMap, key_url);

        Pattern pattern = Pattern.compile("(?<=\\{)(.+?)(?=\\})");
        Matcher matcher = pattern.matcher(url);
        while(matcher.find()){
            String group = matcher.group();
            url = url.replace("{" + group + "}",ResourceUtils.getProperty(group));
        }

        Map<String,Object> tempMap = (Map<String, Object>) MapUtils.getMap(paramMap, key_params);

        Map<String,String> map = new HashMap<>();
        if (tempMap!=null) {
            for (String key : tempMap.keySet()) {
                String value = MapUtils.getString(tempMap, key);
                map.put(key, value);
            }
        }


        String retVal;

        String method = MapUtils.getString(paramMap, key_method);
        if (HttpClientUtil.METHOD_GET.equalsIgnoreCase(method)) {
            retVal = HttpClientUtil.getInstance().sendHttpGet(url);
        } else {
            retVal = HttpClientUtil.getInstance().sendHttpPost(url,map);
        }

        Object mapByJson = null;
        ResponseData responseData = new ResponseData();
        try {
            if (StringUtil.isNotEmpty(retVal)) {
                retVal = retVal.trim();
                if (retVal.startsWith("[")) {
                    mapByJson = JsonUtil.getListByJson(retVal);
                }else if (retVal.startsWith("{")){
                    mapByJson = JsonUtil.getMapByJson(retVal);
                }else {
                    ExceptionUtil.throwBaseRunTimeException("sys.httpapi.response.error",new String[]{url,retVal});
                }
            }
            responseData.setObj(mapByJson);
        }catch (Exception e){
            ExceptionUtil.throwBaseRunTimeException("sys.httpapi.response.error",new String[]{url,retVal});
        }

        super.render(responseData);

    }

}