package cn.flydiy.web.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.web.entity.MenuGroup;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Repository
public class MenuGroupRepositoryImpl extends BaseRepositoryImpl<MenuGroup> implements MenuGroupRepository {
    @Override
    public List<Map> queryByParentId(String id) {
        DBHelper<MenuGroup> dbHelper = getDbHelper();
        return dbHelper.add(Expression.eq("parentId",id)).addOrder(Order.asc("sort")).listMap();
    }

    @Override
    public List<MenuGroup> findAllSort() {
        DBHelper<MenuGroup> dbHelper = getDbHelper();
        return dbHelper.addOrder(Order.asc("sort")).list();
    }

    @Override
    public List<MenuGroup> queryRootByTenantId(String parentId, String tenantId) {
        DBHelper<MenuGroup> dbHelper = getDbHelper();

        dbHelper.add(Expression.eq("parentId", parentId)).addOrder(Order.asc("sort"));

        // 添加租户id条件
        dbHelper.addTenantCondition(tenantId);

        return dbHelper.list();
    }
}
