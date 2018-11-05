package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.OrderService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.OrderListDto;

/**
* 预约列表
* Modify by v_wyaohu on 2018-8-30 14:50:04.
*/
public interface OrderListService {

    void saveDto(OrderListDto dto);

    void commit(OrderListDto dto);

    void batchCommit(java.util.List<OrderListDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(OrderListDto dto);

    void updateDtoNoSaveHistory(OrderListDto dto);

    void deleteById(String id);


//
List<Map> exportOrderListOrder(Map param);
        //
        List<Map> dataTableForHangj_orderList(Map param);
        //OrderList的手工代码
        List<Map> queryConsultantOrder(Map param);

    List<Map> queryExpertOrder(Map param);
    }
