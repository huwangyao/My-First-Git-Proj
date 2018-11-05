package cn.flydiy.core.entity;

import cn.flydiy.common.json.annotation.JsonIgnore;
import cn.flydiy.core.annotation.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by flying on 16-12-26.
 */
@Entity
@Table(name = "sys_config"
        , indexes = {@Index(columnList = "id")}
        )
@Comment("配置表")
public class Config extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = -7089982368203316595L;

    @Column(length = 100)
    @Comment("中文显示名称")
    private String name;

    @Column(length = 100)
    @Comment("配置code")
    private String code;

    @Column(length = 100)
    @Comment("配置的值")
    private String value;

    @Column(length = 255)
    @Comment("说明")
    private String remark;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
