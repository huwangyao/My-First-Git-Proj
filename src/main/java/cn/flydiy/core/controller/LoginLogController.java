package cn.flydiy.core.controller;

import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.service.LoginLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by flying on 16-12-26.
 */
@WebController
public class LoginLogController extends BaseController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    LoginLogService loginLogService;

}