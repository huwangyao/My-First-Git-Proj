
package cn.flydiy.hangj.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.hangj.service.TopicService;
import cn.flydiy.hangj.service.TypeService;
import cn.flydiy.serial.util.SerialNumberUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.dto.HangjTopicDialogDto;

/**
* 行家话题信息弹窗
* Modify by v_sunlli on 2018-8-23 11:19:58.
*/
@org.springframework.stereotype.Service
public class HangjTopicDialogServiceImpl implements HangjTopicDialogService {

    @Autowired
    private TypeService typeService;
    @Autowired
    private TopicService topicService;



    @Override
    public void saveDto(HangjTopicDialogDto dto) {
        cn.flydiy.hangj.entity.Topic topic = dto.getTopic();
        
        topicService.saveEntity(topic);
    }

    @Override
    public void commit(HangjTopicDialogDto dto) {
        cn.flydiy.hangj.entity.Topic topic = dto.getTopic();
        if(cn.flydiy.common.util.StringUtil.isEmpty(topic.getId())){
            
            saveDto(dto);
        }else{
            
            updateDto(dto);
        }
    }


    @Override
    public Map queryById(String id) {
        Map result = new HashMap();
        Map map = new HashMap();
        cn.flydiy.hangj.entity.Topic topic = topicService.findOne(id);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Topic.class);
        BeanUtil.copyPropertiesToMap(map,topic);
        result.put("topic",map);
        return result;
    }


    @Override
    public List<Map> queryByIds(String... ids) {
        List<Map> result = new ArrayList<>();
        List<cn.flydiy.hangj.entity.Topic> topics = topicService.findByIds(ids);

        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Topic.class);

        for (cn.flydiy.hangj.entity.Topic topic : topics) {
            Map temp = new HashMap();
            BeanUtil.copyPropertiesToMap(temp,topic);
            result.add(temp);
        }
        return result;
    }


    @Override
    public void updateDto(HangjTopicDialogDto dto) {
        cn.flydiy.hangj.entity.Topic topic = dto.getTopic();

        
        topicService.updateEntity(topic);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Topic.class);
        if(_isMasterData){
            dto.setTopic(topic);
            return ;
        }

    }


    @Override
    public void updateDtoNoSaveHistory(HangjTopicDialogDto dto) {
        cn.flydiy.hangj.entity.Topic topic = dto.getTopic();

        
        topicService.updateEntityNoSaveHistory(topic);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Topic.class);

    }


    @Override
    public void deleteById(String id) {
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Topic.class);
        cn.flydiy.hangj.entity.Topic topic = topicService.findOne(id);
        topicService.delete(id);
    }

    @Override
    public void batchCommit(List<HangjTopicDialogDto> dtos) {
        if(CollectionUtils.isNotEmpty(dtos)){
            for (HangjTopicDialogDto dto : dtos) {
                commit(dto);
            }
        }
    }



    public List<Map> queryHangjTypeForHangj_hangjTopicDialog(cn.flydiy.hangj.entity.Type type) {
        List<Map> result = typeService.queryPageMapByParams(type);
        return result;
    }
}


