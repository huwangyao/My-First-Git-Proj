package cn.flydiy.demo.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.repository.BaseRepositoryImpl2;
import cn.flydiy.demo.entity.RequestLog2;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
@Repository
public class RequestLogRepositoryImpl2 extends BaseRepositoryImpl2<RequestLog2> {
    public List<RequestLog2> queryAll() {

        DBHelper<RequestLog2> dbHelper = getDbHelper();
        List<RequestLog2> list = dbHelper.list();
        return list;
    }
}
