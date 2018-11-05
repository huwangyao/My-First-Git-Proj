package cn.flydiy.core.controller;


import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.entity.VisitLog;
import cn.flydiy.core.service.VisitLogService;
import cn.flydiy.core.web.WebUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

/**
 * Created by flying on 16-12-2.
 */
@WebController
public class VisitLogController extends BaseController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    VisitLogService visitLogService;


    /**
     * 前端访问日志
     */
    public void addVisitLog(){

        VisitLog visitLog = getParamObj(VisitLog.class);


        Optional.ofNullable(WebUtils.getLoginUser())
                .ifPresent(loginUser->{
                    visitLog.setUserId(loginUser.getId());
                    visitLog.setUserName(loginUser.getAccount());
                });



        Optional.ofNullable(visitLog.getStartTime())
                .map(startTime -> LocalDateTime.ofInstant(startTime.toInstant(), ZoneId.systemDefault()))
                .ifPresent(startTime->{
                    visitLog.setStartYear(startTime.getYear());
                    visitLog.setStartMonth(startTime.getMonthValue());
                    visitLog.setStartDay(startTime.getDayOfMonth());
                    visitLog.setStartHour(startTime.getHour());
                    visitLog.setStartMin(startTime.getMinute());
                });


        visitLogService.save(visitLog);
    }

}