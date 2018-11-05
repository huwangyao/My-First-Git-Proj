package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.ExpertStepThreeDto;

/**
* 步骤三
* Modify by v_wyaohu(胡王耀) on 2018-6-12 21:21:42.
*/
public interface ExpertStepThreeService {

    void saveDto(ExpertStepThreeDto dto);

    void commit(ExpertStepThreeDto dto);

    void batchCommit(java.util.List<ExpertStepThreeDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(ExpertStepThreeDto dto);

    void updateDtoNoSaveHistory(ExpertStepThreeDto dto);

    void deleteById(String id);


    }
