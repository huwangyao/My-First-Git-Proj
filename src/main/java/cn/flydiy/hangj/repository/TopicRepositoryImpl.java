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
* 行家话题
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/

@org.springframework.stereotype.Repository
public class TopicRepositoryImpl extends cn.flydiy.core.repository.BaseRepositoryImpl<cn.flydiy.hangj.entity.Topic>  implements TopicRepository{

    @Override
    public List<Map> queryMapByIds(String... ids){
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",ids));
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.Topic> queryByParams(cn.flydiy.hangj.entity.Topic topic){
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, topic);
        return dbHelper.list();
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.Topic topic){
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, topic);
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.Topic> queryPageByParams(cn.flydiy.hangj.entity.Topic topic){
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, topic);
        return dbHelper.listPage();
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Topic topic){
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, topic);
        return dbHelper.listPageMap();
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.Topic updateParam) {
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
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

    private void addExpressions(DBHelper db, cn.flydiy.hangj.entity.Topic topic){
        if(topic == null){
            topic = new cn.flydiy.hangj.entity.Topic();
        }
            db.addIfNotNull(Expression.eq("infoId",topic.getInfoId()));
            db.addIfNotNull(Expression.eq("id",topic.getId()));
            db.addIfNotNull(Expression.eq("infoIdVer",topic.getInfoIdVer()));
            db.addIfNotNull(Expression.eq("name",topic.getName()));
            db.addIfNotNull(Expression.eq("consultTime",topic.getConsultTime()));
            db.addIfNotNull(Expression.eq("topicOutline",topic.getTopicOutline()));
            db.addIfNotNull(Expression.eq("relevantExp",topic.getRelevantExp()));
            db.addIfNotNull(Expression.eq("status",topic.getStatus()));
            db.addIfNotNull(Expression.eq("no",topic.getNo()));
            db.addIfNotNull(Expression.eq("type",topic.getType()));
            db.addIfNotNull(Expression.eq("typeVer",topic.getTypeVer()));
            db.addIfNotNull(Expression.eq("version",topic.getVersion()));
            db.addIfNotNull(Expression.eq("createTime",topic.getCreateTime()));
            db.addIfNotNull(Expression.eq("creator",topic.getCreator()));
            db.addIfNotNull(Expression.eq("creatorVer",topic.getCreatorVer()));
            db.addIfNotNull(Expression.eq("modiTime",topic.getModiTime()));
            db.addIfNotNull(Expression.eq("modiUser",topic.getModiUser()));
            db.addIfNotNull(Expression.eq("modiUserVer",topic.getModiUserVer()));
            db.addIfNotNull(Expression.eq("orgId",topic.getOrgId()));
            db.addIfNotNull(Expression.eq("orgIdVer",topic.getOrgIdVer()));
            db.addIfNotNull(Expression.eq("corpId",topic.getCorpId()));
            db.addIfNotNull(Expression.eq("corpIdVer",topic.getCorpIdVer()));
            db.addIfNotNull(Expression.eq("tenantId",topic.getTenantId()));
            db.addIfNotNull(Expression.eq("tenantIdVer",topic.getTenantIdVer()));
            db.addIfNotNull(Expression.eq("isNew",topic.getIsNew()));
            db.addIfNotNull(Expression.eq("flowRoleId",topic.getFlowRoleId()));
            db.addIfNotNull(Expression.eq("flowRoleIdVer",topic.getFlowRoleIdVer()));
            db.addIfNotNull(Expression.eq("flowStatus",topic.getFlowStatus()));
    
        if(topic.getVersion()!=null){
            db.setQueryHistory();
        }
        db.addOrder(Order.asc("no"));
    }



    @Override
    public List<Map> queryByInfoId(String... infoId){
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("infoId",infoId));
        dbHelper.addOrder(Order.asc("no"));
        return dbHelper.listMap();
    }

    @Override
    public void deleteByInfoId(String... infoId){
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("infoId",infoId));
        dbHelper.delete();
    }

    @Override
    public List<Map> queryByInfoId(Integer version,String... infoId){
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("infoId",infoId));
        if(version!=null){
            dbHelper.add(Expression.eq("infoIdVer",version));
        }
        dbHelper.setQueryHistory();
            dbHelper.addOrder(Order.asc("no"));
        return dbHelper.listMap();
    }

    @Override
    public void deleteByInfoId(Integer version,String... infoId){
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("infoId",infoId));
        if(version!=null){
            dbHelper.add(Expression.eq("infoIdVer",version));
        }
        dbHelper.setQueryHistory();
        dbHelper.delete();
    }

    @Override
    public void setIsNewToZero(String... id) {
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",id));
        dbHelper.setUpdateColumn("isNew",0);
        dbHelper.update();
    }

    public List<Map> dataTableForHangj_topicManager(Map param){
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        String name = MapUtils.getString(param,"name");
        dbHelper.add(StringUtil.isNotEmpty(name),Expression.like("name",name,MatchMode.ANYWHERE));
        String type = MapUtils.getString(param,"type");
        dbHelper.add(StringUtil.isNotEmpty(type),Expression.eq("type",type));
        String status = MapUtils.getString(param,"status");
        dbHelper.add(StringUtil.isNotEmpty(status),Expression.eq("status",status));
        String infoId = MapUtils.getString(param,"infoId");
        dbHelper.add(StringUtil.isNotEmpty(infoId),Expression.eq("infoId",infoId));
        
        

        dbHelper.addOrder(Order.asc("no"));

        return dbHelper.listPageMap();
    }
        //TopicManager的手工代码
        @Override
    public List<Map> queryPageTopicMapByParams(Map param) {
        DBHelper<cn.flydiy.hangj.entity.Topic> dbHelper = getDbHelper();
        List<String> type = (List<String>)param.get("type");
        if(type.size()>0){
            dbHelper.addIfNotNull(Expression.in("type",type));
        }
        dbHelper.addIfNotNull(Expression.eq("status","已审核"));
        dbHelper.addOrder(Order.asc("no"));
        return dbHelper.listPageMap();
    }
}
