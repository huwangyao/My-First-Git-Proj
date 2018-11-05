package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.ExpertTopicDto;

/**
* 添加话题
* Modify by v_wyaohu(胡王耀) on 2018-6-12 21:22:24.
*/
public interface ExpertTopicService {

    void saveDto(ExpertTopicDto dto);

    void commit(ExpertTopicDto dto);

    void batchCommit(java.util.List<ExpertTopicDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(ExpertTopicDto dto);

    void updateDtoNoSaveHistory(ExpertTopicDto dto);

    void deleteById(String id);


    }
