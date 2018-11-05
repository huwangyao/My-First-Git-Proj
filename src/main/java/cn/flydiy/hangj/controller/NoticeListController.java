package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.hangj.service.NoticeService;
import cn.flydiy.hangj.service.OrderService;//通知中心代码(勿删)的手工导入的代码;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.NoticeListService;
import cn.flydiy.hangj.dto.NoticeListDto;

/**
* 通知中心代码(勿删)控制器
* Modify by v_wyaohu on 2018-8-31 17:10:58.
*/
@cn.flydiy.core.annotation.WebController
public class NoticeListController extends BaseController {

    @Autowired
    private NoticeListService noticeListService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private OrderService orderService;//通知中心代码(勿删)的手工注入的代码;

    //保存
    public void saveEntity() {
    NoticeListDto noticeListDto = getParamObj(NoticeListDto.class);
    noticeListService.saveDto(noticeListDto);
    super.render(new ResponseData(noticeListDto));
    }

    //提交
    public void commitEntity() {
    NoticeListDto noticeListDto = getParamObj(NoticeListDto.class);
    noticeListService.commit(noticeListDto);
    super.render(new ResponseData(noticeListDto));
    }

    public void batchCommit(){
    Map<String, Object> paramMap = getParamMap();
    List datas = (List) paramMap.get("datas");
    List<NoticeListDto> noticeListDtos = BeanUtil.convertMapsToBeans(datas, NoticeListDto.class);
    noticeListService.batchCommit(noticeListDtos);
        super.render(new ResponseData(noticeListDtos));
        }

        //根据id查询
        public void queryById() {
        Map map = getParamMap();
        Map result =  noticeListService.queryById(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
        }

        //更新
        public void updateEntity() {
    NoticeListDto noticeListDto = getParamObj(NoticeListDto.class);
    noticeListService.updateDto(noticeListDto);
        super.render(new ResponseData());
        }

        //更新
        public void updateDtoNoSaveHistory() {
            NoticeListDto noticeListDto = getParamObj(NoticeListDto.class);
            noticeListService.updateDtoNoSaveHistory(noticeListDto);
            super.render(new ResponseData());
        }

        //根据id删除
        public void deleteById() {
        Map map = getParamMap();
    noticeListService.deleteById(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
        }


            //NoticeList的手工代码
            public void saveOrder(){
        cn.flydiy.hangj.entity.Order order = getParamObj(cn.flydiy.hangj.entity.Order.class);
        orderService.saveEntity(order);
        // 整理信息
        String recipient = order.getExpertId();     // 接收人id——行家
        String sender = order.getOrderNameId();     // 发送人id——咨询者
        String title = order.getTitle();     // 话题名称
        String topicId = order.getTopicId();     // 话题id
        String orderId = order.getId();     // 预约id
        int isRead = 0;     // 是否已读
        String type = "101";     // 通知类型
        String expertName = order.getExpertName();     // 行家名称
        String orderName = order.getOrderName();     // 咨询者名称
         String memo = "话题预约";
        
        cn.flydiy.hangj.entity.Notice notice = new cn.flydiy.hangj.entity.Notice();
        // 保存通知的信息
        notice.setExpertName(expertName);
        notice.setIsRead(isRead);
        notice.setOrderId(orderId);
        notice.setRecipient(recipient);
        notice.setSender(sender);
        notice.setSenderName(orderName);
        notice.setTitle(title);
        notice.setOrderId(orderId);
        notice.setTopicId(topicId);
        notice.setType(type);
        notice.setMemo(memo);
        noticeService.saveEntity(notice);
        super.render(new ResponseData(order));
    }

    // 用户取消预约
    public void userCancelOrder(){
        Map map = getParamMap();
        noticeListService.userCancelOrder(map);
        super.render(new ResponseData());
    }

        // 用户填写时间
    public void orderDateCommit(){
        Map map = getParamMap();
        noticeListService.orderDateCommit(map);
        super.render(new ResponseData());
    }

    // 用户修改时间
    public void orderDateUpdate(){
        Map map = getParamMap();
        noticeListService.orderDateUpdate(map);
        super.render(new ResponseData());
    }

    // 行家接受预约
    public void acceptOrder(){
        Map param = getParamMap();
        noticeListService.acceptOrder(param);
        super.render(new ResponseData());
    }

    // 行家拒绝预约
    public void refuseOrder(){
        Map param = getParamMap();
        noticeListService.refuseOrder(param);
        super.render(new ResponseData());
    }

    // 批量接受预约
    public void acceptMoreOrder(){
        Map param = getParamMap();
        noticeListService.acceptMoreOrder(param);
        super.render(new ResponseData());
    }

    // 批量接受预约
    public void refuseMoreOrder(){
        Map param = getParamMap();
        noticeListService.refuseMoreOrder(param);
        super.render(new ResponseData());
    }

    // 查询通知内容
    public void queryMyNotice(){
        Map param = getParamMap();
        List<Map> result = noticeListService.queryMyNotice(param);
        super.render(new ResponseData(result));
    }

    // 行家取消订单
    public void expertCancelOrder(){
        Map param = getParamMap();
        noticeListService.expertCancelOrder(param);
        super.render(new ResponseData());
    }
    //   
    public void dataTableForHangj_noticeList() {
        List<Map> result = noticeListService.dataTableForHangj_noticeList(getParamObj(cn.flydiy.hangj.entity.Notice.class));
        super.render(new ResponseData(result));
    }

}
