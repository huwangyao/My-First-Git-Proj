package cn.flydiy.core.entity;

import cn.flydiy.common.json.annotation.JsonIgnore;
import cn.flydiy.core.annotation.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by flying on 17-1-7.
 */
@Entity
@Table(name = "sys_jobs"
        , indexes = {@Index(columnList = "id")}
        )
@Comment("职位表")
public class Jobs extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = -2686112342419549455L;

    @Column(length = 100)
    @Comment("岗位代码")
    private String code;

    @Column(length = 150)
    @Comment("岗位名称")
    private String name;

    @Comment("排序")
    private Integer sort;

    @Column(length = 32)
    @Comment("状态")
    private String status;

    @Column(length = 4000)
    @Comment("备注")
    private String remark;

    @Column(length = 4000)
    @Comment("岗位要求")
    private String jobRequire;

    @Column(length = 4000)
    @Comment("岗位职责")
    private String jobDuty;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getJobRequire() {
        return jobRequire;
    }

    public void setJobRequire(String jobRequire) {
        this.jobRequire = jobRequire;
    }

    public String getJobDuty() {
        return jobDuty;
    }

    public void setJobDuty(String jobDuty) {
        this.jobDuty = jobDuty;
    }
}
