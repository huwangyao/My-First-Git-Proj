package cn.flydiy.serial.repository;

import cn.flydiy.serial.entity.SerialRule;
import cn.flydiy.core.repository.BaseRepository;
import java.util.List;
import java.util.Map;


/**
* 单号生成规则
* Created by flydiy on 2017-04-10 15:10:49.
*/
public interface SerialRuleRepository extends BaseRepository<cn.flydiy.serial.entity.SerialRule>{

    SerialRule queryByTableAndCol(String table, String col);

    List<SerialRule> queryByTableCodes(String... codes);

    void deleteByTableName(String... tableName);
}
