package cn.flydiy.core.entity;

import cn.flydiy.common.json.annotation.JsonIgnore;
import cn.flydiy.core.annotation.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by flying on 17-1-7.
 */
@Entity
@Table(name = "sys_corp"
        , indexes = {@Index(columnList = "id")}
        )
@Comment("法人表")
public class Corp extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 1798653680441065117L;


    @Column(length = 100)
    @Comment("法人组织代码")
    private String code;

    @Column(length = 100)
    @Comment("法人组织名称")
    private String name;

    @Column(length = 200)
    @Comment("法人")
    private String legalPerson;

    @Comment("法人")
    private Integer legalPersonVer;


    @Column(length = 400)
    @Comment("地址")
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Comment("成立日期")
    private Date establishDate;

    @Comment("注册资金")
    private Double registeredFund;

    @Column(length = 4000)
    @Comment("经营范围")
    private String businessScope;


    @Column(length = 4000)
    @Comment("备注")
    private String remark;


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

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public Integer getLegalPersonVer() {
        return legalPersonVer;
    }

    public void setLegalPersonVer(Integer legalPersonVer) {
        this.legalPersonVer = legalPersonVer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public Double getRegisteredFund() {
        return registeredFund;
    }

    public void setRegisteredFund(Double registeredFund) {
        this.registeredFund = registeredFund;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
