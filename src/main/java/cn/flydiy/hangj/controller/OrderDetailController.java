package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.hangj.service.OrderService;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.OrderDetailService;
import cn.flydiy.hangj.dto.OrderDetailDto;

/**
* 预约信息控制器
* Modify by v_sunlli on 2018-8-23 11:24:25.
*/
@cn.flydiy.core.annotation.WebController
public class OrderDetailController extends BaseController {

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderService orderService;

    //保存
    public void saveEntity() {
    OrderDetailDto orderDetailDto = getParamObj(OrderDetailDto.class);
    orderDetailService.saveDto(orderDetailDto);
    super.render(new ResponseData(orderDetailDto));
    }

    //提交
    public void commitEntity() {
    OrderDetailDto orderDetailDto = getParamObj(OrderDetailDto.class);
    orderDetailService.commit(orderDetailDto);
    super.render(new ResponseData(orderDetailDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<OrderDetailDto> orderDetailDtos = BeanUtil.convertMapsToBeans(datas, OrderDetailDto.class);
    orderDetailService.batchCommit(orderDetailDtos);
        super.render(new ResponseData(orderDetailDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  orderDetailService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    OrderDetailDto orderDetailDto = getParamObj(OrderDetailDto.class);
    orderDetailService.updateDto(orderDetailDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            OrderDetailDto orderDetailDto = getParamObj(OrderDetailDto.class);
            orderDetailService.updateDtoNoSaveHistory(orderDetailDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    orderDetailService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }



}
