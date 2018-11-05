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
import cn.flydiy.hangj.repository.TopicRepository;


/**
* 行家话题
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/
@org.springframework.stereotype.Service
public class TopicServiceImpl extends cn.flydiy.core.service.BaseServiceImpl<cn.flydiy.hangj.entity.Topic>   implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Map> queryMapByIds(String... ids){
        return topicRepository.queryMapByIds(ids);
    }

    @Override
    public List<cn.flydiy.hangj.entity.Topic> queryByParams(cn.flydiy.hangj.entity.Topic topic){
        return topicRepository.queryByParams(topic);
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.Topic topic){
        return topicRepository.queryMapByParams(topic);
    }

    @Override
    public List<cn.flydiy.hangj.entity.Topic> queryPageByParams(cn.flydiy.hangj.entity.Topic topic){
        return topicRepository.queryPageByParams(topic);
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Topic topic){
        return topicRepository.queryPageMapByParams(topic);
    }

    @Override
    public void saveEntity(cn.flydiy.hangj.entity.Topic... topic) {
        checkTopic(topic);
        for(cn.flydiy.hangj.entity.Topic entity:topic){
        }
        save(topic);
        for(cn.flydiy.hangj.entity.Topic entity:topic){
        }
    }

    @Override
    public void updateEntity(cn.flydiy.hangj.entity.Topic... topic) {
        checkTopic(topic);
        update(topic);
    }

    @Override
    public void updateEntityNoSaveHistory(cn.flydiy.hangj.entity.Topic... topic) {
        checkTopic(topic);
        _updateAndNoSaveHistory(topic);
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.Topic updateParam) {
        topicRepository.updateByParam(updateParam);
    }

    private void checkTopic(@NotNull cn.flydiy.hangj.entity.Topic... topic){
        if(topic.length==1){
        }else if(topic.length>1){
        }
    }


    @Override
    public List<Map> queryByInfoId(String... infoId){
        return topicRepository.queryByInfoId(infoId);
    }

    @Override
    public void deleteByInfoId(String... infoId){
        topicRepository.deleteByInfoId(infoId);
    }

    public List<Map> queryByInfoId(Integer version,String... infoId){
        return topicRepository.queryByInfoId(version,infoId);
    }

    @Override
    public void deleteByInfoId(Integer version,String... infoId){
        topicRepository.deleteByInfoId(version,infoId);
    }

    @Override
    public List<String> updateByInfoId(String mainId, List<cn.flydiy.hangj.entity.Topic> topics) {
         return updateByInfoId(1,mainId,topics);
    }

    @Override
    public List<String> updateByInfoId(Integer version,String mainId, List<cn.flydiy.hangj.entity.Topic> topics) {
        List<Map> topicList = queryByInfoId(version,mainId);
        List<String> topicIds = CollectionUtils.getValueListByKey(topicList, "id",true);

        if(cn.flydiy.common.util.CollectionUtils.isEmpty(topics)){
            deleteByInfoId(mainId);
            return topicIds;
        }

        List<String> deleteIds = new ArrayList<>();
        List<cn.flydiy.hangj.entity.Topic> addList = new ArrayList<>();
        List<cn.flydiy.hangj.entity.Topic> updateList = new ArrayList<>();
        //找到要删除的对象
        for (String topicId : topicIds) {
            boolean flag = true;
            for (cn.flydiy.hangj.entity.Topic topic : topics) {
                if(topicId.equals(topic.getId())){
                    flag = false;
                    break;
                }
            }
            if(flag){
                deleteIds.add(topicId);
            }
        }
        _deleteByIds(deleteIds.toArray(new String[]{}));
        //找到要新增和更新的对象
        for (cn.flydiy.hangj.entity.Topic topic : topics) {
            topic.setInfoId(mainId);
            topic.setInfoIdVer(version);
            if(topic.getId() == null){
                addList.add(topic);
            }else {
                updateList.add(topic);
            }
        }
        saveEntity(addList.toArray(new cn.flydiy.hangj.entity.Topic[]{}));
        if(CollectionUtils.isNotEmpty(updateList)){
            updateEntity(updateList.toArray(new cn.flydiy.hangj.entity.Topic[]{}));
        }
        return deleteIds;
    }

    public void setIsNewToZero(String... id) {
        topicRepository.setIsNewToZero(id);
    }

    public List<Map> dataTableForHangj_topicManager(Map param){
        return topicRepository.dataTableForHangj_topicManager(param);
    }
        //TopicManager的手工代码
        @Override
    public List<Map> queryPageTopicMapByParams(Map param) {
        return topicRepository.queryPageTopicMapByParams(param);
    }
}
