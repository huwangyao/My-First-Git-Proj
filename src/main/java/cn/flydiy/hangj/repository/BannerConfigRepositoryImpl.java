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
* banner配置信息
* Modify by v_wyaohu(胡王耀) on 2018-6-4 21:11:39.
*/

@org.springframework.stereotype.Repository
public class BannerConfigRepositoryImpl extends cn.flydiy.core.repository.BaseRepositoryImpl<cn.flydiy.hangj.entity.BannerConfig>  implements BannerConfigRepository{

    @Override
    public List<Map> queryMapByIds(String... ids){
        DBHelper<cn.flydiy.hangj.entity.BannerConfig> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",ids));
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.BannerConfig> queryByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig){
        DBHelper<cn.flydiy.hangj.entity.BannerConfig> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, bannerConfig);
        return dbHelper.list();
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig){
        DBHelper<cn.flydiy.hangj.entity.BannerConfig> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, bannerConfig);
        return dbHelper.listMap();
    }

    @Override
    public List<cn.flydiy.hangj.entity.BannerConfig> queryPageByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig){
        DBHelper<cn.flydiy.hangj.entity.BannerConfig> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, bannerConfig);
        return dbHelper.listPage();
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig){
        DBHelper<cn.flydiy.hangj.entity.BannerConfig> dbHelper = getDbHelper();
        this.addExpressions(dbHelper, bannerConfig);
        return dbHelper.listPageMap();
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.BannerConfig updateParam) {
        DBHelper<cn.flydiy.hangj.entity.BannerConfig> dbHelper = getDbHelper();
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

    private void addExpressions(DBHelper db, cn.flydiy.hangj.entity.BannerConfig bannerConfig){
        if(bannerConfig == null){
            bannerConfig = new cn.flydiy.hangj.entity.BannerConfig();
        }
            db.addIfNotNull(Expression.eq("id",bannerConfig.getId()));
            db.addIfNotNull(Expression.eq("name",bannerConfig.getName()));
            db.addIfNotNull(Expression.eq("pic",bannerConfig.getPic()));
            db.addIfNotNull(Expression.eq("no",bannerConfig.getNo()));
            db.addIfNotNull(Expression.eq("url",bannerConfig.getUrl()));
            db.addIfNotNull(Expression.eq("version",bannerConfig.getVersion()));
            db.addIfNotNull(Expression.eq("createTime",bannerConfig.getCreateTime()));
            db.addIfNotNull(Expression.eq("creator",bannerConfig.getCreator()));
            db.addIfNotNull(Expression.eq("creatorVer",bannerConfig.getCreatorVer()));
            db.addIfNotNull(Expression.eq("modiTime",bannerConfig.getModiTime()));
            db.addIfNotNull(Expression.eq("modiUser",bannerConfig.getModiUser()));
            db.addIfNotNull(Expression.eq("modiUserVer",bannerConfig.getModiUserVer()));
            db.addIfNotNull(Expression.eq("orgId",bannerConfig.getOrgId()));
            db.addIfNotNull(Expression.eq("orgIdVer",bannerConfig.getOrgIdVer()));
            db.addIfNotNull(Expression.eq("corpId",bannerConfig.getCorpId()));
            db.addIfNotNull(Expression.eq("corpIdVer",bannerConfig.getCorpIdVer()));
            db.addIfNotNull(Expression.eq("tenantId",bannerConfig.getTenantId()));
            db.addIfNotNull(Expression.eq("tenantIdVer",bannerConfig.getTenantIdVer()));
            db.addIfNotNull(Expression.eq("isNew",bannerConfig.getIsNew()));
            db.addIfNotNull(Expression.eq("flowRoleId",bannerConfig.getFlowRoleId()));
            db.addIfNotNull(Expression.eq("flowRoleIdVer",bannerConfig.getFlowRoleIdVer()));
            db.addIfNotNull(Expression.eq("flowStatus",bannerConfig.getFlowStatus()));
    
        if(bannerConfig.getVersion()!=null){
            db.setQueryHistory();
        }
        db.addOrder(Order.asc("no"));
    }




    @Override
    public void setIsNewToZero(String... id) {
        DBHelper<cn.flydiy.hangj.entity.BannerConfig> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("id",id));
        dbHelper.setUpdateColumn("isNew",0);
        dbHelper.update();
    }

}
