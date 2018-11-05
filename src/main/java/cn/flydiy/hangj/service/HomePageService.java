package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.InfoService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.HomePageDto;

/**
* 首页
* Modify by v_sunlli(李小阳) on 2018-8-14 15:17:25.
*/
public interface HomePageService {

    void saveDto(HomePageDto dto);

    void commit(HomePageDto dto);

    void batchCommit(java.util.List<HomePageDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(HomePageDto dto);

    void updateDtoNoSaveHistory(HomePageDto dto);

    void deleteById(String id);


        //
        List<Map> dataTableForHangj_homePage(cn.flydiy.hangj.entity.Info info);
    }
