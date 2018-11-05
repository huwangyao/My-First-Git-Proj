package cn.flydiy.core.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.MatchMode;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.entity.DictItem;
import cn.flydiy.common.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
@Repository
public class DictItemRepositoryImpl extends BaseRepositoryImpl<DictItem> implements DictItemRepository {
    @Override
    public List<DictItem> queryEnumItemByEnumCode(String code, String title) {
        DBHelper<DictItem> dbHelper = getDbHelper();
        if (StringUtil.isNotEmpty(title)) {
            dbHelper.add(Expression.like("name", title, MatchMode.ANYWHERE));
        }
        dbHelper.add(Expression.eq("code", code));
        dbHelper.addOrder(Order.asc("sort"));

        return dbHelper.list();
    }

    @Override
    public List<DictItem> queryDictItemByItemValueAndDictCode(String value, String code) {
        DBHelper<DictItem> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("value", value));
        dbHelper.add(Expression.eq("code", code));

        return dbHelper.list();
    }

    @Override
    public void deleteEnumItemByEnumCode(String... code) {
        DBHelper<DictItem> dbHelper = getDbHelper();

        dbHelper.add(Expression.in("code", code));

        dbHelper.delete();
    }

    @Override
    public void updateNewCodeByOldCode(String oldCode, String code) {
        DBHelper<DictItem> dbHelper = getDbHelper();
        dbHelper.setUpdateColumn("code",code);
        dbHelper.add(Expression.eq("code", oldCode));

        dbHelper.update();
    }

    @Override
    public List<DictItem> queryAllDictItem() {
        DBHelper<DictItem> dbHelper = getDbHelper();
        dbHelper.addOrder(Order.asc("sort"));

        return dbHelper.list();
    }

    @Override
    public List<DictItem> queryDictItemByCodes(String[] codes) {
        DBHelper<DictItem> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("code", codes));
        dbHelper.addOrder(Order.asc("sort"));
        return dbHelper.list();
    }
}
