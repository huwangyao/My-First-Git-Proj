package cn.flydiy.core.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.MatchMode;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.entity.Org;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.other.OrgStatus;
import cn.flydiy.core.web.WebUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by flying on 16-12-2.
 */
@Repository
public class OrgRepositoryImpl extends BaseRepositoryImpl<Org> implements OrgRepository {


    @Override
    public List<Org> queryOrgList(String id) {

        DBHelper<Org> dbHelper = getDbHelper();
        dbHelper.add(Expression.or(Expression.eq("id", id), Expression.eq("parentId", id)));

        dbHelper.addOrder(Order.asc("sort"));
        List<Org> orgList = dbHelper.list();
        return orgList;
    }

    @Override
    public List<Org> countSonOrgByOrgId(String id, String title) {
        DBHelper<Org> dbHelper = getDbHelper();
        if (StringUtil.isNotEmpty(title)) {
            dbHelper.add(Expression.like("name", title, MatchMode.ANYWHERE));
        }
        dbHelper.add(Expression.eq("parentId", id));
        dbHelper.addOrder(Order.asc("sort"));
        return dbHelper.list();
    }

    @Override
    public List<Org> queryAllList() {
        DBHelper<Org> dbHelper = getDbHelper();

        dbHelper.add(Expression.eq("corpId", WebUtils.getLoginUser().getCorpId()));
        dbHelper.addOrder(Order.asc("sort"));
        return dbHelper.list();
    }

    //-------------上面的全要删掉
    @Override
    public List<Org> queryAllOrg(boolean isStatus) {

        return queryAllOrg(isStatus,null);
    }
    @Override
    public List<Org> queryAllOrg(boolean isStatus,String filterType) {
        DBHelper<Org> dbHelper = getDbHelper();

//        dbHelper.add(Expression.eq("corpId", WebUtils.getLoginUser().getCorpId()));
        if(isStatus){
            Expression e1 = Expression.ne("status", OrgStatus.LOCK);
            Expression e2 = Expression.isNull("status");
            dbHelper.add(Expression.or(e1,e2));
        }
        if(StringUtil.isNotEmpty(filterType)){
            dbHelper.add(Expression.eq("type", filterType));

        }
        dbHelper.addOrder(Order.asc("sort"));
        return dbHelper.list();
    }

    @Override
    public List<Org> queryOrgByParams(String param) {
        DBHelper<Org> dbHelper = getDbHelper();

        dbHelper.add(Expression.like("name", param, MatchMode.ANYWHERE));
        dbHelper.add(Expression.eq("corpId", WebUtils.getLoginUser().getCorpId()));
        Expression e1 = Expression.ne("status", OrgStatus.LOCK);
        Expression e2 = Expression.isNull("status");
        dbHelper.add(Expression.or(e1,e2));
        dbHelper.addOrder(Order.asc("sort"));
        return dbHelper.list();
    }
}
