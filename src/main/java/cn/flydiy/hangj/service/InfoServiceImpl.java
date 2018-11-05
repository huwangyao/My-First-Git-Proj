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
import cn.flydiy.hangj.repository.InfoRepository;


/**
* 行家信息
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/
@org.springframework.stereotype.Service
public class InfoServiceImpl extends cn.flydiy.core.service.BaseServiceImpl<cn.flydiy.hangj.entity.Info>   implements InfoService {

    @Autowired
    private InfoRepository infoRepository;

    @Override
    public List<Map> queryMapByIds(String... ids){
        return infoRepository.queryMapByIds(ids);
    }

    @Override
    public List<cn.flydiy.hangj.entity.Info> queryByParams(cn.flydiy.hangj.entity.Info info){
        return infoRepository.queryByParams(info);
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.Info info){
        return infoRepository.queryMapByParams(info);
    }

    @Override
    public List<cn.flydiy.hangj.entity.Info> queryPageByParams(cn.flydiy.hangj.entity.Info info){
        return infoRepository.queryPageByParams(info);
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Info info){
        return infoRepository.queryPageMapByParams(info);
    }

    @Override
    public void saveEntity(cn.flydiy.hangj.entity.Info... info) {
        checkInfo(info);
        for(cn.flydiy.hangj.entity.Info entity:info){
        }
        save(info);
        for(cn.flydiy.hangj.entity.Info entity:info){
        }
    }

    @Override
    public void updateEntity(cn.flydiy.hangj.entity.Info... info) {
        checkInfo(info);
        update(info);
    }

    @Override
    public void updateEntityNoSaveHistory(cn.flydiy.hangj.entity.Info... info) {
        checkInfo(info);
        _updateAndNoSaveHistory(info);
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.Info updateParam) {
        infoRepository.updateByParam(updateParam);
    }

    private void checkInfo(@NotNull cn.flydiy.hangj.entity.Info... info){
        if(info.length==1){
        }else if(info.length>1){
        }
    }



    public void setIsNewToZero(String... id) {
        infoRepository.setIsNewToZero(id);
    }

public List<Map> queryHangjInfo1ForHangj_topicManager(Map param){
    return infoRepository.queryHangjInfo1ForHangj_topicManager(param);
}
    public List<Map> dataTableForHangj_managerList(Map param){
        return infoRepository.dataTableForHangj_managerList(param);
    }
public List<Map> queryHangjInfo2ForHangj_topicManager(Map param){
    return infoRepository.queryHangjInfo2ForHangj_topicManager(param);
}
}
