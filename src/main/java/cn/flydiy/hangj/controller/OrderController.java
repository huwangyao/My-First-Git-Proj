package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.OrderService;
import cn.flydiy.hangj.entity.Order;

/**
* 行家预约单控制器
* Modify by v_wyaohu on 2018-8-31 17:45:35.
*/
@cn.flydiy.core.annotation.WebController
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    //保存
    public void saveEntity() {
        Order order = getParamObj(Order.class);
        orderService.saveEntity(order);
        super.render(new ResponseData(order));
    }

    //批量保存或者更新
    public void batchSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("order");
        List<Order> orders = BeanUtil.convertMapsToBeans(listMap, Order.class);
        List<Order> saveArr = new java.util.ArrayList<>();
        List<Order> updateArr = new java.util.ArrayList<>();
        for (Order order : orders) {
            if (order.getId() == null) {
            
                saveArr.add(order);
            }else {
                updateArr.add(order);
            }
        }
        orderService.saveEntity(saveArr.toArray(new Order[]{}));
        orderService.updateEntity(updateArr.toArray(new Order[]{}));
        super.render(new ResponseData(orders));
    }

    //批量整个保存
    public void batchWholeSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("order");
        List<Order> orders = BeanUtil.convertMapsToBeans(listMap, Order.class);
        orderService.saveWhole(orders.toArray(new Order[]{}));
        super.render(new ResponseData(orders));
    }

    //根据id查询
    public void queryById() {
        Map map = getParamMap();
        Order result =  orderService.findOne(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
    }

    //更新
    public void updateEntity() {
        Order order = getParamObj(Order.class);
        orderService.updateEntity(order);
        super.render(new ResponseData(order));
    }

    public void saveOrUpdate(){
        Order order = getParamObj(Order.class);
        if (order.getId() == null) {
        
            orderService.save(order);
        }else{
            orderService._updateEntityById(order);
        }
        super.render(new ResponseData(order));
    }

    //根据id删除
    public void deleteById() {
        Map map = getParamMap();
        orderService.delete(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
    }

    //批量删除
    public void batchDelete(){
        Map map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        orderService.delete(ids.toArray(new String[]{}));
        super.render(new ResponseData());
    }

    public void queryByParam(){
        Order condition = getParamObj(Order.class);
        List<Order> result = orderService.queryByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryPageByParam(){
        Order condition = getParamObj(Order.class);
        List<Order> result = orderService.queryPageByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryByIds(){
        Map<String, Object> map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        List<Order> result = orderService.findByIds(ids.toArray(new String[]{}));
        super.render(new ResponseData(result));
    }

    public void updateByParam(){
        Order updateParam = getParamObj(Order.class);
        orderService.updateByParam(updateParam);
        super.render(new ResponseData());
    }


}
