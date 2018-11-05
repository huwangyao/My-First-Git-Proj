package cn.flydiy.hangj.repository;

import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.DateUtil;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.MatchMode;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.common.db.helper.SqlHelper;
import cn.flydiy.core.common.db.helper.sqltype.GENERALSQL;
import cn.flydiy.core.common.db.helper.sqltype.MYSQL;
import cn.flydiy.core.common.db.helper.sqltype.ORACLE;
import cn.flydiy.core.common.db.helper.sqltype.SQLSERVER;
import cn.flydiy.core.common.db.helper.storedprocedure.StoredProcedureHelper;
import cn.flydiy.core.common.db.helper.storedprocedure.StoredProcedureResultHelper;
import cn.flydiy.core.common.db.projection.Projection;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.StringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
* 行家信息
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/

@org.springframework.stereotype.Repository
public class InfoRepositoryImpl extends cn.flydiy.core.repository.BaseRepositoryImpl<cn.flydiy.hangj.entity.Info>  implements InfoRepository{

    @Override
    public List<Map> queryMapByIds(String... ids){
        DBHelper<cn.flydiy.hangj.entity.Info> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",ids));
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.Info> queryByParams(cn.flydiy.hangj.entity.Info info){
        DBHelper<cn.flydiy.hangj.entity.Info> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, info);
        return dbHelper.list();
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.Info info){
        DBHelper<cn.flydiy.hangj.entity.Info> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, info);
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.Info> queryPageByParams(cn.flydiy.hangj.entity.Info info){
        DBHelper<cn.flydiy.hangj.entity.Info> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, info);
        return dbHelper.listPage();
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Info info){
        DBHelper<cn.flydiy.hangj.entity.Info> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, info);
        return dbHelper.listPageMap();
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.Info updateParam) {
        DBHelper<cn.flydiy.hangj.entity.Info> dbHelper = getDbHelper();
        String id = updateParam.getId();
        if(StringUtil.isEmpty(id)){
            throw new BaseRunTimeException("id not found!!!");
        }
        Map beanMap = new HashMap();
        BeanUtil.copyPropertiesToMap(beanMap,updateParam);
        Set<Map.Entry> entrySet = beanMap.entrySet();
        for (Map.Entry entry : entrySet) {
            String key = (String) entry.getKey();
            if(!"id".equals(key)){
                dbHelper.setUpdateColumn(key,entry.getValue());
            }
        }
        dbHelper.add(Expression.eq("id",id));
        dbHelper.update();
    }

    private void addExpressions(DBHelper db, cn.flydiy.hangj.entity.Info info){
        if(info == null){
            info = new cn.flydiy.hangj.entity.Info();
        }
            db.addIfNotNull(Expression.eq("id",info.getId()));
            db.addIfNotNull(Expression.eq("name",info.getName()));
            db.addIfNotNull(Expression.eq("orgStr",info.getOrgStr()));
            db.addIfNotNull(Expression.eq("position",info.getPosition()));
            db.addIfNotNull(Expression.eq("entryDate",info.getEntryDate()));
            db.addIfNotNull(Expression.eq("title",info.getTitle()));
            db.addIfNotNull(Expression.eq("career",info.getCareer()));
            db.addIfNotNull(Expression.eq("projectExp",info.getProjectExp()));
            db.addIfNotNull(Expression.eq("awards",info.getAwards()));
            db.addIfNotNull(Expression.eq("topicNo",info.getTopicNo()));
            db.addIfNotNull(Expression.eq("status",info.getStatus()));
            db.addIfNotNull(Expression.eq("step",info.getStep()));
            db.addIfNotNull(Expression.eq("address",info.getAddress()));
            db.addIfNotNull(Expression.eq("userId",info.getUserId()));
            db.addIfNotNull(Expression.eq("pic",info.getPic()));
            db.addIfNotNull(Expression.eq("tag",info.getTag()));
            db.addIfNotNull(Expression.eq("newPic",info.getNewPic()));
            db.addIfNotNull(Expression.eq("version",info.getVersion()));
            db.addIfNotNull(Expression.eq("createTime",info.getCreateTime()));
            db.addIfNotNull(Expression.eq("creator",info.getCreator()));
            db.addIfNotNull(Expression.eq("creatorVer",info.getCreatorVer()));
            db.addIfNotNull(Expression.eq("modiTime",info.getModiTime()));
            db.addIfNotNull(Expression.eq("modiUser",info.getModiUser()));
            db.addIfNotNull(Expression.eq("modiUserVer",info.getModiUserVer()));
            db.addIfNotNull(Expression.eq("orgId",info.getOrgId()));
            db.addIfNotNull(Expression.eq("orgIdVer",info.getOrgIdVer()));
            db.addIfNotNull(Expression.eq("corpId",info.getCorpId()));
            db.addIfNotNull(Expression.eq("corpIdVer",info.getCorpIdVer()));
            db.addIfNotNull(Expression.eq("tenantId",info.getTenantId()));
            db.addIfNotNull(Expression.eq("tenantIdVer",info.getTenantIdVer()));
            db.addIfNotNull(Expression.eq("isNew",info.getIsNew()));
            db.addIfNotNull(Expression.eq("flowRoleId",info.getFlowRoleId()));
            db.addIfNotNull(Expression.eq("flowRoleIdVer",info.getFlowRoleIdVer()));
            db.addIfNotNull(Expression.eq("flowStatus",info.getFlowStatus()));
    
        if(info.getVersion()!=null){
            db.setQueryHistory();
        }
        db.addOrder(Order.desc("createTime"));
    }




    @Override
    public void setIsNewToZero(String... id) {
        DBHelper<cn.flydiy.hangj.entity.Info> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",id));
        dbHelper.setUpdateColumn("isNew",0);
        dbHelper.update();
    }

