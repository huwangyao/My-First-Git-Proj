package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.NoticeService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.NoticeListDto;

/**
* 通知中心代码(勿删)
* Modify by v_wyaohu on 2018-8-31 17:11:02.
*/
public interface NoticeListService {

    void saveDto(NoticeListDto dto);

    void commit(NoticeListDto dto);

    void batchCommit(java.util.List<NoticeListDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(NoticeListDto dto);

    void updateDtoNoSaveHistory(NoticeListDto dto);

    void deleteById(String id);


        //NoticeList的手工代码
        void sendEmail(String email,String title,StringBuffer html);

void userCancelOrder(Map map);

void orderDateCommit(Map map);

    void acceptOrder(Map param);

    void refuseOrder(Map param);

    void acceptMoreOrder(Map param);

    void refuseMoreOrder(Map param);

    List<Map> queryMyNotice(Map param);

    void orderDateUpdate(Map map);

    void expertCancelOrder(Map param);
        //
        List<Map> dataTableForHangj_noticeList(cn.flydiy.hangj.entity.Notice notice);
    }
