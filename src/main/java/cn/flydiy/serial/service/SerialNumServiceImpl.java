package cn.flydiy.serial.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.entity.Org;
import cn.flydiy.core.service.BaseServiceImpl;

import cn.flydiy.core.service.OrgService;
import cn.flydiy.core.web.WebUtils;
import cn.flydiy.serial.util.SerialNumberUtils;
import cn.flydiy.serial.entity.SerialNum;
import org.springframework.beans.factory.annotation.Autowired;
import cn.flydiy.serial.repository.SerialNumRepository;

import java.util.HashMap;
import java.util.Map;


/**
* 生成单号
* Created by flydiy on 2017-04-10 15:10:43.
*/
@org.springframework.stereotype.Service
public class SerialNumServiceImpl extends BaseServiceImpl<cn.flydiy.serial.entity.SerialNum> implements SerialNumService{

    @Autowired
    private SerialNumRepository serialNumRepository;
    @Autowired
    private OrgService orgService;


    @Override
    public Map queryOrgById(String id) {
        Org org = new Org();
        org = orgService.findOne(id);
        Map orgMap = new HashMap();
        BeanUtil.copyPropertiesToMap(orgMap,org);
        return orgMap;
    }

    //第一次使用一个序列号时查找不到记录，以默认值创建序列号
    @Override
    public void newSerialNum(String table, String col, String type, String orgId, String date) {
        SerialNum serialNum = new SerialNum();
        serialNum.setSerialNumber(SerialNumberUtils.START_NUMBER);
        serialNum.setStep(SerialNumberUtils.STEP);
        serialNum.setType(type);
        serialNum.setTableName(table);
        serialNum.setColName(col);
        serialNum.setOrgId(orgId);
        serialNum.setDateRecord(date);
        serialNumRepository.save(serialNum);

    }

    @Override
    public SerialNum queryByTableAndCol(String table, String col, String type, String orgId) {
        return serialNumRepository.queryByTableAndCol(table, col, type,orgId, WebUtils.getLoginUserCorpId());
    }


    @Override
    public void update(String id, long serialNumber, String date) {
        serialNumRepository.updateSerialAndDate(id, serialNumber, date);
    }


}
