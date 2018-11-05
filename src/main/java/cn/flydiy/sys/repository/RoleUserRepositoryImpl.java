package cn.flydiy.sys.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.sys.entity.RoleUser;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
@Repository
public class RoleUserRepositoryImpl extends BaseRepositoryImpl<RoleUser> implements RoleUserRepository {
    @Override
    public List<RoleUser> findRoleUsersByUserId(String userId) {
        DBHelper<RoleUser> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("userId", userId));
        return dbHelper.list();
    }

}
