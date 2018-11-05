
package cn.flydiy.hangj.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import cn.flydiy.serial.util.SerialNumberUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.dto.ExpertDetailDto;

/**
* 详情
* Modify by v_wyaohu on 2018-8-21 16:14:44.
*/
@org.springframework.stereotype.Service
public class ExpertDetailServiceImpl implements ExpertDetailService {

    @Autowired
    private TopicService topicService;
    @Autowired
    private InfoService infoService;



    @Override
    public void saveDto(ExpertDetailDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();
        
        infoService.saveEntity(info);
        List<cn.flydiy.hangj.entity.Topic> topics = BeanUtil.convertMapsToBeans(dto.getTopic(),cn.flydiy.hangj.entity.Topic.class);
        for(cn.flydiy.hangj.entity.Topic topic: topics){
            topic.setInfoId(info.getId());
            topic.setInfoIdVer(info.getVersion());
        }
        topicService.saveEntity(topics.toArray(new cn.flydiy.hangj.entity.Topic[]{}));
        dto.setTopic(topics);
    }

    @Override
    public void commit(ExpertDetailDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();
        if(cn.flydiy.common.util.StringUtil.isEmpty(info.getId())){
            
            saveDto(dto);
        }else{
            
            updateDto(dto);
        }
    }


    @Override
    public Map queryById(String id) {
        Map result = new HashMap();
        Map map = new HashMap();
        cn.flydiy.hangj.entity.Info info = infoService.findOne(id);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);
        BeanUtil.copyPropertiesToMap(map,info);
        result.put("info",map);
        List<Map> topics = null;
        if(_isMasterData){
            topics = topicService.queryByInfoId(info.getVersion(),info.getId());
        }else {
            topics = topicService.queryByInfoId(info.getId());
        }
        result.put("topic",topics);
        return result;
    }


    @Override
    public List<Map> queryByIds(String... ids) {
        List<Map> result = new ArrayList<>();
        List<cn.flydiy.hangj.entity.Info> infos = infoService.findByIds(ids);

        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);

        for (cn.flydiy.hangj.entity.Info info : infos) {
            Map temp = new HashMap();
            BeanUtil.copyPropertiesToMap(temp,info);
            List<Map> topics = null;
            if(_isMasterData){
                topics = topicService.queryByInfoId(info.getVersion(),info.getId());
            }else {
                topics = topicService.queryByInfoId(info.getId());
            }
            temp.put("topic",topics);
            result.add(temp);
        }
        return result;
    }


    @Override
    public void updateDto(ExpertDetailDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();

        
        infoService.updateEntity(info);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);
        if(_isMasterData){
            dto.setInfo(info);
        List<cn.flydiy.hangj.entity.Topic> topics = BeanUtil.convertMapsToBeans(dto.getTopic(),cn.flydiy.hangj.entity.Topic.class);
        for(cn.flydiy.hangj.entity.Topic topic: topics){
        topic.setInfoId(info.getId());
        topic.setInfoIdVer(info.getVersion());
        }
        topicService.saveEntity(topics.toArray(new cn.flydiy.hangj.entity.Topic[]{}));
            return ;
        }

        List<cn.flydiy.hangj.entity.Topic> topics =dto.getTopic();
        List<String> topicDelIds = null;
        if (_isMasterData){
            topicDelIds = topicService.updateByInfoId(info.getVersion(),info.getId(), topics);
        }else {
            topicDelIds = topicService.updateByInfoId(info.getId(), topics);
        }

    }


    @Override
    public void updateDtoNoSaveHistory(ExpertDetailDto dto) {
        cn.flydiy.hangj.entity.Info info = dto.getInfo();

        
        infoService.updateEntityNoSaveHistory(info);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);

        List<cn.flydiy.hangj.entity.Topic> topics =dto.getTopic();
        List<String> topicDelIds = null;
        if (_isMasterData){
            topicDelIds = topicService.updateByInfoId(info.getVersion(),info.getId(), topics);
        }else {
            topicDelIds = topicService.updateByInfoId(info.getId(), topics);
        }

    }


    @Override
    public void deleteById(String id) {
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Info.class);
        cn.flydiy.hangj.entity.Info info = infoService.findOne(id);
        infoService.delete(id);
        List<Map> topics = null;
        if(_isMasterData){
            topics = topicService.queryByInfoId(info.getVersion(),id);
        }else {
            topics = topicService.queryByInfoId(id);
        }
        String[] topicIds = CollectionUtils.getValuesByKey(topics, "id");
        if(!_isMasterData){
            topicService.delete(topicIds);
        }else {
            topicService.setIsNewToZero(topicIds);
        }
    }

    @Override
    public void batchCommit(List<ExpertDetailDto> dtos) {
        if(CollectionUtils.isNotEmpty(dtos)){
            for (ExpertDetailDto dto : dtos) {
                commit(dto);
            }
        }
    }



}


