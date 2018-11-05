package cn.flydiy.serial.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.serial.entity.SerialNum;

import java.util.List;
import java.util.Map;


/**
 * 生成单号
 * Created by flydiy on 2017-04-10 15:10:43.
 */
public interface SerialNumService extends BaseService<cn.flydiy.serial.entity.SerialNum> {



    Map queryOrgById(String id);

    //第一次使用一个序列号时查找不到记录，以默认值创建序列号
    void newSerialNum(String table, String col, String type, String orgId, String date);

    /**
     * 查找记录
     *
     * @param table 表名
     * @param col   列名
     * @param type  类型
     * @param orgId 组织id（可选）
     * @return
     */
    SerialNum queryByTableAndCol(String table, String col, String type, String orgId);

    /**
     * 更新序列和时间记录
     */
    void update(String id, long serialNumber, String date);
}
