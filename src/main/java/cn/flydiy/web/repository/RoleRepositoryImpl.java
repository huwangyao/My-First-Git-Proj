package cn.flydiy.web.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.web.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
@Repository
public class RoleRepositoryImpl extends BaseRepositoryImpl<Role> implements RoleRepository {
    @Override
    public List<Role> queryAllRole(String corpId) {

        DBHelper<Role> dbHelper = getDbHelper();
        List<Role> roles = dbHelper.add(Expression.eq("corpId", corpId)).list();

        return roles;
    }
}
