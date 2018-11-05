
package cn.flydiy.hangj.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.DateUtil;//通知中心代码(勿删)的手工导入的代码;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.web.WebUtils;//通知中心代码(勿删)的手工导入的代码;
import cn.flydiy.hangj.service.NoticeService;
import cn.flydiy.serial.util.SerialNumberUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.dto.NoticeListDto;

/**
* 通知中心代码(勿删)
* Modify by v_wyaohu on 2018-8-31 17:11:03.
*/
@org.springframework.stereotype.Service
public class NoticeListServiceImpl implements NoticeListService {

    @Autowired
    private OrderService orderService;//通知中心代码(勿删)的手工注入的代码;
    @Autowired
    private NoticeListService noticeListService;//通知中心代码(勿删)的手工注入的代码;
    @Autowired
    private NoticeService noticeService;



    @Override
    public void saveDto(NoticeListDto dto) {
        cn.flydiy.hangj.entity.Notice notice = dto.getNotice();
        
        noticeService.saveEntity(notice);
    }

    @Override
    public void commit(NoticeListDto dto) {
        cn.flydiy.hangj.entity.Notice notice = dto.getNotice();
        if(cn.flydiy.common.util.StringUtil.isEmpty(notice.getId())){
            
            saveDto(dto);
        }else{
            
            updateDto(dto);
        }
    }


