package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.TypeService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.HangjTypeCreateDto;

/**
* 新建类型弹窗
* Modify by v_wyaohu(胡王耀) on 2018-6-14 14:39:26.
*/
public interface HangjTypeCreateService {

    void saveDto(HangjTypeCreateDto dto);

    void commit(HangjTypeCreateDto dto);

    void batchCommit(java.util.List<HangjTypeCreateDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(HangjTypeCreateDto dto);

    void updateDtoNoSaveHistory(HangjTypeCreateDto dto);

    void deleteById(String id);


    }
