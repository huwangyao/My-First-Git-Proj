package cn.flydiy.hangj.dto;

import cn.flydiy.core.dto.BaseDto;
import cn.flydiy.hangj.service.OrderService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 预约信息数据转换参数
* Modify by v_sunlli on 2018-8-23 11:24:28.
*/
public class OrderDetailDto extends BaseDto {


    private cn.flydiy.hangj.entity.Order order = new cn.flydiy.hangj.entity.Order();



    public cn.flydiy.hangj.entity.Order getOrder() {
        return this.order;
    }
    public void setOrder(cn.flydiy.hangj.entity.Order order) {
        this.order = order;
    }


}
