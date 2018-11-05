package cn.flydiy.demo.service;

import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.demo.entity.RequestLog;
import cn.flydiy.demo.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
@Service
public class RequestLogServiceImpl extends BaseServiceImpl<RequestLog> implements RequestLogService {

    @Autowired
    RequestLogRepository requestLogRepository;

    @Override
    public List<RequestLog> queryAll() {
        List<RequestLog> list = requestLogRepository.queryAll();
        return list;
    }


}
