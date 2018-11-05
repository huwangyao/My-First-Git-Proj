package cn.flydiy.web.listener;

import cn.flydiy.core.listener.DynaConfigListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by flying on 16-11-26.
 */
@Component
public class WebDynaConfigListener extends DynaConfigListener {


    private static final Logger logger = LogManager.getLogger();

    @Override
    protected Set<String> getNoAuthFilterPath() {
        Set<String> pathSet = new HashSet<>();
        pathSet.add("/attachment!upload"); // 文件上传

        logger.debug(pathSet);

        return pathSet;
    }
}
