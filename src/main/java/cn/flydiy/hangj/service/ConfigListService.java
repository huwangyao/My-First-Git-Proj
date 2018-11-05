package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.BannerConfigService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.ConfigListDto;

/**
* banner配置列表
* Modify by v_wyaohu(胡王耀) on 2018-5-31 15:00:29.
*/
public interface ConfigListService {

    void saveDto(ConfigListDto dto);

    void commit(ConfigListDto dto);

    void batchCommit(java.util.List<ConfigListDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(ConfigListDto dto);

    void updateDtoNoSaveHistory(ConfigListDto dto);

    void deleteById(String id);


        //
        List<Map> dataTableForHangj_configList(cn.flydiy.hangj.entity.BannerConfig bannerConfig);
    }
