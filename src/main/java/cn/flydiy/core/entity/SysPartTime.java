package cn.flydiy.core.entity;


import javax.persistence.Index;

/**
* 工作兼任实体
* Created by 梁宇湘 on 2017-11-15 10:07:37.
*/

@javax.persistence.Entity(name = "cn.flydiy.core.entity.SysPartTime")
@javax.persistence.Table(name = "sys_part_time"
        , indexes = {@Index(columnList = "id")}
        )
@cn.flydiy.core.annotation.Comment("工作兼任")
public class SysPartTime extends cn.flydiy.core.entity.BaseEntity {

    
    
    @javax.persistence.Column(name = "userId",length=36)
    @cn.flydiy.core.annotation.Comment("用户")
    private String userId;
    
    
    @javax.persistence.Column(name = "userIdVer",length=11)
    @cn.flydiy.core.annotation.Comment("用户版本号")
    private Integer userIdVer;
    
    
    @javax.persistence.Column(name = "jobsId",length=36)
    @cn.flydiy.core.annotation.Comment("岗位")
    private String jobsId;
    
    
    @javax.persistence.Column(name = "jobsIdVer",length=11)
    @cn.flydiy.core.annotation.Comment("岗位版本号")
    private Integer jobsIdVer;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Integer getUserIdVer() {
        return userIdVer;
    }

    public void setUserIdVer(Integer userIdVer) {
        this.userIdVer = userIdVer;
    }
    public String getJobsId() {
        return jobsId;
    }

    public void setJobsId(String jobsId) {
        this.jobsId = jobsId;
    }
    public Integer getJobsIdVer() {
        return jobsIdVer;
    }

    public void setJobsIdVer(Integer jobsIdVer) {
        this.jobsIdVer = jobsIdVer;
    }
}
