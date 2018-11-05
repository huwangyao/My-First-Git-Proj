package cn.flydiy.sys.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.sys.entity.RoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
@Repository
public class RoleMenuRepositoryImpl extends BaseRepositoryImpl<RoleMenu> implements RoleMenuRepository {


    @Override
    public List<RoleMenu> queryRoleMenuByRoleIds(String... roleIds) {
        DBHelper<RoleMenu> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("roleId", roleIds));
        return dbHelper.list();
    }
}
