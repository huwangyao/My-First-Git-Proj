package cn.flydiy.hangj.entity;


/**
* 行家预约单实体
* Modify by v_wyaohu on 2018-8-31 17:45:34.
*/

@javax.persistence.Entity(name = "hangj_order")
@javax.persistence.Table(name = "hangj_order", indexes = {@javax.persistence.Index(columnList = "id"), @javax.persistence.Index(columnList = "createTime")})
@cn.flydiy.core.annotation.Comment("行家预约单")
public class Order extends cn.flydiy.core.entity.BaseEntity {

    @javax.persistence.Transient
    @cn.flydiy.common.json.annotation.JsonIgnore
    private static final long serialVersionUID = -7931363903081679169L;



    
    
    
    @javax.persistence.Column(name = "orderName",length=255)
    @cn.flydiy.core.annotation.Comment("咨询者姓名")
    private String orderName;

    
    
    
    @javax.persistence.Column(name = "id",length=36)
    @cn.flydiy.core.annotation.Comment("id")
    private String id;

    
    
    
    @javax.persistence.Column(name = "expertName",length=255)
    @cn.flydiy.core.annotation.Comment("行家姓名")
    private String expertName;

    
    
    
    @javax.persistence.Column(name = "title",length=255)
    @cn.flydiy.core.annotation.Comment("咨询话题名称")
    private String title;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "selfIntroduction",length=4001)
    @cn.flydiy.core.annotation.Comment("咨询者自我介绍")
    private String selfIntroduction;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "problemDesc",length=4001)
    @cn.flydiy.core.annotation.Comment("咨询者问题描述")
    private String problemDesc;

    
    
    
    @javax.persistence.Column(name = "status",length=100)
    @cn.flydiy.core.annotation.Comment("状态")
    private String status;

    
    
    
    @javax.persistence.Column(name = "statusCode",length=255)
    @cn.flydiy.core.annotation.Comment("状态字典项编码")
    private String statusCode = "hangj_order_status";

    
    
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "meetDate")
    @cn.flydiy.core.annotation.Comment("见面时间")
    private java.util.Date meetDate;

    
    
    
    @javax.persistence.Column(name = "version")
    @cn.flydiy.core.annotation.Comment("版本号")
    private Integer version;

    
    
    
    @javax.persistence.Column(name = "topicId",length=255)
    @cn.flydiy.core.annotation.Comment("话题id")
    private String topicId;

    
    
    
    @javax.persistence.Column(name = "meetAddress",length=2000)
    @cn.flydiy.core.annotation.Comment("预约地点")
    private String meetAddress;

    
    
    
    @javax.persistence.Column(name = "orderNameId",length=255)
    @cn.flydiy.core.annotation.Comment("咨询者id")
    private String orderNameId;

    
    
    
    @javax.persistence.Column(name = "expertId",length=255)
    @cn.flydiy.core.annotation.Comment("行家id")
    private String expertId;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "memo",length=4001)
    @cn.flydiy.core.annotation.Comment("备注")
    private String memo;

    
    
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "acceptDate")
    @cn.flydiy.core.annotation.Comment("接受时间")
    private java.util.Date acceptDate;


    public String getOrderName() {
        return orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getExpertName() {
        return expertName;
    }
    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }
    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public String getProblemDesc() {
        return problemDesc;
    }
    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public java.util.Date getMeetDate() {
        return meetDate;
    }
    public void setMeetDate(java.util.Date meetDate) {
        this.meetDate = meetDate;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTopicId() {
        return topicId;
    }
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getMeetAddress() {
        return meetAddress;
    }
    public void setMeetAddress(String meetAddress) {
        this.meetAddress = meetAddress;
    }

    public String getOrderNameId() {
        return orderNameId;
    }
    public void setOrderNameId(String orderNameId) {
        this.orderNameId = orderNameId;
    }

    public String getExpertId() {
        return expertId;
    }
    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public java.util.Date getAcceptDate() {
        return acceptDate;
    }
    public void setAcceptDate(java.util.Date acceptDate) {
        this.acceptDate = acceptDate;
    }
}
