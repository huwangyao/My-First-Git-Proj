package cn.flydiy.serial.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.serial.entity.QrCodeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by player on 2017/9/20.
 */
@Repository
public class QrCodeInfoRepositoryImpl extends BaseRepositoryImpl<QrCodeInfo> implements QrCodeInfoRepository{
    @Override
    public List<QrCodeInfo> queryByOnlyCode(String onlyCode) {
        DBHelper<QrCodeInfo> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("onlyCode",onlyCode));
        return dbHelper.list();
    }
}
