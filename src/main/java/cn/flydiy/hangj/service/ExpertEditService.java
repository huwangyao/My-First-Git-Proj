package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.ExpertEditDto;

/**
* 行家编辑
* Modify by v_wyaohu(胡王耀) on 2018-6-14 14:52:40.
*/
public interface ExpertEditService {

    void saveDto(ExpertEditDto dto);

    void commit(ExpertEditDto dto);

    void batchCommit(java.util.List<ExpertEditDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(ExpertEditDto dto);

    void updateDtoNoSaveHistory(ExpertEditDto dto);

    void deleteById(String id);


        //
        List<Map> queryHangjTypeForHangj_expertEdit(cn.flydiy.hangj.entity.Type type);
        //
        List<Map> dataTableForHangj_expertEdit(cn.flydiy.hangj.entity.Topic topic);
    }
