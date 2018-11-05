package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.BannerConfigService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.BannerCreateDto;

/**
* banner新增
* Modify by v_wyaohu(胡王耀) on 2018-6-4 21:11:41.
*/
public interface BannerCreateService {

    void saveDto(BannerCreateDto dto);

    void commit(BannerCreateDto dto);

    void batchCommit(java.util.List<BannerCreateDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(BannerCreateDto dto);

    void updateDtoNoSaveHistory(BannerCreateDto dto);

    void deleteById(String id);


    }
