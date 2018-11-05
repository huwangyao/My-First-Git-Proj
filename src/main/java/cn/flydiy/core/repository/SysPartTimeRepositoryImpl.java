package cn.flydiy.core.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.Order;

import java.util.List;
import java.util.Map;


/**
* 工作兼任
* Created by 梁宇湘 on 2017-11-15 10:07:37.
*/
@org.springframework.stereotype.Repository
public class SysPartTimeRepositoryImpl extends BaseRepositoryImpl<cn.flydiy.core.entity.SysPartTime> implements SysPartTimeRepository{

    @Override
    public List<Map> queryMapByIds(String... ids){
        DBHelper<cn.flydiy.core.entity.SysPartTime> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",ids));
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.core.entity.SysPartTime> queryByParams(cn.flydiy.core.entity.SysPartTime sysPartTime){
        DBHelper<cn.flydiy.core.entity.SysPartTime> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, sysPartTime);
        return dbHelper.list();
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.core.entity.SysPartTime sysPartTime){
        DBHelper<cn.flydiy.core.entity.SysPartTime> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, sysPartTime);
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.core.entity.SysPartTime> queryPageByParams(cn.flydiy.core.entity.SysPartTime sysPartTime){
        DBHelper<cn.flydiy.core.entity.SysPartTime> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, sysPartTime);
        return dbHelper.listPage();
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.core.entity.SysPartTime sysPartTime){
        DBHelper<cn.flydiy.core.entity.SysPartTime> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, sysPartTime);
        return dbHelper.listPageMap();
    }

    private void addExpressions(DBHelper db, cn.flydiy.core.entity.SysPartTime sysPartTime){
        if(sysPartTime == null){
            sysPartTime = new cn.flydiy.core.entity.SysPartTime();
        }
            db.addIfNotNull(Expression.eq("userId",sysPartTime.getUserId()));
            db.addIfNotNull(Expression.eq("id",sysPartTime.getId()));
            db.addIfNotNull(Expression.eq("userIdVer",sysPartTime.getUserIdVer()));
            db.addIfNotNull(Expression.eq("jobsId",sysPartTime.getJobsId()));
            db.addIfNotNull(Expression.eq("jobsIdVer",sysPartTime.getJobsIdVer()));
            db.addIfNotNull(Expression.eq("version",sysPartTime.getVersion()));
            db.addIfNotNull(Expression.eq("createTime",sysPartTime.getCreateTime()));
            db.addIfNotNull(Expression.eq("creator",sysPartTime.getCreator()));
            db.addIfNotNull(Expression.eq("creatorVer",sysPartTime.getCreatorVer()));
            db.addIfNotNull(Expression.eq("modiTime",sysPartTime.getModiTime()));
            db.addIfNotNull(Expression.eq("modiUser",sysPartTime.getModiUser()));
            db.addIfNotNull(Expression.eq("modiUserVer",sysPartTime.getModiUserVer()));
            db.addIfNotNull(Expression.eq("orgId",sysPartTime.getOrgId()));
            db.addIfNotNull(Expression.eq("orgIdVer",sysPartTime.getOrgIdVer()));
            db.addIfNotNull(Expression.eq("corpId",sysPartTime.getCorpId()));
            db.addIfNotNull(Expression.eq("corpIdVer",sysPartTime.getCorpIdVer()));
            db.addIfNotNull(Expression.eq("tenantId",sysPartTime.getTenantId()));
            db.addIfNotNull(Expression.eq("tenantIdVer",sysPartTime.getTenantIdVer()));
            db.addIfNotNull(Expression.eq("isNew",sysPartTime.getIsNew()));
            db.addIfNotNull(Expression.eq("flowRoleId",sysPartTime.getFlowRoleId()));
            db.addIfNotNull(Expression.eq("flowRoleIdVer",sysPartTime.getFlowRoleIdVer()));
            db.addIfNotNull(Expression.eq("flowStatus",sysPartTime.getFlowStatus()));
    
        if(sysPartTime.getVersion()!=null){
            db.setQueryHistory();
        }

        db.addOrder(Order.desc("createTime"));

    }





}
