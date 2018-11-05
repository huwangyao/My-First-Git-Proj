package cn.flydiy.core.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.entity.ApiAddressVar;
import org.springframework.stereotype.Repository;

/**
 * Created by player on 2018/5/11.
 */
@Repository
public class ApiAddressVarRepositoryImpl extends BaseRepositoryImpl<ApiAddressVar> implements ApiAddressVarRepository {
    @Override
    public void deleteAll() {
        DBHelper<ApiAddressVar> dbHelper = getDbHelper();
        dbHelper.delete();
    }
}
