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
* 分类管理
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/

@org.springframework.stereotype.Repository
public class TypeRepositoryImpl extends cn.flydiy.core.repository.BaseRepositoryImpl<cn.flydiy.hangj.entity.Type>  implements TypeRepository{

    @Override
    public List<Map> queryMapByIds(String... ids){
        DBHelper<cn.flydiy.hangj.entity.Type> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",ids));
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.Type> queryByParams(cn.flydiy.hangj.entity.Type type){
        DBHelper<cn.flydiy.hangj.entity.Type> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, type);
        return dbHelper.list();
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.Type type){
        DBHelper<cn.flydiy.hangj.entity.Type> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, type);
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.Type> queryPageByParams(cn.flydiy.hangj.entity.Type type){
        DBHelper<cn.flydiy.hangj.entity.Type> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, type);
        return dbHelper.listPage();
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Type type){
        DBHelper<cn.flydiy.hangj.entity.Type> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, type);
        return dbHelper.listPageMap();
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.Type updateParam) {
        DBHelper<cn.flydiy.hangj.entity.Type> dbHelper = getDbHelper();
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

    private void addExpressions(DBHelper db, cn.flydiy.hangj.entity.Type type){
        if(type == null){
            type = new cn.flydiy.hangj.entity.Type();
        }
            db.addIfNotNull(Expression.eq("id",type.getId()));
            db.addIfNotNull(Expression.eq("name",type.getName()));
            db.addIfNotNull(Expression.eq("no",type.getNo()));
            db.addIfNotNull(Expression.eq("version",type.getVersion()));
            db.addIfNotNull(Expression.eq("createTime",type.getCreateTime()));
            db.addIfNotNull(Expression.eq("creator",type.getCreator()));
            db.addIfNotNull(Expression.eq("creatorVer",type.getCreatorVer()));
            db.addIfNotNull(Expression.eq("modiTime",type.getModiTime()));
            db.addIfNotNull(Expression.eq("modiUser",type.getModiUser()));
            db.addIfNotNull(Expression.eq("modiUserVer",type.getModiUserVer()));
            db.addIfNotNull(Expression.eq("orgId",type.getOrgId()));
            db.addIfNotNull(Expression.eq("orgIdVer",type.getOrgIdVer()));
            db.addIfNotNull(Expression.eq("corpId",type.getCorpId()));
            db.addIfNotNull(Expression.eq("corpIdVer",type.getCorpIdVer()));
            db.addIfNotNull(Expression.eq("tenantId",type.getTenantId()));
            db.addIfNotNull(Expression.eq("tenantIdVer",type.getTenantIdVer()));
            db.addIfNotNull(Expression.eq("isNew",type.getIsNew()));
            db.addIfNotNull(Expression.eq("flowRoleId",type.getFlowRoleId()));
            db.addIfNotNull(Expression.eq("flowRoleIdVer",type.getFlowRoleIdVer()));
            db.addIfNotNull(Expression.eq("flowStatus",type.getFlowStatus()));
    
        if(type.getVersion()!=null){
            db.setQueryHistory();
        }
        db.addOrder(Order.asc("no"));
    }




    @Override
    public void setIsNewToZero(String... id) {
        DBHelper<cn.flydiy.hangj.entity.Type> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",id));
        dbHelper.setUpdateColumn("isNew",0);
        dbHelper.update();
    }

    public List<Map> dataTableForHangj_typeManager(Map param){
        DBHelper<cn.flydiy.hangj.entity.Type> dbHelper = getDbHelper();
        String name = MapUtils.getString(param,"name");
        dbHelper.add(StringUtil.isNotEmpty(name),Expression.like("name",name,MatchMode.ANYWHERE));
        
        

        dbHelper.addOrder(Order.asc("no"));

        return dbHelper.listPageMap();
    }
}
