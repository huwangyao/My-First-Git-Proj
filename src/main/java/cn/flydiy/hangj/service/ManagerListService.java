package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.InfoService;
import cn.flydiy.hangj.service.TopicService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.ManagerListDto;

/**
* 行家列表
* Modify by v_wyaohu(胡王耀) on 2018-6-14 19:34:35.
*/
public interface ManagerListService {

    void saveDto(ManagerListDto dto);

    void commit(ManagerListDto dto);

    void batchCommit(java.util.List<ManagerListDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(ManagerListDto dto);

    void updateDtoNoSaveHistory(ManagerListDto dto);

    void deleteById(String id);


        //
        List<Map> dataTableForHangj_managerList(Map param);
        //ManagerList的手工代码
            Map queryStaffInfo(Map param);

    List<Map> queryTopicForHomePage(Map param);

    Map queryExpertWithTopic(Map param);

     void getResourcesByExcelTopic(List<Map> maps,List<Map> mapsT);

      Map getExpertTopicCount(Map param);
    }
