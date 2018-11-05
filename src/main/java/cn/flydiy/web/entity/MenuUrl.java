package cn.flydiy.web.entity;

import cn.flydiy.common.json.annotation.JsonIgnore;
import cn.flydiy.core.annotation.Comment;
import cn.flydiy.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * 菜单对应的后台url
 * Created by flying on 16-12-26.
 */
@Entity
@Table(name = "sys_menu_url"
        , indexes = {@Index(columnList = "id")}
)
@Comment("菜单后台url表")
public class MenuUrl extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = -8049241996229700518L;

    @Column(length = 100)
    @Comment("名称")
    private String name;

    @Column(length = 1000)
    @Comment("后端请求url")
    private String url;


    @Column(length = 24)
    @Comment("是否停用，显示, valid, invalid")
    private String status;

    @Column(length = 300)
    @Comment("备注")
    private String remark;

//    @Column(length = 50)
//    @Comment("来源")
//    private String fromStr;


    @Column(length = ID_LEN)
    @Comment("角色id")
    private String roleId;

    @Column(length = ID_LEN)
    @Comment("菜单id")
    private String menuId;

    @Comment("菜单id")
    private Integer menuIdVer;

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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuIdVer() {
        return menuIdVer;
    }

    public void setMenuIdVer(Integer menuIdVer) {
        this.menuIdVer = menuIdVer;
    }
}
