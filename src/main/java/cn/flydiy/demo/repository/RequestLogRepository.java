package cn.flydiy.demo.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.demo.entity.RequestLog;

import java.util.Date;
import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
public interface RequestLogRepository extends BaseRepository<RequestLog> {
    List<RequestLog> queryAll();


}
