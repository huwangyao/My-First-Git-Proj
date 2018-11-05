package cn.flydiy.demo.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.demo.entity.RequestLog;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
@Repository
public class RequestLogRepositoryImpl extends BaseRepositoryImpl<RequestLog> implements RequestLogRepository {
    @Override
    public List<RequestLog> queryAll() {

        DBHelper<RequestLog> dbHelper = getDbHelper();
        List<RequestLog> list = dbHelper.list();
        return list;
    }


}
