package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.TypeService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.HangjTypeEditDto;

/**
* 编辑类型弹窗
* Modify by v_wyaohu(胡王耀) on 2018-6-14 14:39:13.
*/
public interface HangjTypeEditService {

    void saveDto(HangjTypeEditDto dto);

    void commit(HangjTypeEditDto dto);

    void batchCommit(java.util.List<HangjTypeEditDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(HangjTypeEditDto dto);

    void updateDtoNoSaveHistory(HangjTypeEditDto dto);

    void deleteById(String id);


    }
