package cn.flydiy.serial.entity;


import cn.flydiy.common.json.annotation.JsonIgnore;

import javax.persistence.Index;
import javax.persistence.Transient;

/**
* 生成单号实体
* Created by flydiy on 2017-04-12 09:03:28.
*/

@javax.persistence.Entity(name = "cn.flydiy.serial.entity.SerialNum")
@javax.persistence.Table(name = "management_serial_num"
        , indexes = {@Index(columnList = "id")}
        )
@cn.flydiy.core.annotation.Comment("生成单号")
public class SerialNum extends cn.flydiy.core.entity.BaseEntity {


    @Transient
    @JsonIgnore
    private static final long serialVersionUID = -2380516318107337725L;

    @javax.persistence.Column(name = "serialNumber")
    @cn.flydiy.core.annotation.Comment("序号")
    private Long serialNumber;
    
    
    @javax.persistence.Column(name = "step")
    @cn.flydiy.core.annotation.Comment("步长")
    private Integer step;
    
    
    @javax.persistence.Column(name = "dateRecord",length=36)
    @cn.flydiy.core.annotation.Comment("日期记录")
    private String dateRecord;
    
    
    @javax.persistence.Column(name = "orgId",length=36)
    @cn.flydiy.core.annotation.Comment("组织")
    private String orgId;
    
    
    @javax.persistence.Column(name = "orgIdVer",length=11)
    @cn.flydiy.core.annotation.Comment("组织版本号")
    private Integer orgIdVer;
    
    
    @javax.persistence.Column(name = "tableName",length=36)
    @cn.flydiy.core.annotation.Comment("表名")
    private String tableName;
    
    
    @javax.persistence.Column(name = "colName",length=36)
    @cn.flydiy.core.annotation.Comment("列名")
    private String colName;
    
    
    @javax.persistence.Column(name = "type",length=36)
    @cn.flydiy.core.annotation.Comment("类型")
    private String type;

    public Long getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }
    public Integer getStep() {
        return step;
    }
    public void setStep(Integer step) {
        this.step = step;
    }
    public String getDateRecord() {
        return dateRecord;
    }
    public void setDateRecord(String dateRecord) {
        this.dateRecord = dateRecord;
    }
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public Integer getOrgIdVer() {
        return orgIdVer;
    }
    public void setOrgIdVer(Integer orgIdVer) {
        this.orgIdVer = orgIdVer;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getColName() {
        return colName;
    }
    public void setColName(String colName) {
        this.colName = colName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
