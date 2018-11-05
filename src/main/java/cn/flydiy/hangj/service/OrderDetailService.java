package cn.flydiy.hangj.service;

import cn.flydiy.hangj.service.OrderService;
import java.util.List;
import java.util.Map;

import cn.flydiy.hangj.dto.OrderDetailDto;

/**
* 预约信息
* Modify by v_sunlli on 2018-8-23 11:24:28.
*/
public interface OrderDetailService {

    void saveDto(OrderDetailDto dto);

    void commit(OrderDetailDto dto);

    void batchCommit(java.util.List<OrderDetailDto> dtos);

    Map queryById(String id);

    List<Map> queryByIds(String... ids);

    void updateDto(OrderDetailDto dto);

    void updateDtoNoSaveHistory(OrderDetailDto dto);

    void deleteById(String id);


    }
