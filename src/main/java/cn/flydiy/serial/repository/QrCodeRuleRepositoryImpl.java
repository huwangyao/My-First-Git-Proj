package cn.flydiy.serial.repository;

import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.serial.entity.QrCodeRule;
import cn.flydiy.serial.repository.QrCodeRuleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by player on 2017/9/7.
 */
@Repository
public class QrCodeRuleRepositoryImpl extends BaseRepositoryImpl<QrCodeRule> implements QrCodeRuleRepository {

    @Override
    public QrCodeRule queryByAttrCode(String tableCode, String attrCode) {
        DBHelper<QrCodeRule> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("tableCode",tableCode));
        dbHelper.add(Expression.eq("attrCode",attrCode));
        List<QrCodeRule> list = dbHelper.list();
        if(CollectionUtils.isNotEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<QrCodeRule> queryByParam(QrCodeRule param) {
        DBHelper<QrCodeRule> dbHelper = getDbHelper();
        dbHelper.add(StringUtil.isNotEmpty(param.getTableCode()),Expression.eq("tableCode",param.getTableCode()));
        dbHelper.add(StringUtil.isNotEmpty(param.getAttrCode()),Expression.eq("attrCode",param.getAttrCode()));
        return dbHelper.list();
    }
}
