package cn.flydiy.web.controller;

import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.web.service.MenuLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by flying on 16-12-26.
 */
@WebController
public class MenuLogController extends BaseController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    MenuLogService menuLogService;

}