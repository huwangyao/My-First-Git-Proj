package cn.flydiy.sys.entity;

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
@Entity(name = "cn.flydiy.sys.entity.Menu")
@Table(name = "sys_menu"
        , indexes = {@Index(columnList = "id")}
        )
@Comment("菜单表")
public class Menu extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 7829796395014265703L;

    @Column(length = 100)
    @Comment("名称")
    private String name;

    @Column(length = 200)
    @Comment("前端路由地址")
    private String url;

    @Column(length = 1000)
    @Comment("后端请求url")
    private String requestPath;

    @Column(length = 100)
    @Comment("图标")
    private String icon;

    @Comment("排序")
    private Integer sort;

    @Column(length = 24)
    @Comment("类型，有可能是菜单中的某些操作, menu, btn, api")
    private String type;

    @Column(length = 24)
    @Comment("是否停用，显示, valid, invalid")
    private String status;

    @Column(length = 300)
    @Comment("备注")
    private String remark;

    @Column(length = ID_LEN)
    @Comment("菜单组id")
    private String menuGroupId;

    @Comment("菜单组id")
    private Integer menuGroupIdVer;

    @Column(length = 36)
    @Comment("颜色")
    private String color;

    @Comment("是否有角标")
    private Integer isLog;

    @Column(length = 24)
    @Comment("设备, pc, mobile")
    private String device;

    public Integer getIsLog() {
        return isLog;
    }

    public void setIsLog(Integer isLog) {
        this.isLog = isLog;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getMenuGroupId() {
        return menuGroupId;
    }

    public void setMenuGroupId(String menuGroupId) {
        this.menuGroupId = menuGroupId;
    }

    public Integer getMenuGroupIdVer() {
        return menuGroupIdVer;
    }

    public void setMenuGroupIdVer(Integer menuGroupIdVer) {
        this.menuGroupIdVer = menuGroupIdVer;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
