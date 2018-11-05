package cn.flydiy.hangj.entity;


/**
* 通知记录表实体
* Modify by v_wyaohu on 2018-8-31 17:10:58.
*/

@javax.persistence.Entity(name = "hangj_notice")
@javax.persistence.Table(name = "hangj_notice", indexes = {@javax.persistence.Index(columnList = "id")})
@cn.flydiy.core.annotation.Comment("通知记录表")
public class Notice extends cn.flydiy.core.entity.BaseEntity {

    @javax.persistence.Transient
    @cn.flydiy.common.json.annotation.JsonIgnore
    private static final long serialVersionUID = -7931363903081679169L;



    
    
    
    @javax.persistence.Column(name = "recipient",length=255)
    @cn.flydiy.core.annotation.Comment("接收人")
    private String recipient;

    
    
    
    @javax.persistence.Column(name = "sender",length=255)
    @cn.flydiy.core.annotation.Comment("发送人")
    private String sender;

    
    
    
    @javax.persistence.Column(name = "title",length=255)
    @cn.flydiy.core.annotation.Comment("话题名称")
    private String title;

    
    
    
    @javax.persistence.Column(name = "topicId",length=255)
    @cn.flydiy.core.annotation.Comment("话题id")
    private String topicId;

    
    
    
    @javax.persistence.Column(name = "orderId",length=255)
    @cn.flydiy.core.annotation.Comment("预约id")
    private String orderId;

    
    
    
    @javax.persistence.Column(name = "isRead",length=8)
    @cn.flydiy.core.annotation.Comment("是否阅读")
    private Integer isRead;

    
    
    
    @javax.persistence.Column(name = "type",length=255)
    @cn.flydiy.core.annotation.Comment("类型")
    private String type;

    
    
    
    @javax.persistence.Column(name = "expertName",length=255)
    @cn.flydiy.core.annotation.Comment("行家名称")
    private String expertName;

    
    
    
    @javax.persistence.Column(name = "senderName",length=255)
    @cn.flydiy.core.annotation.Comment("咨询者名称")
    private String senderName;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "memo",length=4001)
    @cn.flydiy.core.annotation.Comment("备注")
    private String memo;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "message",length=4001)
    @cn.flydiy.core.annotation.Comment("信息")
    private String message;


    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopicId() {
        return topicId;
    }
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getIsRead() {
        return isRead;
    }
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getExpertName() {
        return expertName;
    }
    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getSenderName() {
        return senderName;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
