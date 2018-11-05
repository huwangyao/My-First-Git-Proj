package cn.flydiy.demo.service;

import cn.flydiy.core.service.BaseServiceImpl2;
import cn.flydiy.demo.entity.RequestLog2;
import cn.flydiy.demo.repository.RequestLogRepositoryImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
@Service
public class RequestLogServiceImpl2 extends BaseServiceImpl2<RequestLog2> {

    @Autowired
    RequestLogRepositoryImpl2 requestLogRepository2;

    public List<RequestLog2> queryAll() {

        List<RequestLog2> list = requestLogRepository2.queryAll();
        return list;
    }


}
