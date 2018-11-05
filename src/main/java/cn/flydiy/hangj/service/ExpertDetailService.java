package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.ExpertDetailDto;

/**
* 详情
* Modify by v_wyaohu on 2018-8-21 16:14:44.
*/
public interface ExpertDetailService {

    void saveDto(ExpertDetailDto dto);

    void commit(ExpertDetailDto dto);

    void batchCommit(java.util.List<ExpertDetailDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(ExpertDetailDto dto);

    void updateDtoNoSaveHistory(ExpertDetailDto dto);

    void deleteById(String id);


    }
