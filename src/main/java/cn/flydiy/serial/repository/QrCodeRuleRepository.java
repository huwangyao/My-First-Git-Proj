package cn.flydiy.serial.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.serial.entity.QrCodeRule;

import java.util.List;

/**
 * Created by player on 2017/9/7.
 */
public interface QrCodeRuleRepository extends BaseRepository<QrCodeRule>{
    QrCodeRule queryByAttrCode(String tableCode, String attrCode);

    List<QrCodeRule> queryByParam(QrCodeRule param);
}
