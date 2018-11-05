package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.ExpertStepOneDto;

/**
* 步骤一
* Modify by v_sunlli on 2018-8-22 14:57:10.
*/
public interface ExpertStepOneService {

    void saveDto(ExpertStepOneDto dto);

    void commit(ExpertStepOneDto dto);

    void batchCommit(java.util.List<ExpertStepOneDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(ExpertStepOneDto dto);

    void updateDtoNoSaveHistory(ExpertStepOneDto dto);

    void deleteById(String id);


    }
