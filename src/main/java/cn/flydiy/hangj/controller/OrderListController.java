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

import cn.flydiy.hangj.service.OrderListService;
import cn.flydiy.hangj.dto.OrderListDto;

/**
* 预约列表控制器
* Modify by v_wyaohu on 2018-8-30 14:50:02.
*/
@cn.flydiy.core.annotation.WebController
public class OrderListController extends BaseController {

    @Autowired
    private OrderListService orderListService;
    @Autowired
    private OrderService orderService;

    //保存
    public void saveEntity() {
    OrderListDto orderListDto = getParamObj(OrderListDto.class);
    orderListService.saveDto(orderListDto);
    super.render(new ResponseData(orderListDto));
    }

    //提交
    public void commitEntity() {
    OrderListDto orderListDto = getParamObj(OrderListDto.class);
    orderListService.commit(orderListDto);
    super.render(new ResponseData(orderListDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<OrderListDto> orderListDtos = BeanUtil.convertMapsToBeans(datas, OrderListDto.class);
    orderListService.batchCommit(orderListDtos);
        super.render(new ResponseData(orderListDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  orderListService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    OrderListDto orderListDto = getParamObj(OrderListDto.class);
    orderListService.updateDto(orderListDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            OrderListDto orderListDto = getParamObj(OrderListDto.class);
            orderListService.updateDtoNoSaveHistory(orderListDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    orderListService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }


    //   
    public void dataTableForHangj_orderList() {
        Map param = getParamMap();
        List<Map> result = orderListService.dataTableForHangj_orderList(param);
        super.render(new ResponseData(result));
    }
            //OrderList的手工代码
            public void exportOrderListOrderNew() throws java.io.IOException {
    Map param = getParamMap();
    List<Map> result = orderListService.exportOrderListOrder(param);

     List<Map> data = new java.util.ArrayList();
    for(Map map : result){
        Map obj = new java.util.LinkedHashMap();
        obj.put("咨询话题名称",map.get("title"));
        obj.put("咨询者名称",map.get("orderName"));
        obj.put("行家名称",map.get("expertName"));
        obj.put("提交预约时间",map.get("createTime"));
        obj.put("咨询者问题描述",map.get("problemDesc"));
        obj.put("咨询者自我介绍",map.get("selfIntroduction"));
        obj.put("状态",map.get("status"));
        data.add(obj);
    }
    java.io.ByteArrayOutputStream output =  cn.flydiy.metadata.util.MetadataExcelUtil.exportTableDataExcel(data);

    cn.flydiy.core.web.WebUtils.renderDownload(output.toByteArray(),"hangj_order.xlsx");
}

// 查询咨询者的预约信息
    public void queryConsultantOrder(){
        Map param = getParamMap();
        List<Map> result = orderListService.queryConsultantOrder(param);

        super.render(new ResponseData(result));
    }

        // 查询行家的预约信息
    public void queryExpertOrder(){
        Map param = getParamMap();
        List<Map> result = orderListService.queryExpertOrder(param);

        super.render(new ResponseData(result));
    }
public void exportOrderListOrder() throws java.io.IOException {
    Map param = getParamMap();
    List<Map> result = orderListService.exportOrderListOrder(param);

    java.io.ByteArrayOutputStream output =  cn.flydiy.metadata.util.MetadataExcelUtil.exportTableDataExcel("hangj_order",result,"createTime,orderName,title,status,expertName,problemDesc,selfIntroduction");

    cn.flydiy.core.web.WebUtils.renderDownload(output.toByteArray(),"hangj_order.xlsx");
}

}
