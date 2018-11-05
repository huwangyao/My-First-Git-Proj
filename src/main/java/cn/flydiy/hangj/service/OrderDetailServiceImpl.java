
package cn.flydiy.hangj.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.hangj.service.OrderService;
import cn.flydiy.serial.util.SerialNumberUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.dto.OrderDetailDto;

/**
* 预约信息
* Modify by v_sunlli on 2018-8-23 11:24:28.
*/
@org.springframework.stereotype.Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderService orderService;



    @Override
    public void saveDto(OrderDetailDto dto) {
        cn.flydiy.hangj.entity.Order order = dto.getOrder();
        
        orderService.saveEntity(order);
    }

    @Override
    public void commit(OrderDetailDto dto) {
        cn.flydiy.hangj.entity.Order order = dto.getOrder();
        if(cn.flydiy.common.util.StringUtil.isEmpty(order.getId())){
            
            saveDto(dto);
        }else{
            
            updateDto(dto);
        }
    }


    @Override
    public Map queryById(String id) {
        Map result = new HashMap();
        Map map = new HashMap();
        cn.flydiy.hangj.entity.Order order = orderService.findOne(id);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Order.class);
        BeanUtil.copyPropertiesToMap(map,order);
        result.put("order",map);
        return result;
    }


    @Override
    public List<Map> queryByIds(String... ids) {
        List<Map> result = new ArrayList<>();
        List<cn.flydiy.hangj.entity.Order> orders = orderService.findByIds(ids);

        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Order.class);

        for (cn.flydiy.hangj.entity.Order order : orders) {
            Map temp = new HashMap();
            BeanUtil.copyPropertiesToMap(temp,order);
            result.add(temp);
        }
        return result;
    }


    @Override
    public void updateDto(OrderDetailDto dto) {
        cn.flydiy.hangj.entity.Order order = dto.getOrder();

        
        orderService.updateEntity(order);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Order.class);
        if(_isMasterData){
            dto.setOrder(order);
            return ;
        }

    }


    @Override
    public void updateDtoNoSaveHistory(OrderDetailDto dto) {
        cn.flydiy.hangj.entity.Order order = dto.getOrder();

        
        orderService.updateEntityNoSaveHistory(order);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Order.class);

    }


    @Override
    public void deleteById(String id) {
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Order.class);
        cn.flydiy.hangj.entity.Order order = orderService.findOne(id);
        orderService.delete(id);
    }

    @Override
    public void batchCommit(List<OrderDetailDto> dtos) {
        if(CollectionUtils.isNotEmpty(dtos)){
            for (OrderDetailDto dto : dtos) {
                commit(dto);
            }
        }
    }



}


