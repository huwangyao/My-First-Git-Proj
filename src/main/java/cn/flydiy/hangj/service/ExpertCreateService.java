package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.InfoService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.ExpertCreateDto;

/**
* 新建行家
* Modify by v_wyaohu(胡王耀) on 2018-6-14 11:58:36.
*/
public interface ExpertCreateService {

    void saveDto(ExpertCreateDto dto);

    void commit(ExpertCreateDto dto);

    void batchCommit(java.util.List<ExpertCreateDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(ExpertCreateDto dto);

    void updateDtoNoSaveHistory(ExpertCreateDto dto);

    void deleteById(String id);


        //ExpertCreate的手工代码
            void saveExpert(ExpertCreateDto expertCreateDto);
    }
