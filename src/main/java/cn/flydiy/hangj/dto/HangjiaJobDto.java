package cn.flydiy.hangj.dto;

import cn.flydiy.core.dto.BaseDto;
import cn.flydiy.hangj.service.OrderService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 定时任务代码(勿删)数据转换参数
* Modify by v_wyaohu on 2018-8-31 17:45:37.
*/
public class HangjiaJobDto extends BaseDto {


    private cn.flydiy.hangj.entity.Order order = new cn.flydiy.hangj.entity.Order();



    public cn.flydiy.hangj.entity.Order getOrder() {
        return this.order;
    }
    public void setOrder(cn.flydiy.hangj.entity.Order order) {
        this.order = order;
    }


}
