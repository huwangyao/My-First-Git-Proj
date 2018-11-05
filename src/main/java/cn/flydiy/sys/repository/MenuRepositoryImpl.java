package cn.flydiy.sys.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.sys.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Repository
public class MenuRepositoryImpl extends BaseRepositoryImpl<Menu> implements MenuRepository {
    @Override
    public List<Map> queryByGroupId(String groupId) {
        DBHelper<Menu> dbHelper = getDbHelper();
        return dbHelper.add(Expression.eq("menuGroupId",groupId)).addOrder(Order.asc("sort")).listMap();
    }

}
