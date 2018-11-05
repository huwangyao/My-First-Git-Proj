
package cn.flydiy.hangj.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.DateUtil;//定时任务代码(勿删)的手工导入的代码;
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

import cn.flydiy.hangj.dto.HangjiaJobDto;

/**
* 定时任务代码(勿删)
* Modify by v_wyaohu on 2018-8-31 17:45:37.
*/
@org.springframework.stereotype.Service
public class HangjiaJobServiceImpl implements HangjiaJobService {

    @Autowired
    private OrderService orderService;
    @Autowired
    private NoticeService noticeService;//定时任务代码(勿删)的手工注入的代码;
    @Autowired
    private NoticeListService noticeListService;//定时任务代码(勿删)的手工注入的代码;



    @Override
    public void saveDto(HangjiaJobDto dto) {
        cn.flydiy.hangj.entity.Order order = dto.getOrder();
        
        orderService.saveEntity(order);
    }

    @Override
    public void commit(HangjiaJobDto dto) {
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
    public void updateDto(HangjiaJobDto dto) {
        cn.flydiy.hangj.entity.Order order = dto.getOrder();

        
        orderService.updateEntity(order);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Order.class);
        if(_isMasterData){
            dto.setOrder(order);
            return ;
        }

    }


    @Override
    public void updateDtoNoSaveHistory(HangjiaJobDto dto) {
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
    public void batchCommit(List<HangjiaJobDto> dtos) {
        if(CollectionUtils.isNotEmpty(dtos)){
            for (HangjiaJobDto dto : dtos) {
                commit(dto);
            }
        }
    }



        //HangjiaJob的手工代码
            // 获取用户名的英文名
    public String getAccountName(String name){
        if(name.indexOf("(")>0){
            name = name.substring(0,name.indexOf("("));
        }
        return name;
    }

    // 传入时间计算距离当前时间的天数
    public long calculateDateInterval(String dateStr){
        long nowTime=(new java.util.Date()).getTime();  //获取当前时间的毫秒数
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date beforeDate = null;
        try{
            beforeDate = sdf.parse(dateStr);
        }catch (java.text.ParseException e){
            e.printStackTrace();
        }
        long reset=beforeDate.getTime();   //获取指定时间的毫秒数
        long dateDiff=nowTime-reset;

        String msg = "";
        if(dateDiff<0){
            msg="输入的时间不对";
        }else {

            long dateTemp1 = dateDiff / 1000; //秒
            long dateTemp2 = dateTemp1 / 60; //分钟
            long dateTemp3 = dateTemp2 / 60; //小时
            long dateTemp4 = dateTemp3 / 24; //天数
            long dateTemp5 = dateTemp4 / 30; //月数
            long dateTemp6 = dateTemp5 / 12; //年数

            return dateTemp4;
        }
        return 0;
    }

   // 传入时间计算距离当前时间的天数
    public String addDate(String dateStr,int day){
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date beforeDate = null;
        try{
            beforeDate = sdf.parse(dateStr);
        }catch (java.text.ParseException e){
            e.printStackTrace();
        }
        long reset=beforeDate.getTime();   //获取指定时间的毫秒数
        long addDay=day*24*60*60*1000;
        reset = reset + addDay;

        String result = sdf.format(reset);
        return result;
    }

    @Override
    public void cancelOrderAfterTwoWeek() {
        // 查询处理中状态的预约
        cn.flydiy.hangj.entity.Order orderParam = new cn.flydiy.hangj.entity.Order();
        orderParam.setStatus("处理中");
        List<Map> orderList = orderService.queryMapByParams(orderParam);

        // 整理出距离现在超过两周的预约集合
        List<Map> dealList = new ArrayList();
        for(Map map:orderList){
            String dateStr = DateUtil.toDateString((java.util.Date)map.get("createTime"));
            long day = this.calculateDateInterval(dateStr);
            if(day>=14){
                dealList.add(map);
            }
        }

        // 将列表  dealList 中的预约全都关闭  , 并发送通知  +   邮件
        for(Map map : dealList){
            String id = (String)map.get("id");
            cn.flydiy.hangj.entity.Order updateObj = new cn.flydiy.hangj.entity.Order();
            updateObj.setId(id);
            updateObj.setStatus("失败");
            orderService.updateByParam(updateObj);

            // 1、发送通知
            // 整理信息
            String recipient = (String)map.get("orderNameId");     // 接收人id——咨询者
            String sender = (String)map.get("expertId");      // 发送人id——行家
            String title = (String)map.get("title");      // 话题名称
            String topicId = (String)map.get("topicId");      // 话题id
            String orderId = (String)map.get("id");      // 预约id
            int isRead = 0;     // 是否已读
            String type = "113";
            String memo = "很遗憾，你的咨询预约提交2周后仍未被行家处理，将被系统自动关闭";

            String expertName = (String)map.get("expertName");      // 行家名称
            String orderName = (String)map.get("orderName");      // 咨询者名称

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
            notice.setMessage("很遗憾，你的咨询预约提交2周后仍未被行家处理，将被系统自动关闭");
            noticeService.saveEntity(notice);

            // 2、邮件
            String url = "http://hangjia.oa.com/#/blank-main/hangj_main-page/expert-detail?id="+topicId;
            String senderEmail = this.getAccountName(orderName) + "@tencent.com";
            String emailTitle = "【行家】您的预约已被取消";
            StringBuffer html = new StringBuffer("<html><head></head><body>");
            html.append("<div><h4>亲爱的"+orderName+":</h4>");
            // html.append("<h5>您发布的组件\""+toolsName+"\"有人评论了，快去看看吧！");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;很遗憾，你预约咨询的话题【<a href=\""+url+"\">\""+title+"\"</a>】提交2周后仍未被行家处理，将被系统自动关闭。</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别灰心，鹅厂总有一款行家适合你，去看看有没有其他适合你咨询的话题吧！<a href=\"http://hangjia.oa.com/\">http://hangjia.oa.com/</a></div>");
            html.append("<br><br><br><br><br><br>");
            html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
            html.append("<div style=\"color: b5c4df;\">v_wyaohu(胡王耀)</div>");
            html.append("<div style=\"color: b5c4df;\">电话 ：123456</div>");
            html.append("<div style=\"color: b5c4df;\">Mobile ：13726221877</div>");
            html.append("<div style=\"color: b5c4df;\">E-mail ：v_wyaohu@tencent.com</div>");
            html.append("</div></body></html>");
            noticeListService.sendEmail(senderEmail,emailTitle,html);
        }
    }

    @Override
    public void noticeFillInInfo() {
        // 查询出已经接受的预约，状态：成功
        // 查询处理中状态的预约
        cn.flydiy.hangj.entity.Order orderParam = new cn.flydiy.hangj.entity.Order();
        orderParam.setStatus("处理中");
        List<Map> orderList = orderService.queryMapByParams(orderParam);

        // 整理出距离现在超过一周的预约（已经被行家接受）集合
        List<Map> cancelList = new ArrayList();
        // 整理出距离现在还没超过一周但是超过了4天的预约（已经被行家接受）集合,发送给行家的
        List<Map> noticeList = new ArrayList();
        // 整理出距离现在还没超过一周的预约（已经被行家接受）集合,发送给咨询者的
        List<Map> noticeUserList = new ArrayList();
        for(Map map:orderList){
            String dateStr = DateUtil.toDateString((java.util.Date)map.get("acceptDate"));
            long day = this.calculateDateInterval(dateStr);
            if(day>=7){
                cancelList.add(map);
            }else if(day>=4 && day<7){
                noticeList.add(map);
                noticeUserList.add(map);
            }else if(day>=0 && day<4){
                noticeUserList.add(map);
            }
        }

        // 将列表  cancelList 中的预约全都关闭  , 并发送通知（通知中心+邮件+企业微信+短信）
        for(Map map : cancelList){
            java.util.Date meetDate = (java.util.Date)map.get("createDate");
            String id = (String)map.get("id");
            cn.flydiy.hangj.entity.Order updateObj = new cn.flydiy.hangj.entity.Order();
            updateObj.setId(id);
            updateObj.setStatus("失败");
            orderService.updateByParam(updateObj);

            // 1、发送通知
            // 整理信息
            // 发送给行家的
            String recipient = (String)map.get("expertId");     // 接收人id——行家
            String sender = (String)map.get("orderNameId");      // 发送人id——咨询者
            String title = (String)map.get("title");      // 话题名称
            String topicId = (String)map.get("topicId");      // 话题id
            String orderId = (String)map.get("id");      // 预约id
            String expertName = (String)map.get("expertName");      // 行家名称
            String orderName = (String)map.get("orderName");      // 咨询者名称
            int isRead = 0;     // 是否已读
            String type = "114";
            String memo = "由于咨询预约人未按时填写咨询时间地点，此次咨询已被系统取消。\n" +
                    "话题："+title +"\n"+
                    "咨询人："+orderName+"\n"+
                    "预约创建时间："+ DateUtil.toDateString(meetDate);

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
            notice.setMessage("请注意：咨询订单已被系统取消");
            noticeService.saveEntity(notice);

            // 2、邮件
            String senderEmail = this.getAccountName(expertName) + "@tencent.com";
            String emailTitle = "【行家】您的预约已被取消";
            StringBuffer html = new StringBuffer("<html><head></head><body>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;由于咨询预约人未按时填写咨询时间地点，此次咨询已被系统取消。</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;咨询人："+orderName+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;创建时间："+ DateUtil.toDateString(meetDate)+"</div>");
            html.append("<br>");
            html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
            html.append("</div></body></html>");
            noticeListService.sendEmail(senderEmail,emailTitle,html);


            // 发送给咨询者的
            String recipientNew = (String)map.get("orderNameId");     // 接收人id——行家
            String senderNew = (String)map.get("expertId");      // 发送人id——咨询者
            String typeNew = "200";
            String memoUser = "很遗憾，由于长时间未填写咨询时间地点，此次咨询已被自动取消。\n" +
                    "话题："+title +"\n"+
                    "行家："+expertName+"\n"+
                    "预约创建时间："+ DateUtil.toDateString(meetDate);

            cn.flydiy.hangj.entity.Notice noticeUser = new cn.flydiy.hangj.entity.Notice();
            // 保存通知的信息
            noticeUser.setExpertName(expertName);
            noticeUser.setIsRead(isRead);
            noticeUser.setOrderId(orderId);
            noticeUser.setRecipient(recipientNew);
            noticeUser.setSender(senderNew);
            noticeUser.setSenderName(orderName);
            noticeUser.setTitle(title);
            noticeUser.setOrderId(orderId);
            noticeUser.setTopicId(topicId);
            noticeUser.setType(typeNew);
            noticeUser.setMemo(memoUser);
            noticeUser.setMessage("由于未能按时填写时间地点，你的咨询订单将被取消");
            noticeService.saveEntity(noticeUser);

            // 2、邮件
            String senderEmailUser = this.getAccountName(orderName) + "@tencent.com";
            String emailTitleUser = "【行家】由于未能按时填写时间地点，你的咨询订单将被取消";
            StringBuffer htmlNew = new StringBuffer("<html><head></head><body>");
            String urlUser = "http://ntsapps.oa.com/hangjia/";
            htmlNew.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;很遗憾，由于长时间未填写咨询时间地点，此次咨询已被自动取消。</div>");
            htmlNew.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
            htmlNew.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家："+expertName+"</div>");
            htmlNew.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;创建时间："+ DateUtil.toDateString(meetDate)+"</div>");
            htmlNew.append("<br>");
            htmlNew.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
            htmlNew.append("<a href=\""+urlUser+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">去发现更多行家</span></span></b></a> ");
            htmlNew.append("</div></body></html>");
            noticeListService.sendEmail(senderEmailUser,emailTitleUser,htmlNew);
        }

        // 将列表  noticeList 中的预约发通知写地点  , 并发送通知（通知中心+邮件+企业微信+短信）
        for(Map map : noticeList){
            // 1、发送通知
            // 整理信息
            java.util.Date meetDate = (java.util.Date)map.get("createDate");
            String recipient = (String)map.get("expertId");     // 接收人id——行家
            String sender = (String)map.get("orderNameId");      // 发送人id——咨询者
            String title = (String)map.get("title");      // 话题名称
            String topicId = (String)map.get("topicId");      // 话题id
            String orderId = (String)map.get("id");      // 预约id
            String expertName = (String)map.get("expertName");      // 行家名称
            String orderName = (String)map.get("orderName");      // 咨询者名称

            int isRead = 0;     // 是否已读
            String type = "115";
            String  memo = "你接受的咨询预约仍未填写咨询时间地点，请联系预约人确定咨询时间地点，并提醒预约人在3天内将时间地点录入系统，否则系统将自动取消此次咨询订单。\n" +
                    "话题："+title +"\n"+
                    "咨询人："+orderName+"\n"+
                    "预约创建时间："+ DateUtil.toDateString(meetDate);

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
            notice.setMessage("请注意：咨询订单仍未确认时间地点");
            noticeService.saveEntity(notice);

            // 2、邮件
            String dateStr = DateUtil.toDateString((java.util.Date)map.get("acceptDate"));
            String senderEmail = this.getAccountName(expertName) + "@tencent.com";
            String expertAccount = this.getAccountName(orderName);
            String chatUrl = "wxwork://message/?username="+expertAccount;
            String emailTitle = "【行家】请注意：咨询订单仍未确认时间地点";
            StringBuffer html = new StringBuffer("<html><head></head><body>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你接受的咨询预约仍未填写咨询时间地点，请联系预约人确定咨询时间地点，并提醒预约人在3天内将时间地点录入系统，否则系统将自动取消此次咨询订单。</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;咨询人："+orderName+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;创建时间："+ DateUtil.toDateString(meetDate)+"</div>");
            html.append("<br>");
            html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
            html.append("<a href=\""+chatUrl+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">唤起与行家的企业微信会话</span></span></b></a> ");
            html.append("</div></body></html>");
            noticeListService.sendEmail(senderEmail,emailTitle,html);
        }

        // 将列表  noticeUserList 中的预约发通知写地点  , 并发送通知（通知中心+邮件+企业微信+短信）====通知用户
        for(Map map : noticeUserList){
            // 1、发送通知
            // 整理信息
            java.util.Date createDate = (java.util.Date)map.get("createDate");
            String meetDate = (String)map.get("meetDate");
            String recipient = DateUtil.toDateString((java.util.Date)map.get("orderNameId"));     // 接收人id——咨询者
            String sender = (String)map.get("expertId");      // 发送人id——行家
            String title = (String)map.get("title");      // 话题名称
            String topicId = (String)map.get("topicId");      // 话题id
            String orderId = (String)map.get("id");      // 预约id
            String expertName = (String)map.get("expertName");      // 行家名称
            String orderName = (String)map.get("orderName");      // 咨询者名称

            int isRead = 0;     // 是否已读
            String type = "201";
            String meetDateAddSeven = this.addDate(meetDate,7);
            meetDateAddSeven = meetDateAddSeven.substring(0,10)+" 23:59";
            String  memo = "你的咨询预约仍未填写咨询时间地点，请尽快联系行家确定咨询时间地点。如至"+meetDateAddSeven+"仍未填写时间地点，此次咨询将被系统自动取消。\n" +
                    "话题："+title +"\n"+
                    "行家："+expertName;

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
            notice.setMessage("你有未填写时间地点的咨询订单，请尽快填写");
            noticeService.saveEntity(notice);

            // 2、邮件
            String dateStr = DateUtil.toDateString((java.util.Date)map.get("acceptDate"));
            String senderEmail = this.getAccountName(orderName) + "@tencent.com";
            String expertAccount = this.getAccountName(expertName);
            String chatUrl = "wxwork://message/?username="+expertAccount;
            String url = "http://hangjia.oa.com/#/blank-main/hangj_hangj-my/my-menu";
            String emailTitle = "【行家】你有未填写时间地点的咨询订单，请尽快填写";
            StringBuffer html = new StringBuffer("<html><head></head><body>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你的咨询预约仍未填写咨询时间地点，请尽快联系行家确定咨询时间地点。如至"+meetDateAddSeven+"仍未填写时间地点，此次咨询将被系统自动取消。</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家："+expertName+"</div>");
            html.append("<br>");
            html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
            html.append("<a href=\""+chatUrl+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">联系行家</span></span></b></a> ");
            html.append("<a href=\""+url+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">填写时间地点</span></span></b></a> ");
            html.append("</div></body></html>");
            noticeListService.sendEmail(senderEmail,emailTitle,html);
        }
    }

    // 在传入时间的基础上减少天数
    public String reduceDate(String dateStr,int day){
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date beforeDate = null;
        try{
            beforeDate = sdf.parse(dateStr);
        }catch (java.text.ParseException e){
            e.printStackTrace();
        }
        long reset=beforeDate.getTime();   //获取指定时间的毫秒数
        long addDay=day*24*60*60*1000;
        reset = reset - addDay;

        String result = sdf.format(reset);
        return result;
    }

    @Override
    public void OneDayAheadNoticeJob() {
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 查询已经填写地址的预约  status:"已发日历"
        cn.flydiy.hangj.entity.Order orderParam = new cn.flydiy.hangj.entity.Order();
        orderParam.setStatus("已发日历");
        List<Map> orderList = orderService.queryMapByParams(orderParam);

        // 整理出明天要咨询的预约
        List<Map> dealList = new ArrayList();
        for(Map map:orderList){
            String meetDate = DateUtil.toDateString((java.util.Date)map.get("meetDate"));
            meetDate = meetDate.substring(0,10) + " 00:00:00";
            String nowDate = sdf.format(new java.util.Date());
            nowDate = nowDate.substring(0,10) + " 00:00:00";
            long meetDateTime = DateUtil.parseDate(meetDate).getTime();
            long nowDateTime = DateUtil.parseDate(nowDate).getTime();
            long between = meetDateTime - nowDateTime;
            if(between == 24*60*60*1000){
                dealList.add(map);
            }
        }

        // 将列表  dealList 中的预约全都关闭  , 并发送通知  +   邮件  + 公众号  + 短信
        for(Map map : dealList){
            String meetDate = DateUtil.toDateString((java.util.Date)map.get("meetDate"));
            // 1、发送通知
            // 整理信息
            String recipient = (String)map.get("orderNameId");     // 接收人id——咨询者
            String sender = (String)map.get("expertId");      // 发送人id——行家
            String title = (String)map.get("title");      // 话题名称
            String topicId = (String)map.get("topicId");      // 话题id
            String orderId = (String)map.get("id");      // 预约id
            String expertName = (String)map.get("expertName");      // 行家名称
            String orderName = (String)map.get("orderName");      // 咨询者名称
            int isRead = 0;     // 是否已读
            String type = "116";
            String memo = "你有一场咨询将于明天进行，请与咨询者再次确定，按时赴约。\n" +
                    "话题："+title +"\n"+
                    "咨询人："+orderName+"\n"+
                    "预约时间："+ meetDate;

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
            notice.setMessage("请注意：有咨询将于明天[时间]进行");
            noticeService.saveEntity(notice);

            // 2、邮件
            String expertAccount = this.getAccountName(expertName);
            String chatUrl = "wxwork://message/?username="+expertAccount;
            String url = "http://hangjia.oa.com/#/blank-main/hangj_hangj-my/my-menu";
            String senderEmail = this.getAccountName(orderName) + "@tencent.com";
            String emailTitle = "【行家】请注意：有咨询将于明天[时间]进行";
            StringBuffer html = new StringBuffer("<html><head></head><body>");
            // html.append("<h5>您发布的组件\""+toolsName+"\"有人评论了，快去看看吧！");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你有一场咨询将于明天进行，请与咨询者再次确定，按时赴约。</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;咨询人："+orderName+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预约时间："+ meetDate+"</div>");
            html.append("<br>");
            html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
            html.append("<a href=\""+chatUrl+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">唤起与行家的企业微信会话</span></span></b></a> ");
            html.append("<a href=\""+url+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">修改时间地点</span></span></b></a> ");
            html.append("</div></body></html>");
            noticeListService.sendEmail(senderEmail,emailTitle,html);
        }
    }
}


