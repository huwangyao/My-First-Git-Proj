package cn.flydiy.demo.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.demo.entity.RequestLog;

import java.util.Date;
import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
public interface RequestLogService extends BaseService<RequestLog> {
    List<RequestLog> queryAll();




}
