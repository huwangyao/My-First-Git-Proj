package cn.flydiy.core.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.MatchMode;
import cn.flydiy.core.constants.LoginStatus;
import cn.flydiy.core.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by flying on 16-12-2.
 */
@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    @Override
    public String getPasswordForUser(String username) {
        return null;
    }

    @Override
    public User findByAccount(String account) {


        DBHelper<User> dbHelper = getDbHelper();
        List<User> users = dbHelper.add(Expression.eq("account", account)).list();

        User user = null;
        if (!users.isEmpty()) {
            user = users.get(0);
        }
        return user;
    }

    @Override
    public List<User> queryUserListByOrgId(String id) {
        DBHelper<User> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("orgId", id));
        return dbHelper.list();
    }

    @Override
    public List<User> queryAllUserByOrgId(String orgId) {
        DBHelper<User> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("orgId", orgId));
        dbHelper.add(Expression.ne("status",LoginStatus.DISABLE));
        return dbHelper.list();
    }

    @Override
    public List<User> queryAllUser() {
        DBHelper<User> dbHelper = getDbHelper();
        dbHelper.add(Expression.ne("status",LoginStatus.DISABLE));
        return dbHelper.list();
    }

    @Override
    public List<User> queryAllUser2() {
        DBHelper<User> dbHelper = getDbHelper();
        return dbHelper.list();
    }

    @Override
    public List queryUserByParams(String param) {
        DBHelper<User> dbHelper = getDbHelper();
        dbHelper.add(Expression.like("realName", param,MatchMode.ANYWHERE));
        dbHelper.add(Expression.ne("status", LoginStatus.DISABLE));
//        dbHelper.add(Expression.eq("corpId", WebUtils.getLoginUser().getCorpId()));
        return dbHelper.list();
    }

}
