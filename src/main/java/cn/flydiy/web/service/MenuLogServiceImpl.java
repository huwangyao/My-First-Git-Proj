package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.web.entity.MenuLog;
import cn.flydiy.web.repository.MenuLogRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class MenuLogServiceImpl extends BaseServiceImpl<MenuLog> implements MenuLogService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    MenuLogRepository menuLogRepository;

}
