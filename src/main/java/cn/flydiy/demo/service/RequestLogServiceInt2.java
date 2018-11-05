package cn.flydiy.demo.service;

import cn.flydiy.core.service.BaseIntServiceImpl2;
import cn.flydiy.demo.entity.RequestLogInt2;
import cn.flydiy.demo.repository.RequestLogRepositoryInt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
@Service
public class RequestLogServiceInt2 extends BaseIntServiceImpl2<RequestLogInt2> {

    @Autowired
    RequestLogRepositoryInt2 requestLogRepositoryInt;

    public List<RequestLogInt2> queryAll() {

        List<RequestLogInt2> list = requestLogRepositoryInt.queryAll();
        return list;
    }

}
