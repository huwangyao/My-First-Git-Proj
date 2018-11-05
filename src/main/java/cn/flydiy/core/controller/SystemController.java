package cn.flydiy.core.controller;


import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.common.ResourceUtils;
import cn.flydiy.core.web.ResponseData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统配置
 * Created by flying on 16-12-2.
 */
@WebController
public class SystemController extends BaseController {

    private static final Logger logger = LogManager.getLogger();


    /**
     * 查询系统配置(不登录就能访问的)
     */
    public void findConfig() {

        Map configMap = new HashMap();

        configMap.put("assistant", ResourceUtils.getVoiceAssistant());// 语音助手开关
//        configMap.put("chat", ); // 聊天开关

        super.render(new ResponseData(configMap));
    }

    /**
     * 查询系统配置(登录后才允许访问), 暂时没有其他地方使用
     */
    public void findAuthConfig() {

        Map configMap = new HashMap();

        configMap.put("assistant", ResourceUtils.getVoiceAssistant());// 语音助手开关
//        configMap.put("chat", ); // 聊天开关

        super.render(new ResponseData(configMap));
    }

}