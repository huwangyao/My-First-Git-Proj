package cn.flydiy.web.repository;

import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.web.entity.UserBindInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by player on 2017/10/12.
 */
@Repository
public class UserBindInfoRepositoryImpl extends BaseRepositoryImpl<UserBindInfo> implements UserBindInfoRepository{
    @Override
    public List<UserBindInfo> queryByUserIdAndType(UserBindInfo userBindInfo) {
        DBHelper<UserBindInfo> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("userId",userBindInfo.getUserId()));
        dbHelper.add(Expression.eq("type",userBindInfo.getType()));
        List<UserBindInfo> list = dbHelper.list();
        return list;
    }

    @Override
    public List<UserBindInfo> queryByAccountAndType(UserBindInfo userBindInfo) {
        DBHelper<UserBindInfo> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("account",userBindInfo.getAccount()));
        dbHelper.add(Expression.eq("type",userBindInfo.getType()));
        List<UserBindInfo> list = dbHelper.list();
        return list;
    }

    @Override
    public UserBindInfo queryByBindIdAndType(String bindId, String type) {
        DBHelper<UserBindInfo> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("bindingId",bindId));
        dbHelper.add(Expression.eq("type",type));
        List<UserBindInfo> list = dbHelper.list();
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }
}
