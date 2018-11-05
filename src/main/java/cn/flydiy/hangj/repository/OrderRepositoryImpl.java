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
* 行家预约单
* Modify by v_wyaohu on 2018-8-31 17:45:34.
*/

@org.springframework.stereotype.Repository
public class OrderRepositoryImpl extends cn.flydiy.core.repository.BaseRepositoryImpl<cn.flydiy.hangj.entity.Order>  implements OrderRepository{

    @Override
    public List<Map> queryMapByIds(String... ids){
        DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",ids));
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.Order> queryByParams(cn.flydiy.hangj.entity.Order order){
        DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, order);
        return dbHelper.list();
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.Order order){
        DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, order);
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.Order> queryPageByParams(cn.flydiy.hangj.entity.Order order){
        DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, order);
        return dbHelper.listPage();
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Order order){
        DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, order);
        return dbHelper.listPageMap();
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.Order updateParam) {
        DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
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

    private void addExpressions(DBHelper db, cn.flydiy.hangj.entity.Order order){
        if(order == null){
            order = new cn.flydiy.hangj.entity.Order();
        }
            db.addIfNotNull(Expression.eq("orderName",order.getOrderName()));
            db.addIfNotNull(Expression.eq("id",order.getId()));
            db.addIfNotNull(Expression.eq("expertName",order.getExpertName()));
            db.addIfNotNull(Expression.eq("title",order.getTitle()));
            db.addIfNotNull(Expression.eq("selfIntroduction",order.getSelfIntroduction()));
            db.addIfNotNull(Expression.eq("problemDesc",order.getProblemDesc()));
            db.addIfNotNull(Expression.eq("status",order.getStatus()));
            db.addIfNotNull(Expression.eq("meetDate",order.getMeetDate()));
            db.addIfNotNull(Expression.eq("version",order.getVersion()));
            db.addIfNotNull(Expression.eq("topicId",order.getTopicId()));
            db.addIfNotNull(Expression.eq("meetAddress",order.getMeetAddress()));
            db.addIfNotNull(Expression.eq("orderNameId",order.getOrderNameId()));
            db.addIfNotNull(Expression.eq("expertId",order.getExpertId()));
            db.addIfNotNull(Expression.eq("memo",order.getMemo()));
            db.addIfNotNull(Expression.eq("acceptDate",order.getAcceptDate()));
            db.addIfNotNull(Expression.eq("createTime",order.getCreateTime()));
            db.addIfNotNull(Expression.eq("creator",order.getCreator()));
            db.addIfNotNull(Expression.eq("creatorVer",order.getCreatorVer()));
            db.addIfNotNull(Expression.eq("modiTime",order.getModiTime()));
            db.addIfNotNull(Expression.eq("modiUser",order.getModiUser()));
            db.addIfNotNull(Expression.eq("modiUserVer",order.getModiUserVer()));
            db.addIfNotNull(Expression.eq("orgId",order.getOrgId()));
            db.addIfNotNull(Expression.eq("orgIdVer",order.getOrgIdVer()));
            db.addIfNotNull(Expression.eq("corpId",order.getCorpId()));
            db.addIfNotNull(Expression.eq("corpIdVer",order.getCorpIdVer()));
            db.addIfNotNull(Expression.eq("tenantId",order.getTenantId()));
            db.addIfNotNull(Expression.eq("tenantIdVer",order.getTenantIdVer()));
            db.addIfNotNull(Expression.eq("isNew",order.getIsNew()));
            db.addIfNotNull(Expression.eq("flowRoleId",order.getFlowRoleId()));
            db.addIfNotNull(Expression.eq("flowRoleIdVer",order.getFlowRoleIdVer()));
            db.addIfNotNull(Expression.eq("flowStatus",order.getFlowStatus()));
    
        if(order.getVersion()!=null){
            db.setQueryHistory();
        }
        db.addOrder(Order.desc("createTime"));
    }




    @Override
    public void setIsNewToZero(String... id) {
        DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",id));
        dbHelper.setUpdateColumn("isNew",0);
        dbHelper.update();
    }

        //HangjiaJob的手工代码
         @Override
    public List<Map> getOrderCountByExpertName(Map param) {
        DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
        String expertName = MapUtils.getString(param,"expertName");
        dbHelper.add(StringUtil.isNotEmpty(expertName),Expression.like("expertName",expertName,MatchMode.ANYWHERE));
        dbHelper.addIfNotNull(Expression.eq("status","成功"));
        dbHelper.addIfNotNull(Expression.le("meetDate",new java.util.Date()));
        return dbHelper.listMap();
    }

    @Override
    public List<Map> queryOrderByConsultantIdOrConsultantName(cn.flydiy.hangj.entity.Order orderParam , List<String> status) {
        DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
        Expression e1 = Expression.eq("orderName",orderParam.getOrderName());
        Expression e2 = Expression.eq("orderNameId",orderParam.getOrderNameId());
        Expression eOr = Expression.or(e1,e2);
        dbHelper.add(eOr);
        if(status.size()>0){
            dbHelper.add(Expression.in("status",status.toArray(new String[]{})));
        }
        return dbHelper.listMap();
    }

    @Override
    public List<Map> queryExpertOrder(cn.flydiy.hangj.entity.Order orderParam , List<String> status) {
        DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
        Expression e1 = Expression.eq("expertName",orderParam.getExpertName());
        Expression e2 = Expression.eq("expertId",orderParam.getExpertId());
        Expression eOr = Expression.or(e1,e2);
        dbHelper.add(eOr);
        if(status.size()>0){
            dbHelper.add(Expression.in("status",status.toArray(new String[]{})));
        }
        return dbHelper.listMap();
    }
public List<Map> exportOrderListOrder(Map param){
DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
        String status = MapUtils.getString(param,"status");
        dbHelper.add(StringUtil.isNotEmpty(status),Expression.eq("status",status));
        String title = MapUtils.getString(param,"title");
        dbHelper.add(StringUtil.isNotEmpty(title),Expression.like("title",title,MatchMode.ANYWHERE));
        String expertName = MapUtils.getString(param,"expertName");
        dbHelper.add(StringUtil.isNotEmpty(expertName),Expression.like("expertName",expertName,MatchMode.ANYWHERE));
        String orderName = MapUtils.getString(param,"orderName");
        dbHelper.add(StringUtil.isNotEmpty(orderName),Expression.like("orderName",orderName,MatchMode.ANYWHERE));



    dbHelper.addOrder(Order.desc("createTime"));
    return dbHelper.listMap();
}
    public List<Map> dataTableForHangj_orderList(Map param){
        DBHelper<cn.flydiy.hangj.entity.Order> dbHelper = getDbHelper();
        String orderName = MapUtils.getString(param,"orderName");
        dbHelper.add(StringUtil.isNotEmpty(orderName),Expression.like("orderName",orderName,MatchMode.ANYWHERE));
        String expertName = MapUtils.getString(param,"expertName");
        dbHelper.add(StringUtil.isNotEmpty(expertName),Expression.like("expertName",expertName,MatchMode.ANYWHERE));
        String title = MapUtils.getString(param,"title");
        dbHelper.add(StringUtil.isNotEmpty(title),Expression.like("title",title,MatchMode.ANYWHERE));
        String status = MapUtils.getString(param,"status");
        dbHelper.add(StringUtil.isNotEmpty(status),Expression.eq("status",status));
        
        

        dbHelper.addOrder(Order.desc("createTime"));

        return dbHelper.listPageMap();
    }
}