public List<Map> queryHangjInfo2ForHangj_topicManager(Map param){
    DBHelper<cn.flydiy.hangj.entity.Info> dbHelper = getDbHelper();
    cn.flydiy.hangj.entity.Info info = new cn.flydiy.hangj.entity.Info();
    cn.flydiy.common.util.BeanUtil.copyPropertiesToBean(info,param);
    addExpressions(dbHelper,info);

    String _param = MapUtils.getString(param,"_param");
    if(StringUtil.isNotEmpty(_param)){
        dbHelper.add(Expression.or(
            
            Expression.like("name",_param,MatchMode.ANYWHERE)
        ));
    }




    dbHelper.addOrder(Order.desc("createTime"));
    return dbHelper.listPageMap();
}
    public List<Map> dataTableForHangj_managerList(Map param){
        DBHelper<cn.flydiy.hangj.entity.Info> dbHelper = getDbHelper();
        String name = MapUtils.getString(param,"name");
        dbHelper.add(StringUtil.isNotEmpty(name),Expression.like("name",name,MatchMode.ANYWHERE));
        String status = MapUtils.getString(param,"status");
        dbHelper.add(StringUtil.isNotEmpty(status),Expression.eq("status",status));
        
        

        dbHelper.addOrder(Order.desc("createTime"));

        return dbHelper.listPageMap();
    }
public List<Map> queryHangjInfo1ForHangj_topicManager(Map param){
    DBHelper<cn.flydiy.hangj.entity.Info> dbHelper = getDbHelper();
    cn.flydiy.hangj.entity.Info info = new cn.flydiy.hangj.entity.Info();
    cn.flydiy.common.util.BeanUtil.copyPropertiesToBean(info,param);
    addExpressions(dbHelper,info);

    String _param = MapUtils.getString(param,"_param");
    if(StringUtil.isNotEmpty(_param)){
        dbHelper.add(Expression.or(
            
            Expression.like("name",_param,MatchMode.ANYWHERE)
        ));
    }




    dbHelper.addOrder(Order.desc("createTime"));
    return dbHelper.listPageMap();
}
}
