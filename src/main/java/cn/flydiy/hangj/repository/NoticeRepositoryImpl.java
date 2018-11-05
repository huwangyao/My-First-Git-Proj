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
* 通知记录表
* Modify by v_wyaohu on 2018-8-31 17:10:58.
*/

@org.springframework.stereotype.Repository
public class NoticeRepositoryImpl extends cn.flydiy.core.repository.BaseRepositoryImpl<cn.flydiy.hangj.entity.Notice>  implements NoticeRepository{

    @Override
    public List<Map> queryMapByIds(String... ids){
        DBHelper<cn.flydiy.hangj.entity.Notice> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",ids));
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.Notice> queryByParams(cn.flydiy.hangj.entity.Notice notice){
        DBHelper<cn.flydiy.hangj.entity.Notice> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, notice);
        return dbHelper.list();
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.Notice notice){
        DBHelper<cn.flydiy.hangj.entity.Notice> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, notice);
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.Notice> queryPageByParams(cn.flydiy.hangj.entity.Notice notice){
        DBHelper<cn.flydiy.hangj.entity.Notice> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, notice);
        return dbHelper.listPage();
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Notice notice){
        DBHelper<cn.flydiy.hangj.entity.Notice> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, notice);
        return dbHelper.listPageMap();
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.Notice updateParam) {
        DBHelper<cn.flydiy.hangj.entity.Notice> dbHelper = getDbHelper();
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

    private void addExpressions(DBHelper db, cn.flydiy.hangj.entity.Notice notice){
        if(notice == null){
            notice = new cn.flydiy.hangj.entity.Notice();
        }
            db.addIfNotNull(Expression.eq("recipient",notice.getRecipient()));
            db.addIfNotNull(Expression.eq("sender",notice.getSender()));
            db.addIfNotNull(Expression.eq("title",notice.getTitle()));
            db.addIfNotNull(Expression.eq("topicId",notice.getTopicId()));
            db.addIfNotNull(Expression.eq("orderId",notice.getOrderId()));
            db.addIfNotNull(Expression.eq("isRead",notice.getIsRead()));
            db.addIfNotNull(Expression.eq("type",notice.getType()));
            db.addIfNotNull(Expression.eq("expertName",notice.getExpertName()));
            db.addIfNotNull(Expression.eq("senderName",notice.getSenderName()));
            db.addIfNotNull(Expression.eq("memo",notice.getMemo()));
            db.addIfNotNull(Expression.eq("message",notice.getMessage()));
            db.addIfNotNull(Expression.eq("createTime",notice.getCreateTime()));
            db.addIfNotNull(Expression.eq("creator",notice.getCreator()));
            db.addIfNotNull(Expression.eq("creatorVer",notice.getCreatorVer()));
            db.addIfNotNull(Expression.eq("modiTime",notice.getModiTime()));
            db.addIfNotNull(Expression.eq("modiUser",notice.getModiUser()));
            db.addIfNotNull(Expression.eq("modiUserVer",notice.getModiUserVer()));
            db.addIfNotNull(Expression.eq("orgId",notice.getOrgId()));
            db.addIfNotNull(Expression.eq("orgIdVer",notice.getOrgIdVer()));
            db.addIfNotNull(Expression.eq("corpId",notice.getCorpId()));
            db.addIfNotNull(Expression.eq("corpIdVer",notice.getCorpIdVer()));
            db.addIfNotNull(Expression.eq("tenantId",notice.getTenantId()));
            db.addIfNotNull(Expression.eq("tenantIdVer",notice.getTenantIdVer()));
            db.addIfNotNull(Expression.eq("isNew",notice.getIsNew()));
            db.addIfNotNull(Expression.eq("flowRoleId",notice.getFlowRoleId()));
            db.addIfNotNull(Expression.eq("flowRoleIdVer",notice.getFlowRoleIdVer()));
            db.addIfNotNull(Expression.eq("flowStatus",notice.getFlowStatus()));
    
        if(notice.getVersion()!=null){
            db.setQueryHistory();
        }
    }




    @Override
    public void setIsNewToZero(String... id) {
        DBHelper<cn.flydiy.hangj.entity.Notice> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",id));
        dbHelper.setUpdateColumn("isNew",0);
        dbHelper.update();
    }

}
