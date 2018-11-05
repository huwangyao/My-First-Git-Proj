package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.TypeService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.TypeManagerDto;

/**
* 分类管理
* Modify by v_wyaohu(胡王耀) on 2018-6-20 12:00:42.
*/
public interface TypeManagerService {

    void saveDto(TypeManagerDto dto);

    void commit(TypeManagerDto dto);

    void batchCommit(java.util.List<TypeManagerDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(TypeManagerDto dto);

    void updateDtoNoSaveHistory(TypeManagerDto dto);

    void deleteById(String id);


        //
        List<Map> dataTableForHangj_typeManager(Map param);
    }
