package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.TopicManagerDto;

/**
* 话题管理
* Modify by v_sunlli on 2018-8-23 11:22:43.
*/
public interface TopicManagerService {

    void saveDto(TopicManagerDto dto);

    void commit(TopicManagerDto dto);

    void batchCommit(java.util.List<TopicManagerDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(TopicManagerDto dto);

    void updateDtoNoSaveHistory(TopicManagerDto dto);

    void deleteById(String id);


        //
        List<Map> queryHangjTypeForHangj_topicManager(cn.flydiy.hangj.entity.Type type);
//
List<Map> queryHangjInfo2ForHangj_topicManager(Map param);
        //TopicManager的手工代码
            void updateTopicSort(Map param);
        //
        List<Map> dataTableForHangj_topicManager(Map param);
        //
        List<Map> queryHangjType3ForHangj_topicManager(cn.flydiy.hangj.entity.Type type);
//
List<Map> queryHangjInfo1ForHangj_topicManager(Map param);
    }
