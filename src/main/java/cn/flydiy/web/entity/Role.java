package cn.flydiy.web.entity;

import cn.flydiy.common.json.annotation.JsonIgnore;
import cn.flydiy.core.annotation.Comment;
import cn.flydiy.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by flying on 16-12-26.
 */
@Entity
@Table(name = "sys_role"
        , indexes = {@Index(columnList = "id")}
)
@Comment("角色表")
public class Role extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 5731959086294633747L;

    @Column(length = 100)
    @Comment("名称")
    private String name;

    @Column(length = 100)
    @Comment("代码编号")
    private String code;

    @Column(length = 300)
    @Comment("备注")
    private String remark;

    @Column(length = 24)
    @Comment("状态, valid, invalid")
    private String status;

    @Column(length = 24)
    @Comment("设备, pc, mobile")
    private String device;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

}
