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
@Table(name = "sys_menu_group"
        , indexes = {@Index(columnList = "id"), @Index(columnList = "createTime")}
        )
@Comment("菜单组表")
public class MenuGroup extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 9222759848565749667L;

    @Column(length = 100)
    @Comment("菜单组名称")
    private String name;

    @Comment("分组级别")
    private Integer groupLevel;

    @Comment("序号")
    private Integer sort;

    @Column(length = ID_LEN)
    @Comment("上级分组")
    private String parentId;

    @Comment("上级分组")
    private Integer parentIdVer;

    @Column(length = 24)
    @Comment("状态")
    private String status;

    @Column(length = 100)
    @Comment("图标")
    private String icon;

    @Column(length = 36)
    @Comment("颜色")
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(Integer groupLevel) {
        this.groupLevel = groupLevel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
