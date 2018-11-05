package cn.flydiy.demo.controller;

import cn.flydiy.common.util.collect.Maps;
import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.demo.entity.RequestLog;
import cn.flydiy.demo.entity.RequestLog2;
import cn.flydiy.demo.entity.RequestLogInt;
import cn.flydiy.demo.entity.RequestLogInt2;
import cn.flydiy.demo.service.RequestLogService;
import cn.flydiy.demo.service.RequestLogServiceImpl2;
import cn.flydiy.demo.service.RequestLogServiceInt;
import cn.flydiy.demo.service.RequestLogServiceInt2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
@WebController
public class RequestLogController extends BaseController {

    @Autowired
    RequestLogService requestLogService;
    @Autowired
    RequestLogServiceImpl2 requestLogService2;
    @Autowired
    RequestLogServiceInt requestLogServiceInt;
    @Autowired
    RequestLogServiceInt2 requestLogServiceInt2;

    public void queryAll() {

        RequestLog requestLogInt4 = new RequestLog();
        requestLogInt4.setUrl("from-save");
        RequestLog requestLogInt3 = new RequestLog();
        requestLogInt3.setUrl("from-save");
        RequestLog requestLogInt2 = new RequestLog();
        requestLogInt2.setUrl("from-save");
        
        
        requestLogService.save(requestLogInt2,requestLogInt3,requestLogInt4);

        requestLogInt2.setUsername("from-update");
        requestLogService._updateEntityById(requestLogInt2);

        requestLogService._deleteByEntityId(requestLogInt3);

        List<RequestLog> byIds = requestLogService.findByIds(requestLogInt2.getId(), requestLogInt3.getId(),requestLogInt4.getId());

        List<RequestLog> requestLogs = requestLogService.queryAll();

        super.render(new ResponseData(Maps.create("currSave",byIds).put("all",requestLogs).collect()));
    }

    public void queryAll2() {

        RequestLog2 requestLogInt4 = new RequestLog2();
        requestLogInt4.setUrl("from-save");
        RequestLog2 requestLogInt3 = new RequestLog2();
        requestLogInt3.setUrl("from-save");
        RequestLog2 requestLogInt2 = new RequestLog2();
        requestLogInt2.setUrl("from-save");


        requestLogService2.save(requestLogInt2,requestLogInt3,requestLogInt4);

        requestLogInt2.setUsername("from-update");
        requestLogService2._updateEntityById(requestLogInt2);

        requestLogService2._deleteByEntityId(requestLogInt3);

        List<RequestLog2> byIds = requestLogService2.findByIds(requestLogInt2.getId(), requestLogInt3.getId(),requestLogInt4.getId());

        List<RequestLog2> requestLogs = requestLogService2.queryAll();

        super.render(new ResponseData(Maps.create("currSave",byIds).put("all",requestLogs).collect()));
    }

    public void queryAllInt() {

        RequestLogInt requestLogInt4 = new RequestLogInt();
        requestLogInt4.setUrl("from-save");
        RequestLogInt requestLogInt3 = new RequestLogInt();
        requestLogInt3.setUrl("from-save");
        RequestLogInt requestLogInt2 = new RequestLogInt();
        requestLogInt2.setUrl("from-save");


        requestLogServiceInt.save(requestLogInt2,requestLogInt3,requestLogInt4);

        requestLogInt2.setUsername("from-update");
        requestLogServiceInt._updateEntityById(requestLogInt2);

        requestLogServiceInt._deleteByEntityId(requestLogInt3);

        List<RequestLogInt> byIds = requestLogServiceInt.findByIds(requestLogInt2.getId(), requestLogInt3.getId(),requestLogInt4.getId());

        List<RequestLogInt> requestLogs = requestLogServiceInt.queryAll();

        super.render(new ResponseData(Maps.create("currSave",byIds).put("all",requestLogs).collect()));
    }

    public void queryAllInt2() {

        RequestLogInt2 requestLogInt4 = new RequestLogInt2();
        requestLogInt4.setUrl("from-save");
        RequestLogInt2 requestLogInt3 = new RequestLogInt2();
        requestLogInt3.setUrl("from-save");
        RequestLogInt2 requestLogInt2 = new RequestLogInt2();
        requestLogInt2.setUrl("from-save");


        requestLogServiceInt2.save(requestLogInt2,requestLogInt3,requestLogInt4);

        requestLogInt2.setUsername("from-update");
        requestLogServiceInt2._updateEntityById(requestLogInt2);

        requestLogServiceInt2._deleteByEntityId(requestLogInt3);

        List<RequestLogInt2> byIds = requestLogServiceInt2.findByIds(requestLogInt2.getId(), requestLogInt3.getId(),requestLogInt4.getId());

        List<RequestLogInt2> requestLogs = requestLogServiceInt2.queryAll();

        super.render(new ResponseData(Maps.create("currSave",byIds).put("all",requestLogs).collect()));
    }
}
