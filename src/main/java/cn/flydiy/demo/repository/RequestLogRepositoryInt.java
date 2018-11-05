package cn.flydiy.demo.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.repository.BaseIntRepositoryImpl;
import cn.flydiy.demo.entity.RequestLogInt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
@Repository
public class RequestLogRepositoryInt extends BaseIntRepositoryImpl<RequestLogInt> {
    public List<RequestLogInt> queryAll() {

        DBHelper<RequestLogInt> dbHelper = getDbHelper();
        List<RequestLogInt> list = dbHelper.list();
        return list;
    }



}
