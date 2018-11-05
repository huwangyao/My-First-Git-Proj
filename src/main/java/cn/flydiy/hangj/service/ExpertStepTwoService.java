package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.ExpertStepTwoDto;

/**
* 步骤二
* Modify by v_wyaohu(胡王耀) on 2018-6-12 21:22:04.
*/
public interface ExpertStepTwoService {

    void saveDto(ExpertStepTwoDto dto);

    void commit(ExpertStepTwoDto dto);

    void batchCommit(java.util.List<ExpertStepTwoDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(ExpertStepTwoDto dto);

    void updateDtoNoSaveHistory(ExpertStepTwoDto dto);

    void deleteById(String id);


    }
