package cn.flydiy.web.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.web.entity.MenuLang;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Repository
public class MenuLangRepositoryImpl extends BaseRepositoryImpl<MenuLang> implements MenuLangRepository {
    @Override
    public List<Map> queryByGroupId(String groupId) {
        DBHelper<MenuLang> dbHelper = getDbHelper();
        return dbHelper.add(Expression.eq("menuGroupId",groupId)).listMap();
    }

    @Override
    public List<MenuLang> queryByMenuIdAndCurrentTenantId(String menuId) {
        DBHelper<MenuLang> dbHelper = getDbHelper();

        dbHelper.add(Expression.eq("menuId", menuId));
        dbHelper.add(Expression.eq("tenantId", WebUtils.getLoginUserTenantId()));

        return dbHelper.list();
    }

    @Override
    public void deleteByMenuIdAndTenantId(String menuId) {
        DBHelper<MenuLang> dbHelper = getDbHelper();

        dbHelper.add(Expression.eq("menuId", menuId));
        dbHelper.add(Expression.eq("tenantId", WebUtils.getLoginUserTenantId()));

        dbHelper.delete();
    }

    @Override
    public List<Map> queryAllByTenantId() {
        DBHelper<MenuLang> dbHelper = getDbHelper();

        dbHelper.add(Expression.eq("tenantId", WebUtils.getLoginUserTenantId()));

        return dbHelper.listMap();
    }

}
