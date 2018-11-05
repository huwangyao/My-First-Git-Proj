package cn.flydiy.serial.service;

import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.dto.DBSaveParam;
import cn.flydiy.serial.entity.SerialRule;
import cn.flydiy.core.service.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import cn.flydiy.serial.repository.SerialRuleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
* 单号生成规则
* Created by flydiy on 2017-04-10 15:10:50.
*/
@org.springframework.stereotype.Service
public class SerialRuleServiceImpl extends BaseServiceImpl<cn.flydiy.serial.entity.SerialRule> implements SerialRuleService{

    @Autowired
    private SerialRuleRepository serialRuleRepository;

    @Override
    public SerialRule queryByTableAndCol(String table, String col) {
            return serialRuleRepository.queryByTableAndCol(table,col);

    }

    @Override
    public List<SerialRule> findAll() {
        return serialRuleRepository.findAll();
    }

    public List<SerialRule> queryByTableCodes(String... codes) {
        return serialRuleRepository.queryByTableCodes(codes);
    }

    @Override
    public void importSerialRule(String idSuffix, List<SerialRule> serialRules) {
        if(CollectionUtils.isEmpty(serialRules)){
            return ;
        }
        Set<String> tableNames = new HashSet<>();
        for (SerialRule serialRule : serialRules) {
            tableNames.add(serialRule.getTableName());
            serialRule.setId(StringUtil.subUnderLine(serialRule.getId() + idSuffix));
        }
        serialRuleRepository.deleteByTableName(tableNames.toArray(new String[]{}));

        DBSaveParam dbSaveParam = new DBSaveParam();
        dbSaveParam.setUseCurrCorpId(true);
        dbSaveParam.setUseCurrTenantId(true);
        dbSaveParam.setNoGenerateNewId(true);
        save(dbSaveParam,serialRules.toArray(new SerialRule[]{}));
    }

}



