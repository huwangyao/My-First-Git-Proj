package cn.flydiy.hangj.entity;


/**
* 行家话题实体
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/

@javax.persistence.Entity(name = "hangj_topic")
@javax.persistence.Table(name = "hangj_topic", indexes = {@javax.persistence.Index(columnList = "id"), @javax.persistence.Index(columnList = "no")})
@cn.flydiy.core.annotation.Comment("行家话题")
public class Topic extends cn.flydiy.core.entity.BaseEntity {

    @javax.persistence.Transient
    @cn.flydiy.common.json.annotation.JsonIgnore
    private static final long serialVersionUID = -7931363903081679169L;



    
    
    
    @javax.persistence.Column(name = "infoId",length=36)
    @cn.flydiy.core.annotation.Comment("行家关联id")
    private String infoId;

    
    
    
    @javax.persistence.Column(name = "id",length=36)
    @cn.flydiy.core.annotation.Comment("id")
    private String id;

    
    
    
    @javax.persistence.Column(name = "infoIdVer",length=11)
    @cn.flydiy.core.annotation.Comment("行家关联id版本号")
    private Integer infoIdVer;

    
    
    
    @javax.persistence.Column(name = "name",length=255)
    @cn.flydiy.core.annotation.Comment("话题名称")
    private String name;

    
    
    
    @javax.persistence.Column(name = "consultTime",length=255)
    @cn.flydiy.core.annotation.Comment("预计咨询时长")
    private String consultTime;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "topicOutline")
    @cn.flydiy.core.annotation.Comment("话题大纲")
    private String topicOutline;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "relevantExp")
    @cn.flydiy.core.annotation.Comment("话题相关资历")
    private String relevantExp;

    
    
    
    @javax.persistence.Column(name = "status",length=100)
    @cn.flydiy.core.annotation.Comment("状态")
    private String status;

    
    
    
    @javax.persistence.Column(name = "statusCode",length=255)
    @cn.flydiy.core.annotation.Comment("状态字典项")
    private String statusCode = "hangj-topic-status";

    
    
    
    @javax.persistence.Column(name = "no",length=11)
    @cn.flydiy.core.annotation.Comment("序号")
    private Integer no;

    
    
    
    @javax.persistence.Column(name = "type",length=36)
    @cn.flydiy.core.annotation.Comment("话题分类")
    private String type;

    
    
    
    @javax.persistence.Column(name = "typeVer",length=11)
    @cn.flydiy.core.annotation.Comment("话题分类版本号")
    private Integer typeVer;

    
    
    
    @javax.persistence.Column(name = "version")
    @cn.flydiy.core.annotation.Comment("版本号")
    private Integer version;


    public String getInfoId() {
        return infoId;
    }
    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Integer getInfoIdVer() {
        return infoIdVer;
    }
    public void setInfoIdVer(Integer infoIdVer) {
        this.infoIdVer = infoIdVer;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getConsultTime() {
        return consultTime;
    }
    public void setConsultTime(String consultTime) {
        this.consultTime = consultTime;
    }

    public String getTopicOutline() {
        return topicOutline;
    }
    public void setTopicOutline(String topicOutline) {
        this.topicOutline = topicOutline;
    }

    public String getRelevantExp() {
        return relevantExp;
    }
    public void setRelevantExp(String relevantExp) {
        this.relevantExp = relevantExp;
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

    public Integer getNo() {
        return no;
    }
    public void setNo(Integer no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Integer getTypeVer() {
        return typeVer;
    }
    public void setTypeVer(Integer typeVer) {
        this.typeVer = typeVer;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
}
