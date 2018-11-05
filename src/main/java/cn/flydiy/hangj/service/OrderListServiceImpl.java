
package cn.flydiy.hangj.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.DateUtil;//预约列表的手工导入的代码;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.web.WebUtils;//预约列表的手工导入的代码;
import cn.flydiy.hangj.service.OrderService;
import cn.flydiy.serial.util.SerialNumberUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.dto.OrderListDto;

/**
* 预约列表
* Modify by v_wyaohu on 2018-8-30 14:50:04.
*/
@org.springframework.stereotype.Service
public class OrderListServiceImpl implements OrderListService {

    @Autowired
    private InfoService infoService;//预约列表的手工注入的代码;
    @Autowired
    private OrderService orderService;



    @Override
    public void saveDto(OrderListDto dto) {
        cn.flydiy.hangj.entity.Order order = dto.getOrder();
        
        orderService.saveEntity(order);
    }

    @Override
    public void commit(OrderListDto dto) {
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
    public void updateDto(OrderListDto dto) {
        cn.flydiy.hangj.entity.Order order = dto.getOrder();

        
        orderService.updateEntity(order);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Order.class);
        if(_isMasterData){
            dto.setOrder(order);
            return ;
        }

    }


    @Override
    public void updateDtoNoSaveHistory(OrderListDto dto) {
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
    public void batchCommit(List<OrderListDto> dtos) {
        if(CollectionUtils.isNotEmpty(dtos)){
            for (OrderListDto dto : dtos) {
                commit(dto);
            }
        }
    }



public List<Map> exportOrderListOrder(Map param){
    List<Map> result = orderService.exportOrderListOrder(param);

    return result;
}

    public List<Map> dataTableForHangj_orderList(Map param){
        List<Map> result = orderService.dataTableForHangj_orderList(param);
        return result;
    }

        //OrderList的手工代码
        @Override
    public List<Map> queryConsultantOrder(Map param) {
        cn.flydiy.hangj.entity.Order orderParam = new cn.flydiy.hangj.entity.Order();
        orderParam.setOrderName(WebUtils.getLoginUser().getRealName());
        orderParam.setOrderNameId(WebUtils.getLoginUser().getId());
        // 整理搜索条件的状态
        String statusCode = (String)param.get("statusCode");
        List<String> statusList = this.getStatusByParam(statusCode);
        List<Map> result = orderService.queryOrderByConsultantIdOrConsultantName(orderParam,statusList);

        // 查询行家的信息
        cn.flydiy.hangj.entity.Info infoParam = new cn.flydiy.hangj.entity.Info();
        List<cn.flydiy.hangj.entity.Info> expertList = infoService.queryByParams(infoParam);
        for(Map map:result){
            String expertName = (String)map.get("expertName");    // 行家姓名
            String expertId = (String)map.get("expertId");     // 行家id
            for(cn.flydiy.hangj.entity.Info info : expertList){
                String id = info.getId();    // 行家id
                String name = info.getName();     // 行家名称
                String orgStr = info.getOrgStr();
                String address = info.getAddress();
                // 整理一下account
                String account = "";
                int end = name.indexOf("(");
                if(end>-1){
                    account = name.substring(0,end);
                }else{
                    account = name;
                }
                if(StringUtil.equalsIgnoreCase(id,expertId) || StringUtil.equalsIgnoreCase(name,expertName)){
                    map.put("orgStr",orgStr);
                    map.put("address",address);
                    map.put("account",account);
                     // 处理时间
                    String makeOrderDate = DateUtil.toDateString((java.util.Date)map.get("createTime"));
                    if(StringUtil.isNotEmpty(makeOrderDate)){
                        makeOrderDate = makeOrderDate.substring(0,10);
                        map.put("makeOrderDate",makeOrderDate);
                    }
                }
            }
        }
        return result;
    }

        @Override
    public List<Map> queryExpertOrder(Map param) {
        cn.flydiy.hangj.entity.Order orderParam = new cn.flydiy.hangj.entity.Order();
        orderParam.setExpertName(WebUtils.getLoginUser().getRealName());
        orderParam.setExpertId(WebUtils.getLoginUser().getId());
        // 整理搜索条件的状态
        String statusCode = (String)param.get("statusCode");
        List<String> statusList = new ArrayList();
        if(StringUtil.equalsIgnoreCase(statusCode,"待处理")){
            statusList.add("处理中");
        }else if(StringUtil.equalsIgnoreCase(statusCode,"待确认时间地点")){
            statusList.add("成功");
            statusList.add("已沟通待反馈");
        }else if(StringUtil.equalsIgnoreCase(statusCode,"待咨询")){
            statusList.add("已发日历");
        }else if(StringUtil.equalsIgnoreCase(statusCode,"已完成咨询")){
            statusList.add("已实施");
        }else if(StringUtil.equalsIgnoreCase(statusCode,"已收到学员反馈")){
            statusList.add("已评价");
        }
        List<Map> result = orderService.queryExpertOrder(orderParam,statusList);

        // TODO 扩展咨询者的信息 地址/组织/account
        for(Map map:result){
            String orderName = (String)map.get("orderName");
            // 整理一下account
            String account = "";
            int end = orderName.indexOf("(");
            if(end>-1){
                account = orderName.substring(0,end);
            }else{
                account = orderName;
            }
            map.put("account",account);
             // 处理时间
            String makeOrderDate = DateUtil.toDateString((java.util.Date)map.get("createTime"));
            if(StringUtil.isNotEmpty(makeOrderDate)){
                makeOrderDate = makeOrderDate.substring(0,10);
                map.put("makeOrderDate",makeOrderDate);
            }
        }

        return result;
    }

    public List<String> getStatusByParam(String code){
        List<String> status = new ArrayList();
        if(StringUtil.equalsIgnoreCase(code,"待处理")){
            status.add("处理中");
        }else if(StringUtil.equalsIgnoreCase(code,"进行中")){
            status.add("成功");
            status.add("已沟通待反馈");
            status.add("已发日历");
        }else if(StringUtil.equalsIgnoreCase(code,"已完成")){
            status.add("已评价");
            status.add("已实施");
        }else if(StringUtil.equalsIgnoreCase(code,"已取消")){
            status.add("失败");
        }
        return status;
}
}


