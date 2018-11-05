package cn.flydiy.serial.service;

import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.serial.entity.QrCodeRule;
import cn.flydiy.serial.repository.QrCodeRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by player on 2017/9/7.
 */
@Service
public class QrCodeRuleServiceImpl extends BaseServiceImpl<QrCodeRule> implements QrCodeRuleService {

    @Autowired
    private QrCodeRuleRepository qrCodeRuleRepository;

    @Override
    public QrCodeRule queryByAttrCode(String tableCode, String attrCode) {
        return qrCodeRuleRepository.queryByAttrCode(tableCode,attrCode);
    }

    @Override
    public List<QrCodeRule> queryByParam(QrCodeRule param) {
        return qrCodeRuleRepository.queryByParam(param);
    }

    @Override
    public void saveOrUpdateEntity(QrCodeRule entity) {
        QrCodeRule qrCodeRule = queryByAttrCode(entity.getTableCode(), entity.getAttrCode());
        if(qrCodeRule != null && !qrCodeRule.getId().equals(entity.getId())){
            throw new BaseRunTimeException("该二维码规则已经被设置");
        }
        if(StringUtil.isEmpty(entity.getId())){
            save(entity);
        }else {
            _updateEntityById(entity);
        }
    }
}
