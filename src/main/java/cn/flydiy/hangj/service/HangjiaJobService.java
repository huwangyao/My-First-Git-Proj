package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.OrderService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.HangjiaJobDto;

/**
* 定时任务代码(勿删)
* Modify by v_wyaohu on 2018-8-31 17:45:37.
*/
public interface HangjiaJobService {

    void saveDto(HangjiaJobDto dto);

    void commit(HangjiaJobDto dto);

    void batchCommit(java.util.List<HangjiaJobDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(HangjiaJobDto dto);

    void updateDtoNoSaveHistory(HangjiaJobDto dto);

    void deleteById(String id);


        //HangjiaJob的手工代码
        void cancelOrderAfterTwoWeek();

void noticeFillInInfo();

    void OneDayAheadNoticeJob();
    }
