package cn.flydiy.web.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.web.entity.MenuFavorite;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Repository
public class MenuFavoriteRepositoryImpl extends BaseRepositoryImpl<MenuFavorite> implements MenuFavoriteRepository {
    @Override
    public List<MenuFavorite> queryByUserId(String userId) {
        DBHelper<MenuFavorite> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("userId",userId));
        dbHelper.addOrder(Order.asc("sort"));
        return dbHelper.list();
    }

    @Override
    public List<Map> queryMapByUserId(String userId) {
        DBHelper<MenuFavorite> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("userId",userId));
        dbHelper.addOrder(Order.asc("sort"));
        return dbHelper.listMap();
    }

    @Override
    public void deleteByUserId(String userId) {
        DBHelper<MenuFavorite> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("userId",userId));
        dbHelper.delete();
    }

    @Override
    public void deleteByMenuId(String menuId) {
        DBHelper<MenuFavorite> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("userId", WebUtils.getLoginUser().getId()));
        dbHelper.add(Expression.eq("menuId", menuId));
        dbHelper.delete();
    }
}
