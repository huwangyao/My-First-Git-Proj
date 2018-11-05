package cn.flydiy.serial.repository;

import cn.flydiy.serial.entity.SerialRule;
import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.repository.BaseRepositoryImpl;

import java.util.List;


/**
* 单号生成规则
* Created by flydiy on 2017-04-10 15:10:49.
*/
@org.springframework.stereotype.Repository
public class SerialRuleRepositoryImpl extends BaseRepositoryImpl<cn.flydiy.serial.entity.SerialRule> implements SerialRuleRepository{

    @Override
    public SerialRule queryByTableAndCol(String table, String col) {
        DBHelper<SerialRule> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("tableName",table));
        dbHelper.add(Expression.eq("colName",col));
        List<SerialRule> rules = dbHelper.list();
        return !rules.isEmpty()?rules.get(0):null;
    }

    @Override
    public List<SerialRule> queryByTableCodes(String... codes) {
        DBHelper<SerialRule> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("tableName",codes));
        return dbHelper.list();
    }

    @Override
    public void deleteByTableName(String... tableName) {
        DBHelper<SerialRule> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("tableName",tableName));
        dbHelper.delete();
    }
}
