package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.BannerConfigService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.ConfigUpdateDto;

/**
* banner编辑
* Modify by v_wyaohu(胡王耀) on 2018-5-31 16:28:36.
*/
public interface ConfigUpdateService {

    void saveDto(ConfigUpdateDto dto);

    void commit(ConfigUpdateDto dto);

    void batchCommit(java.util.List<ConfigUpdateDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(ConfigUpdateDto dto);

    void updateDtoNoSaveHistory(ConfigUpdateDto dto);

    void deleteById(String id);


    }
