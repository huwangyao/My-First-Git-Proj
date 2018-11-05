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
@Table(name = "sys_org"
        , indexes = {@Index(columnList = "id")}
        )
@Comment("组织表")
public class Org extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = -1950585596795084974L;

    @Column(length = 100)
    @Comment("组织单元代码")
    private String code;

    @Column(length = 32)
    @Comment("组织类型，法人corp，部门(非法人)dept")
    private String type;

    @Column(length = 32)
    @Comment("组织类型描述，集团,公司,一级组织...")
    private String typeDesc;

    @Column(length = 150)
    @Comment("组织名称")
    private String name;

    @Column(length = 100)
    @Comment("组织简称")
    private String shortName;

    @Column(length = ID_LEN)
    @Comment("上级组织")
    private String parentId;

    @Comment("上级组织")
    private Integer parentIdVer;

    @Comment("排序号")
    private Integer sort;

    @Column(length = ID_LEN)
    @Comment("组织负责人")
    private String leader;

    @Comment("组织负责人")
    private Integer leaderVer;

    @Column(length = 100)
    @Comment("联系地址")
    private String addr;

    @Column(length = 20)
    @Comment("邮政编码")
    private String zip;//邮政编码

    @Column(length = 24)
    @Comment("电话")
    private String tel;

    @Column(length = 24)
    @Comment("传真")
    private String fax;//传真

    @Column(length = 150)
    @Comment("网址")
    private String web;

    @Column(length = 32)
    @Comment("状态")
    private String status;

    @Column(length = 4000)
    @Comment("备注")
    private String remark;

    @javax.persistence.Column(name = "isRepertory",length=8)
    @cn.flydiy.core.annotation.Comment("库存组织")
    private Integer isRepertory;



    @javax.persistence.Column(name = "isFinance",length=8)
    @cn.flydiy.core.annotation.Comment("财务账套")
    private Integer isFinance;



    @javax.persistence.Column(name = "isCorp",length=8)
    @cn.flydiy.core.annotation.Comment("法人实体")
    private Integer isCorp;



    @javax.persistence.Column(name = "isCost",length=8)
    @cn.flydiy.core.annotation.Comment("成本中心")
    private Integer isCost;



    @javax.persistence.Column(name = "isProfit",length=8)
    @cn.flydiy.core.annotation.Comment("利润中心")
    private Integer isProfit;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getParentIdVer() {
        return parentIdVer;
    }

    public void setParentIdVer(Integer parentIdVer) {
        this.parentIdVer = parentIdVer;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Integer getLeaderVer() {
        return leaderVer;
    }

    public void setLeaderVer(Integer leaderVer) {
        this.leaderVer = leaderVer;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
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


    public Integer getIsRepertory() {
        return isRepertory;
    }

    public void setIsRepertory(Integer isRepertory) {
        this.isRepertory = isRepertory;
    }

    public Integer getIsFinance() {
        return isFinance;
    }

    public void setIsFinance(Integer isFinance) {
        this.isFinance = isFinance;
    }

    public Integer getIsCorp() {
        return isCorp;
    }

    public void setIsCorp(Integer isCorp) {
        this.isCorp = isCorp;
    }

    public Integer getIsCost() {
        return isCost;
    }

    public void setIsCost(Integer isCost) {
        this.isCost = isCost;
    }

    public Integer getIsProfit() {
        return isProfit;
    }

    public void setIsProfit(Integer isProfit) {
        this.isProfit = isProfit;
    }
}
