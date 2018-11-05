package cn.flydiy.hangj.service;

import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.service.BaseServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.collections4.MapUtils;
import cn.flydiy.hangj.repository.OrderRepository;


/**
* 行家预约单
* Modify by v_wyaohu on 2018-8-31 17:45:35.
*/
@org.springframework.stereotype.Service
public class OrderServiceImpl extends cn.flydiy.core.service.BaseServiceImpl<cn.flydiy.hangj.entity.Order>   implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Map> queryMapByIds(String... ids){
        return orderRepository.queryMapByIds(ids);
    }

    @Override
    public List<cn.flydiy.hangj.entity.Order> queryByParams(cn.flydiy.hangj.entity.Order order){
        return orderRepository.queryByParams(order);
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.Order order){
        return orderRepository.queryMapByParams(order);
    }

    @Override
    public List<cn.flydiy.hangj.entity.Order> queryPageByParams(cn.flydiy.hangj.entity.Order order){
        return orderRepository.queryPageByParams(order);
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Order order){
        return orderRepository.queryPageMapByParams(order);
    }

    @Override
    public void saveEntity(cn.flydiy.hangj.entity.Order... order) {
        checkOrder(order);
        for(cn.flydiy.hangj.entity.Order entity:order){
        }
        save(order);
        for(cn.flydiy.hangj.entity.Order entity:order){
        }
    }

    @Override
    public void updateEntity(cn.flydiy.hangj.entity.Order... order) {
        checkOrder(order);
        update(order);
    }

    @Override
    public void updateEntityNoSaveHistory(cn.flydiy.hangj.entity.Order... order) {
        checkOrder(order);
        _updateAndNoSaveHistory(order);
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.Order updateParam) {
        orderRepository.updateByParam(updateParam);
    }

    private void checkOrder(@NotNull cn.flydiy.hangj.entity.Order... order){
        if(order.length==1){
        }else if(order.length>1){
        }
    }



    public void setIsNewToZero(String... id) {
        orderRepository.setIsNewToZero(id);
    }

public List<Map> exportOrderListOrder(Map param){
    return orderRepository.exportOrderListOrder(param);
}
    public List<Map> dataTableForHangj_orderList(Map param){
        return orderRepository.dataTableForHangj_orderList(param);
    }
        //HangjiaJob的手工代码
            @Override
    public List<Map> getOrderCountByExpertName(Map param) {
        return orderRepository.getOrderCountByExpertName(param);
    }

    @Override
    public List<Map> queryOrderByConsultantIdOrConsultantName(cn.flydiy.hangj.entity.Order orderParam,List<String> status) {
        return orderRepository.queryOrderByConsultantIdOrConsultantName(orderParam,status);
    }

    @Override
    public List<Map> queryExpertOrder(cn.flydiy.hangj.entity.Order orderParam,List<String> status) {
        return orderRepository.queryExpertOrder(orderParam,status);
    }
}
