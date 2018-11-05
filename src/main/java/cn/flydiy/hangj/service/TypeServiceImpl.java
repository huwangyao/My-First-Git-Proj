package cn.flydiy.hangj.service;

import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.service.BaseServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.collections4.MapUtils;
import cn.flydiy.hangj.repository.TypeRepository;


/**
* 分类管理
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/
@org.springframework.stereotype.Service
public class TypeServiceImpl extends cn.flydiy.core.service.BaseServiceImpl<cn.flydiy.hangj.entity.Type>   implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Map> queryMapByIds(String... ids){
        return typeRepository.queryMapByIds(ids);
    }

    @Override
    public List<cn.flydiy.hangj.entity.Type> queryByParams(cn.flydiy.hangj.entity.Type type){
        return typeRepository.queryByParams(type);
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.Type type){
        return typeRepository.queryMapByParams(type);
    }

    @Override
    public List<cn.flydiy.hangj.entity.Type> queryPageByParams(cn.flydiy.hangj.entity.Type type){
        return typeRepository.queryPageByParams(type);
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Type type){
        return typeRepository.queryPageMapByParams(type);
    }

    @Override
    public void saveEntity(cn.flydiy.hangj.entity.Type... type) {
        checkType(type);
        for(cn.flydiy.hangj.entity.Type entity:type){
        }
        save(type);
        for(cn.flydiy.hangj.entity.Type entity:type){
        }
    }

    @Override
    public void updateEntity(cn.flydiy.hangj.entity.Type... type) {
        checkType(type);
        update(type);
    }

    @Override
    public void updateEntityNoSaveHistory(cn.flydiy.hangj.entity.Type... type) {
        checkType(type);
        _updateAndNoSaveHistory(type);
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.Type updateParam) {
        typeRepository.updateByParam(updateParam);
    }

    private void checkType(@NotNull cn.flydiy.hangj.entity.Type... type){
        if(type.length==1){
        }else if(type.length>1){
        }
    }



    public void setIsNewToZero(String... id) {
        typeRepository.setIsNewToZero(id);
    }

    public List<Map> dataTableForHangj_typeManager(Map param){
        return typeRepository.dataTableForHangj_typeManager(param);
    }
}
