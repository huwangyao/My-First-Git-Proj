package cn.flydiy.hangj.entity;


/**
* 行家信息实体
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/

@javax.persistence.Entity(name = "hangj_info")
@javax.persistence.Table(name = "hangj_info", indexes = {@javax.persistence.Index(columnList = "id"), @javax.persistence.Index(columnList = "createTime")})
@cn.flydiy.core.annotation.Comment("行家信息")
public class Info extends cn.flydiy.core.entity.BaseEntity {

    @javax.persistence.Transient
    @cn.flydiy.common.json.annotation.JsonIgnore
    private static final long serialVersionUID = -7931363903081679169L;



    
    
    
    @javax.persistence.Column(name = "id",length=36)
    @cn.flydiy.core.annotation.Comment("id")
    private String id;

    
    
    
    @javax.persistence.Column(name = "name",length=255)
    @cn.flydiy.core.annotation.Comment("姓名")
    private String name;

    
    
    
    @javax.persistence.Column(name = "orgStr",length=255)
    @cn.flydiy.core.annotation.Comment("组织架构")
    private String orgStr;

    
    
    
    @javax.persistence.Column(name = "position",length=255)
    @cn.flydiy.core.annotation.Comment("职位")
    private String position;

    
    
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "entryDate")
    @cn.flydiy.core.annotation.Comment("入职时间")
    private java.util.Date entryDate;

    
    
    
    @javax.persistence.Column(name = "title",length=255)
    @cn.flydiy.core.annotation.Comment("标题")
    private String title;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "career")
    @cn.flydiy.core.annotation.Comment("职业经历")
    private String career;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "projectExp")
    @cn.flydiy.core.annotation.Comment("项目经历")
    private String projectExp;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "awards")
    @cn.flydiy.core.annotation.Comment("所获奖项")
    private String awards;

    
    
    
    @javax.persistence.Column(name = "topicNo",length=11)
    @cn.flydiy.core.annotation.Comment("话题数量")
    private Integer topicNo;

    
    
    
    @javax.persistence.Column(name = "status",length=100)
    @cn.flydiy.core.annotation.Comment("状态")
    private String status;

    
    
    
    @javax.persistence.Column(name = "statusCode",length=255)
    @cn.flydiy.core.annotation.Comment("状态字典项编码")
    private String statusCode = "hangj-status";

    
    
    
    @javax.persistence.Column(name = "step",length=255)
    @cn.flydiy.core.annotation.Comment("页面步骤")
    private String step;

    
    
    
    @javax.persistence.Column(name = "address",length=255)
    @cn.flydiy.core.annotation.Comment("所在地")
    private String address;

    
    
    
    @javax.persistence.Column(name = "userId",length=255)
    @cn.flydiy.core.annotation.Comment("用户id")
    private String userId;

    
    
    
    @javax.persistence.Column(name = "pic",length=255)
    @cn.flydiy.core.annotation.Comment("图片")
    private String pic;

    
    
    
    @javax.persistence.Column(name = "tag",length=255)
    @cn.flydiy.core.annotation.Comment("标签")
    private String tag;

    @javax.persistence.Lob
    
    
    @javax.persistence.Column(name = "newPic")
    @cn.flydiy.core.annotation.Comment("自定义头像")
    private String newPic;

    
    
    
    @javax.persistence.Column(name = "version")
    @cn.flydiy.core.annotation.Comment("版本号")
    private Integer version;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getOrgStr() {
        return orgStr;
    }
    public void setOrgStr(String orgStr) {
        this.orgStr = orgStr;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public java.util.Date getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(java.util.Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getCareer() {
        return career;
    }
    public void setCareer(String career) {
        this.career = career;
    }

    public String getProjectExp() {
        return projectExp;
    }
    public void setProjectExp(String projectExp) {
        this.projectExp = projectExp;
    }

    public String getAwards() {
        return awards;
    }
    public void setAwards(String awards) {
        this.awards = awards;
    }

    public Integer getTopicNo() {
        return topicNo;
    }
    public void setTopicNo(Integer topicNo) {
        this.topicNo = topicNo;
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

    public String getStep() {
        return step;
    }
    public void setStep(String step) {
        this.step = step;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNewPic() {
        return newPic;
    }
    public void setNewPic(String newPic) {
        this.newPic = newPic;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
}
