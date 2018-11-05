package cn.flydiy.web.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.web.entity.RoleUser;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
@Repository
public class RoleUserRepositoryImpl extends BaseRepositoryImpl<RoleUser> implements RoleUserRepository {

    @Override
    public List<RoleUser> findUserIdByRoleId(String... roleIds) {

        if (roleIds == null || roleIds.length == 0) {
            return Collections.emptyList();
        }
        DBHelper<RoleUser> dbHelper = getDbHelper();
        if (roleIds.length == 1) {

            dbHelper.add(Expression.eq("roleId", roleIds[0]));
        } else {
            dbHelper.add(Expression.in("roleId", roleIds));
        }
        List<RoleUser> list = dbHelper.list();
        return list;

    }

    @Override
    public void deleteRoleUserByRoleId(String roleId) {
        DBHelper<RoleUser> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("roleId", roleId));
        dbHelper.delete();
    }

    @Override
    public List<RoleUser> findPageUserIdByRoleId(String... roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return Collections.emptyList();
        }
        DBHelper<RoleUser> dbHelper = getDbHelper();
        if (roleIds.length == 1) {

            dbHelper.add(Expression.eq("roleId", roleIds[0]));
        } else {
            dbHelper.add(Expression.in("roleId", roleIds));
        }
        List<RoleUser> list = dbHelper.listPage();
        return list;
    }
}
