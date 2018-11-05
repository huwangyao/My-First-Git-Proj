package cn.flydiy.demo.service;

import cn.flydiy.core.service.BaseIntServiceImpl;
import cn.flydiy.demo.entity.RequestLogInt;
import cn.flydiy.demo.repository.RequestLogRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
@Service
public class RequestLogServiceInt extends BaseIntServiceImpl<RequestLogInt> {

    @Autowired
    RequestLogRepositoryInt requestLogRepositoryInt;

    public List<RequestLogInt> queryAll() {

        List<RequestLogInt> list = requestLogRepositoryInt.queryAll();
        return list;
    }

}
