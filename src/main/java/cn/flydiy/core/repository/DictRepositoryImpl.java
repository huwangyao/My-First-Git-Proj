package cn.flydiy.core.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.MatchMode;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.entity.Dict;
import cn.flydiy.common.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
@Repository
public class DictRepositoryImpl extends BaseRepositoryImpl<Dict> implements DictRepository {
    @Override
    public List<Dict> queryEnumTerm(String title) {
        DBHelper<Dict> dbHelper = getDbHelper();
        if (StringUtil.isNotEmpty(title)) {
            dbHelper.add(Expression.or(Expression.like("name", title, MatchMode.ANYWHERE), Expression.like("code", title, MatchMode.ANYWHERE)));
        }
        dbHelper.addOrder(Order.desc("createTime"));

        return dbHelper.listPage();
    }

    @Override
    public List<Dict> queryEnumByCode(String... code) {
        DBHelper<Dict> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("code",code));

        return dbHelper.list();
    }

    @Override
    public void deleteByCodes(String... codes) {
        DBHelper<Dict> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("code",codes));

        dbHelper.delete();
    }
}
