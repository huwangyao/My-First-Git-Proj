package cn.flydiy.demo.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.repository.BaseIntRepositoryImpl2;
import cn.flydiy.demo.entity.RequestLogInt2;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
@Repository
public class RequestLogRepositoryInt2 extends BaseIntRepositoryImpl2<RequestLogInt2> {
    public List<RequestLogInt2> queryAll() {

        DBHelper<RequestLogInt2> dbHelper = getDbHelper();
        List<RequestLogInt2> list = dbHelper.list();
        return list;
    }



}
