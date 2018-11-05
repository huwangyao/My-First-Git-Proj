package cn.flydiy.serial.repository;

import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.common.db.expression.Order;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.serial.entity.SerialNum;

import java.util.List;


/**
* 生成单号
* Created by flydiy on 2017-04-12 09:03:28.
*/
@org.springframework.stereotype.Repository
public class SerialNumRepositoryImpl extends BaseRepositoryImpl<SerialNum> implements SerialNumRepository{

    @Override
    public SerialNum queryByTableAndCol(String table, String col, String type, String orgId,String corp) {
        DBHelper<SerialNum> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("tableName",table));
        dbHelper.add(Expression.eq("colName",col));
        dbHelper.add(Expression.eq("type",type));
        dbHelper.add(Expression.eq("corpId", corp));
        if(orgId!=null) {
            dbHelper.add(Expression.eq("orgId", orgId));
        }
        List<SerialNum> serialNums = dbHelper.list();
        if(serialNums.size()>=1) {
            return serialNums.get(0);
        }
        return null;
    }

    @Override
    public void updateSerialAndDate(String id, long serialNumber, String date){
        DBHelper<SerialNum> dbHelper = getDbHelper();
        dbHelper.setUpdateColumn("serialNumber",serialNumber);
        if(!StringUtil.isEmpty(date)) {
            dbHelper.setUpdateColumn("dateRecord", date);
        }
        dbHelper.add(Expression.eq("id",id));
        dbHelper.update();
    }

}