    @Override
    public Map queryById(String id) {
        Map result = new HashMap();
        Map map = new HashMap();
        cn.flydiy.hangj.entity.Notice notice = noticeService.findOne(id);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Notice.class);
        BeanUtil.copyPropertiesToMap(map,notice);
        result.put("notice",map);
        return result;
    }


    @Override
    public List<Map> queryByIds(String... ids) {
        List<Map> result = new ArrayList<>();
        List<cn.flydiy.hangj.entity.Notice> notices = noticeService.findByIds(ids);

        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Notice.class);

        for (cn.flydiy.hangj.entity.Notice notice : notices) {
            Map temp = new HashMap();
            BeanUtil.copyPropertiesToMap(temp,notice);
            result.add(temp);
        }
        return result;
    }


    @Override
    public void updateDto(NoticeListDto dto) {
        cn.flydiy.hangj.entity.Notice notice = dto.getNotice();

        
        noticeService.updateEntity(notice);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Notice.class);
        if(_isMasterData){
            dto.setNotice(notice);
            return ;
        }

    }


    @Override
    public void updateDtoNoSaveHistory(NoticeListDto dto) {
        cn.flydiy.hangj.entity.Notice notice = dto.getNotice();

        
        noticeService.updateEntityNoSaveHistory(notice);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Notice.class);

    }


    @Override
    public void deleteById(String id) {
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.Notice.class);
        cn.flydiy.hangj.entity.Notice notice = noticeService.findOne(id);
        noticeService.delete(id);
    }

    @Override
    public void batchCommit(List<NoticeListDto> dtos) {
        if(CollectionUtils.isNotEmpty(dtos)){
            for (NoticeListDto dto : dtos) {
                commit(dto);
            }
        }
    }



    public List<Map> dataTableForHangj_noticeList(cn.flydiy.hangj.entity.Notice notice) {
        List<Map> result = noticeService.queryPageMapByParams(notice);
        return result;
    }
        //NoticeList的手工代码
             // 获取用户名的英文名
    public String getAccountName(String name){
        if(name.indexOf("(")>0){
            name = name.substring(0,name.indexOf("("));
        }
        return name;
    }
 
 @Override
    public void sendEmail(String email,String title,StringBuffer html) {
        String mailServerUrl = "https://mail.tencent.com/ews/Exchange.asmx";
//        String mailServerUrl = ResourceUtils.getProperty("esf.mail.server.url");
        if(StringUtil.isEmpty(mailServerUrl)){
//            log.debug("邮件服务器地址尚未配置!!!");
            return;
        }
        microsoft.exchange.webservices.data.core.ExchangeService exchangeService = new microsoft.exchange.webservices.data.core.ExchangeService(microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion.Exchange2010_SP2);
        exchangeService.setCredentials(new microsoft.exchange.webservices.data.credential.WebCredentials("v_wyaohu", "Hu489264", "tencent.com"));

        try {
            exchangeService.setUrl(new java.net.URI(mailServerUrl));
            //邮件类
            microsoft.exchange.webservices.data.core.service.item.EmailMessage message = new microsoft.exchange.webservices.data.core.service.item.EmailMessage(exchangeService);
            // 邮件主题
            message.setSubject(title);
            message.setBody(new microsoft.exchange.webservices.data.property.complex.MessageBody());
            // 指定发送邮件的格式，可以是Text和Html格式
            message.getBody().setBodyType(microsoft.exchange.webservices.data.core.enumeration.property.BodyType.HTML);

            // 邮件内容 demo
//            StringBuffer html = new StringBuffer("<html><head></head><body>");
//            html.append("<div><h4>亲爱的"+realName+":</h4>");
//            // html.append("<h5>您发布的组件\""+toolsName+"\"有人评论了，快去看看吧！");
//            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您发布的组件<a href=\""+url+"\">\""+toolsName+"\"</a>有人评论了，快去看看吧！</div>");
//            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;组件市场地址：<a href=\"http://cmall.oa.com/\">http://cmall.oa.com</a></div>");
//            html.append("<br><br><br><br><br><br>");
//            html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
//            html.append("<div style=\"color: b5c4df;\">v_yyhyu(余彦烨)</div>");
//            html.append("<div style=\"color: b5c4df;\">电话 ：75164</div>");
//            html.append("<div style=\"color: b5c4df;\">Mobile ：13417701130</div>");
//            html.append("<div style=\"color: b5c4df;\">E-mail ：v_yyhyu@tencent.com</div>");
//            html.append("</div></body></html>");
            message.getBody().setText(html.toString());

            message.getToRecipients().add(email);
            // 发送并保存已发送邮件
            message.sendAndSaveCopy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void userCancelOrder(Map map) {
        // 处理预约单的状态
        String state = (String)map.get("status");
        String id = (String)map.get("id");
        cn.flydiy.hangj.entity.Order updateOrder = new cn.flydiy.hangj.entity.Order();
        updateOrder.setId(id);
        updateOrder.setStatus("失败");
        if(StringUtil.equalsIgnoreCase("成功",state)){
            updateOrder.setMemo("行家已接受，还未填写预约时间，用户取消预约");
        }else if(StringUtil.equalsIgnoreCase("处理中",state)){
            updateOrder.setMemo("行家未接受，用户取消预约");
        }else if(StringUtil.equalsIgnoreCase("已发日历",state)){
            updateOrder.setMemo("行家已接受，已填写预约时间，用户取消预约");
        }
        orderService.updateByParam(updateOrder);

        // 发送通知
        // 1、发送业务通知消息
        // 整理信息
        String recipient = (String)map.get("expertId");     // 接收人id——行家
        String sender = (String)map.get("orderNameId");      // 发送人id——咨询者
        String title = (String)map.get("title");      // 话题名称
        String topicId = (String)map.get("topicId");      // 话题id
        String orderId = (String)map.get("id");      // 预约id
        String expertName = (String)map.get("expertName");      // 行家名称
        String orderName = (String)map.get("orderName");      // 咨询者名称
        int isRead = 0;     // 是否已读
        String type = "";
        String memo = "咨询者取消了咨询订单，如有疑问请与咨询人联系。\n" +
                "话题："+title +"\n"+
                "咨询人："+orderName;
        if(StringUtil.equalsIgnoreCase("成功",state)){
            type = "103";     // 通知类型
        }else if(StringUtil.equalsIgnoreCase("处理中",state)){
            type = "102";     // 通知类型
        }else if(StringUtil.equalsIgnoreCase("已发日历",state)){
            type = "112";     // 通知类型
        }

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
        notice.setMessage("咨询者取消订单");
        if(StringUtil.equalsIgnoreCase("成功",state) || StringUtil.equalsIgnoreCase("已发日历",state)){
            noticeService.saveEntity(notice);

            // 2、发送邮件
            String senderEmail = this.getAccountName(expertName) + "@tencent.com";
            String expertAccount = this.getAccountName(orderName);
            String chatUrl = "wxwork://message/?username="+expertAccount;
            String emailTitle = "【行家】咨询者取消订单";
            StringBuffer html = new StringBuffer("<html><head></head><body>");
//            html.append("<div><h4>亲爱的"+orderName+":</h4>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;咨询者取消了咨询订单，如有疑问请与咨询人联系。</div>");
            html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
            html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;咨询人："+orderName+"</div>");
            html.append("<br>");
            html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
            html.append("<a href=\""+chatUrl+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">唤起与咨询人的企业微信会话</span></span></b></a> ");
//         html.append("<div style=\"color: b5c4df;\">Mobile ：13726221877</div>");
//         html.append("<div style=\"color: b5c4df;\">E-mail ：v_wyaohu@tencent.com</div>");
            html.append("</div></body></html>");
            noticeListService.sendEmail(senderEmail,emailTitle,html);
        }

    }

    @Override
    public void orderDateCommit(Map map) {
        // 修改订单的时间和地址
        java.util.Date meetDate = cn.flydiy.common.util.DateUtil.parseDate ((String)map.get("meetDate"));
        String meetAddress = (String)map.get("meetAddress");
        String id = (String)map.get("id");
        String activeType = (String)map.get("activeType");

        cn.flydiy.hangj.entity.Order updateOrder = new cn.flydiy.hangj.entity.Order();
        updateOrder.setId(id);
        updateOrder.setStatus("已发日历");
        updateOrder.setMeetAddress(meetAddress);
        updateOrder.setMeetDate(meetDate);
        // 用户保存的见面时间
        if(StringUtil.equalsIgnoreCase("userCommit",activeType)){
            updateOrder.setMemo("用户提交的见面时间、地点");
        }else{ // 行家保存的见面时间
            updateOrder.setMemo("行家提交的见面时间、地点");
        }
        orderService.updateByParam(updateOrder);

        // 发送通知
        // 1、发送业务通知消息
        // 整理信息
        String recipient = "";     // 接收人id——行家
        String sender = "";      // 发送人id——咨询者
        String title = (String)map.get("title");      // 话题名称
        String topicId = (String)map.get("topicId");      // 话题id
        String orderId = (String)map.get("id");      // 预约id
        String expertName = (String)map.get("expertName");      // 行家名称
        String orderName = (String)map.get("orderName");      // 咨询者名称
        int isRead = 0;     // 是否已读
        String type = "";
        String memo = "";
        if(StringUtil.equalsIgnoreCase("userCommit",activeType)){
            type = "104";     // 通知类型
            memo = "咨询人刚刚填写了咨询的时间地点，请检查确认。\n" +
                    "话题："+title +"\n"+
                    "咨询人："+orderName+"\n"+
                    "学员填写的时间地点："+ DateUtil.toDateString(meetDate) + "  "+ meetAddress;
            recipient = (String)map.get("expertId");     // 接收人id——行家
            sender = (String)map.get("orderNameId");      // 发送人id——咨询者
        }else{
            type = "105";     // 通知类型
            memo = "行家填写了此次咨询的时间地点，如有疑问请与行家联系\n" +
                    "话题："+title +"\n"+
                    "行家："+expertName+"\n"+
                    "行家填写的时间地点："+ DateUtil.toDateString(meetDate) + "  "+ meetAddress;
            recipient = (String)map.get("orderNameId");     // 接收人id——咨询者
            sender = (String)map.get("expertId");      // 发送人id——行家
        }

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
        if(StringUtil.equalsIgnoreCase("userCommit",activeType)){
            notice.setMessage("请确认：学员刚刚填写了咨询的时间地点");
        }else{
            notice.setMessage("行家填写了咨询时间地点");
        }
        noticeService.saveEntity(notice);

        // 2、邮件
        if(StringUtil.equalsIgnoreCase("userCommit",activeType)){
            // 咨询者填写，就发送给行家
            String senderEmail = this.getAccountName(expertName) + "@tencent.com";
            String expertAccount = this.getAccountName(orderName);
            String chatUrl = "wxwork://message/?username="+expertAccount;
            String url = "http://hangjia.oa.com/#/blank-main/hangj_hangj-my/my-menu";
            String emailTitle = "【行家】请确认：学员刚刚填写了咨询的时间地点";
            StringBuffer html = new StringBuffer("<html><head></head><body>");
//            html.append("<div><h4>亲爱的"+orderName+":</h4>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;咨询人刚刚填写了咨询的时间地点，请检查确认。</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;咨询人："+orderName+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学员填写的时间地点："+ DateUtil.toDateString(meetDate) + " &nbsp;&nbsp;"+ meetAddress+"</div>");
            html.append("<br>");
            html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
            html.append("<a href=\""+chatUrl+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">唤起与咨询人的企业微信会话</span></span></b></a> ");
            html.append("<a href=\""+url+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">去订单中心查看</span></span></b></a> ");
//         html.append("<div style=\"color: b5c4df;\">Mobile ：13726221877</div>");
//         html.append("<div style=\"color: b5c4df;\">E-mail ：v_wyaohu@tencent.com</div>");
            html.append("</div></body></html>");
            noticeListService.sendEmail(senderEmail,emailTitle,html);
        }else{
            // 行家填写，就发送给咨询者
            String senderEmail = this.getAccountName(orderName) + "@tencent.com";
            String expertAccount = this.getAccountName(expertName);
            String chatUrl = "wxwork://message/?username="+expertAccount;
            String emailTitle = "【行家】行家填写了咨询时间地点";
            StringBuffer html = new StringBuffer("<html><head></head><body>");
//            html.append("<div><h4>亲爱的"+orderName+":</h4>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家填写了此次咨询的时间地点，如有疑问请与行家联系。</div>");
            html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
            html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家："+expertName+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家填写的时间地点："+ DateUtil.toDateString(meetDate) + " &nbsp;&nbsp;"+ meetAddress+"</div>");
            html.append("<br>");
            html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
            html.append("<a href=\""+chatUrl+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">唤起与行家的企业微信会话</span></span></b></a> ");
//         html.append("<div style=\"color: b5c4df;\">Mobile ：13726221877</div>");
//         html.append("<div style=\"color: b5c4df;\">E-mail ：v_wyaohu@tencent.com</div>");
            html.append("</div></body></html>");
            noticeListService.sendEmail(senderEmail,emailTitle,html);
        }
    }

     @Override
    public void acceptOrder(Map map) {
                java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 处理预约单的状态
        String id = (String)map.get("id");
        cn.flydiy.hangj.entity.Order updateOrder = new cn.flydiy.hangj.entity.Order();
        updateOrder.setId(id);
        updateOrder.setStatus("成功");
        updateOrder.setAcceptDate(new java.util.Date());
        updateOrder.setMemo("行家接受预约");
        orderService.updateByParam(updateOrder);

        // 发送通知
        // 1、发送业务通知消息
        // 整理信息
        String recipient = (String)map.get("orderNameId");  // 接收人id——咨询者
        String sender = (String)map.get("expertId");         // 发送人id——行家
        String title = (String)map.get("title");      // 话题名称
        String topicId = (String)map.get("topicId");      // 话题id
        String orderId = (String)map.get("id");      // 预约id
        int isRead = 0;     // 是否已读
        String type = "106";
        String expertName = (String)map.get("expertName");      // 行家名称
        String orderName = (String)map.get("orderName");      // 咨询者名称

        String memo = "你的咨询预约已被行家接受，快去联系行家约定咨询时间地点吧\n" +
                "话题："+title +"\n"+
                "行家："+expertName+"\n"+
                "时间："+sdf.format( new java.util.Date());

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
        notice.setMessage("你的咨询预约已被行家接受，快去联系行家吧");
        noticeService.saveEntity(notice);

        // 2、邮件

        String url = "http://hangjia.oa.com/#/blank-main/hangj_hangj-my/my-menu";

        String senderEmail = this.getAccountName(orderName) + "@tencent.com";
        String expertAccount = this.getAccountName(expertName);
        String chatUrl = "wxwork://message/?username="+expertAccount;
        String emailTitle = "【行家】你的咨询预约已被行家接受，快去联系行家吧";
        StringBuffer html = new StringBuffer("<html><head></head><body>");
        html.append("<div><h4>亲爱的"+orderName+":</h4>");
        html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你的咨询的预约已被行家接受，快去联系行家约定咨询时间地点吧。</div>");
        html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
        html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家："+expertName+"</div>");
        html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间："+sdf.format( new java.util.Date())+"</div>");
        html.append("<br>");
        html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
        html.append("<a href=\""+chatUrl+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">联系行家（唤起企业微信会话）</span></span></b></a> ");
        html.append("<a href=\""+url+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">填写时间地点</span></span></b></a> ");
//         html.append("<div style=\"color: b5c4df;\">Mobile ：13726221877</div>");
//         html.append("<div style=\"color: b5c4df;\">E-mail ：v_wyaohu@tencent.com</div>");
        html.append("</div></body></html>");
        noticeListService.sendEmail(senderEmail,emailTitle,html);
    }

    @Override
    public void refuseOrder(Map map) {
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 处理预约单的状态
        String id = (String)map.get("id");
        cn.flydiy.hangj.entity.Order updateOrder = new cn.flydiy.hangj.entity.Order();
        updateOrder.setId(id);
        updateOrder.setStatus("失败");
        updateOrder.setMemo("行家拒绝预约");
        orderService.updateByParam(updateOrder);

        // 发送通知
        // 1、发送业务通知消息
        // 整理信息
        String recipient = (String)map.get("expertId");     // 接收人id——行家
        String sender = (String)map.get("orderNameId");      // 发送人id——咨询者
        String title = (String)map.get("title");      // 话题名称
        String topicId = (String)map.get("topicId");      // 话题id
        String orderId = (String)map.get("id");      // 预约id
        int isRead = 0;     // 是否已读
        String type = "107";
        String expertName = (String)map.get("expertName");      // 行家名称
        String orderName = (String)map.get("orderName");      // 咨询者名称
        String memo = "很遗憾，行家此次无法接受你的咨询预约。别灰心，鹅厂中总有一款行家适合你，去看看有没有其他需要咨询的话题吧\n" +
                "话题："+title +"\n"+
                "行家："+expertName+"\n"+
                "时间："+sdf.format( new java.util.Date());


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
        notice.setMessage("很遗憾，行家此次无法接受你的咨询预约");
        noticeService.saveEntity(notice);

        // 2、邮件
        String url = "http://test.ntsapps.oa.com/hangjia/";

        String senderEmail = this.getAccountName(orderName) + "@tencent.com";
        String expertAccount = this.getAccountName(expertName);
        String emailTitle = "【行家】很遗憾，行家此次无法接受你的咨询预约";
        StringBuffer html = new StringBuffer("<html><head></head><body>");
        html.append("<div><h4>亲爱的"+orderName+":</h4>");
        html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;很遗憾，行家此次无法接受你的咨询预约。别灰心，鹅厂中总有一款行家适合你，去看看有没有其他需要咨询的话题吧。</div>");
        html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
        html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家："+expertName+"</div>");
        html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间："+sdf.format( new java.util.Date())+"</div>");
        html.append("<br>");
        html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
        html.append("<a href=\""+url+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">去发现更多行家</span></span></b></a> ");
//         html.append("<div style=\"color: b5c4df;\">Mobile ：13726221877</div>");
//         html.append("<div style=\"color: b5c4df;\">E-mail ：v_wyaohu@tencent.com</div>");
        html.append("</div></body></html>");
        noticeListService.sendEmail(senderEmail,emailTitle,html);
    }

        @Override
    public void acceptMoreOrder(Map param) {
        List<Map> acceptList = (List<Map>)param.get("list");
        for(Map map : acceptList){
            this.acceptOrder(map);
        }
    }

    @Override
    public void refuseMoreOrder(Map param) {
        List<Map> acceptList = (List<Map>)param.get("list");
        for(Map map : acceptList){
            this.refuseOrder(map);
        }
    }

    @Override
    public List<Map> queryMyNotice(Map param) {
        cn.flydiy.hangj.entity.Notice noticeParam = new cn.flydiy.hangj.entity.Notice();
        noticeParam.setRecipient(WebUtils.getLoginUser().getId());
        List<Map> result = noticeService.queryMapByParams(noticeParam);
        for(Map map : result){
            // 处理时间
            String makeOrderDate = DateUtil.toDateString((java.util.Date)map.get("createTime"));
            if(StringUtil.isNotEmpty(makeOrderDate)){
                makeOrderDate = makeOrderDate.substring(0,10);
                map.put("makeOrderDate",makeOrderDate);
            }
        }
        return result;
    }

    @Override
    public void orderDateUpdate(Map map) {
        // 修改订单的时间和地址
        java.util.Date meetDate = cn.flydiy.common.util.DateUtil.parseDate ((String)map.get("meetDate"));
        String meetAddress = (String)map.get("meetAddress");
        String id = (String)map.get("id");
        String activeType = (String)map.get("activeType");

        cn.flydiy.hangj.entity.Order updateOrder = new cn.flydiy.hangj.entity.Order();
        updateOrder.setId(id);
        updateOrder.setStatus("已发日历");
        updateOrder.setMeetAddress(meetAddress);
        updateOrder.setMeetDate(meetDate);
        // 用户保存的见面时间
        if(StringUtil.equalsIgnoreCase("userUpdate",activeType)){
            updateOrder.setMemo("用户修改见面时间、地点");
        }else{ // 行家保存的见面时间
            updateOrder.setMemo("行家修改见面时间、地点");
        }
        orderService.updateByParam(updateOrder);

        // 发送通知
        // 1、发送业务通知消息
        // 整理信息
        String recipient = "";     // 接收人id——行家
        String sender = "";      // 发送人id——咨询者
        String title = (String)map.get("title");      // 话题名称
        String topicId = (String)map.get("topicId");      // 话题id
        String orderId = (String)map.get("id");      // 预约id
        String expertName = (String)map.get("expertName");      // 行家名称
        String orderName = (String)map.get("orderName");      // 咨询者名称
        int isRead = 0;     // 是否已读
        String type = "";
        String memo = "";
        if(StringUtil.equalsIgnoreCase("userUpdate",activeType)){
            type = "108";     // 通知类型
            memo = "咨询者修改了咨询的时间地点，如有疑问请咨询者联系\n" +
                    "话题："+title +"\n"+
                    "行家："+expertName+"\n"+
                    "修改后的时间地点："+ DateUtil.toDateString(meetDate) + "  "+ meetAddress;
            recipient = (String)map.get("expertId");     // 接收人id——行家
            sender = (String)map.get("orderNameId");      // 发送人id——咨询者
        }else{
            type = "109";     // 通知类型
            memo = "行家修改了咨询的时间地点，如有疑问请与行家联系\n" +
                    "话题："+title +"\n"+
                    "行家："+expertName+"\n"+
                    "修改后的时间地点："+ DateUtil.toDateString(meetDate) + "  "+ meetAddress;
            recipient = (String)map.get("orderNameId");     // 接收人id——咨询者
            sender = (String)map.get("expertId");      // 发送人id——行家
        }

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
        if(StringUtil.equalsIgnoreCase("userUpdate",activeType)){
            notice.setMessage("咨询者修改时间地点");
        }else{
            notice.setMessage("行家修改了咨询时间地点");
        }
        noticeService.saveEntity(notice);

        // 2、邮件
        if(StringUtil.equalsIgnoreCase("userUpdate",activeType)){
            // 咨询者修改，就发送给行家
            String senderEmail = this.getAccountName(expertName) + "@tencent.com";
            String expertAccount = this.getAccountName(orderName);
            String chatUrl = "wxwork://message/?username="+expertAccount;
            String emailTitle = "【行家】咨询者修改时间地点";
            StringBuffer html = new StringBuffer("<html><head></head><body>");
//            html.append("<div><h4>亲爱的"+orderName+":</h4>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;咨询者修改了咨询的时间地点，如有疑问请咨询者联系。</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家："+expertName+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改后的时间地点："+ DateUtil.toDateString(meetDate) + " &nbsp;&nbsp;"+ meetAddress+"</div>");
            html.append("<br>");
            html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
            html.append("<a href=\""+chatUrl+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">唤起与咨询人的企业微信会话</span></span></b></a> ");
//         html.append("<div style=\"color: b5c4df;\">Mobile ：13726221877</div>");
//         html.append("<div style=\"color: b5c4df;\">E-mail ：v_wyaohu@tencent.com</div>");
            html.append("</div></body></html>");
            noticeListService.sendEmail(senderEmail,emailTitle,html);
        }else{
            // 行家修改，就发送给咨询者
            String senderEmail = this.getAccountName(orderName) + "@tencent.com";
            String expertAccount = this.getAccountName(expertName);
            String chatUrl = "wxwork://message/?username="+expertAccount;
            String emailTitle = "【行家】行家修改了咨询时间地点";
            StringBuffer html = new StringBuffer("<html><head></head><body>");
//            html.append("<div><h4>亲爱的"+orderName+":</h4>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家修改了咨询的时间地点，如有疑问请与行家联系。</div>");
            html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
            html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家："+expertName+"</div>");
            html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改后的时间地点："+ DateUtil.toDateString(meetDate) + " &nbsp;&nbsp;"+ meetAddress+"</div>");
            html.append("<br>");
            html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
            html.append("<a href=\""+chatUrl+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">唤起与咨询人的企业微信会话</span></span></b></a> ");
//         html.append("<div style=\"color: b5c4df;\">Mobile ：13726221877</div>");
//         html.append("<div style=\"color: b5c4df;\">E-mail ：v_wyaohu@tencent.com</div>");
            html.append("</div></body></html>");
            noticeListService.sendEmail(senderEmail,emailTitle,html);
        }
    }

        @Override
    public void expertCancelOrder(Map map) {
        // 处理预约单的状态
        String state = (String)map.get("status");
        String id = (String)map.get("id");
        cn.flydiy.hangj.entity.Order updateOrder = new cn.flydiy.hangj.entity.Order();
        updateOrder.setId(id);
        updateOrder.setStatus("失败");
        if(StringUtil.equalsIgnoreCase("成功",state)){
            updateOrder.setMemo("行家已接受，行家取消预约");
        }else if(StringUtil.equalsIgnoreCase("已发日历",state)){
            updateOrder.setMemo("行家已接收，已发日历，行家取消预约");
        }
        orderService.updateByParam(updateOrder);

        // 发送通知
        // 1、发送业务通知消息
        // 整理信息
        String recipient = (String)map.get("expertId");     // 接收人id——行家
        String sender = (String)map.get("orderNameId");      // 发送人id——咨询者
        String title = (String)map.get("title");      // 话题名称
        String topicId = (String)map.get("topicId");      // 话题id
        String orderId = (String)map.get("id");      // 预约id
        String expertName = (String)map.get("expertName");      // 行家名称
        String orderName = (String)map.get("orderName");      // 咨询者名称
        int isRead = 0;     // 是否已读
        String type = "";
        String memo = "行家取消了咨询订单，如有疑问请与行家联系\n" +
                "话题："+title +"\n"+
                "行家："+expertName;
        if(StringUtil.equalsIgnoreCase("成功",state)){
            type = "110";     // 通知类型
        }else if(StringUtil.equalsIgnoreCase("已发日历",state)){
            type = "111";     // 通知类型
        }


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
        notice.setMessage("行家取消订单");

        noticeService.saveEntity(notice);

        // 2、邮件

        String senderEmail = this.getAccountName(orderName) + "@tencent.com";
        String expertAccount = this.getAccountName(expertName);
        String chatUrl = "wxwork://message/?username="+expertAccount;
        String emailTitle = "【行家】行家取消订单";
        StringBuffer html = new StringBuffer("<html><head></head><body>");
        html.append("<div><h4>亲爱的"+orderName+":</h4>");
        html.append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家取消了咨询订单，如有疑问请与行家联系。</div>");
        html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话题："+title+"</div>");
        html.append("<div style=\"color: #b5c4df;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行家："+expertName+"</div>");
        html.append("<br>");
        html.append("<hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\">");
        html.append("<a href=\""+chatUrl+"\"><b><span lang=\"EN-US\" style=\"font-size:9.0pt;color:#5DADE2;border:solid #5DADE2 1.0pt;padding:5.0pt;background:#ECF0F1;text-decoration:none;text-underline:none\"><span lang=\"EN-US\">唤起与咨询人的企业微信会话</span></span></b></a> ");
//         html.append("<div style=\"color: b5c4df;\">Mobile ：13726221877</div>");
//         html.append("<div style=\"color: b5c4df;\">E-mail ：v_wyaohu@tencent.com</div>");
        html.append("</div></body></html>");
        noticeListService.sendEmail(senderEmail,emailTitle,html);
    }


}


