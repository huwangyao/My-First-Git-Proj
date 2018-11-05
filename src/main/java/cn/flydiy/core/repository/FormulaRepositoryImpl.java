package cn.flydiy.core.repository;

import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.entity.Formula;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by player on 2017/10/26.
 */
@Repository
public class FormulaRepositoryImpl extends BaseRepositoryImpl<Formula> implements FormulaRepository{
    @Override
    public List<Formula> queryByTableCode(String tableCode) {
        DBHelper<Formula> dbHelper = getDbHelper();
        dbHelper.add(StringUtil.isNotEmpty(tableCode),Expression.eq("tableCode",tableCode));
        dbHelper.addOrder(Order.asc("orderNum"));
        return dbHelper.list();
    }
}
