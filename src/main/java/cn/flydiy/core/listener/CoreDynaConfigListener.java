package cn.flydiy.core.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by flying on 16-11-26.
 */
@Component
public class CoreDynaConfigListener extends DynaConfigListener {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected Set<String> getNoAuthFilterPath() {
        Set<String> pathSet = new HashSet<>();
        pathSet.add("/auth!queryCurrentUser"); // 查询当前登录用户
        pathSet.add("system!findConfig"); // 获取系统配置
        pathSet.add("auth!login"); // 登录
        pathSet.add("auth!thirdPartyLogin"); // 第三方登录
        pathSet.add("auth!loginAndBind"); // 登录和绑定
        pathSet.add("auth!logout"); // 注销
        pathSet.add("auth!getFiveParams");//获取钉钉五个参数
        pathSet.add("voiceAssistant!analyzeVoice");
        pathSet.add("auth!testOnline"); // 测试服务器是否启动了
        pathSet.add("dictItem!queryDictItemListByDictCodes");
        pathSet.add("frontErrorLog!saveEntity");//前端错误日志记录

        logger.debug(pathSet);

        return pathSet;
    }
}
