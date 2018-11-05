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
@Table(name = "sys_role_menu"
        , indexes = {@Index(columnList = "id")}
)
@Comment("角色菜单表")
public class RoleMenu extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 1549146841538509062L;

    @Column(length = ID_LEN)
    @Comment("角色ID")
    private String roleId;

    @Comment("角色ID")
    private Integer roleIdVer;

    @Column(length = ID_LEN)
    @Comment("菜单ID")
    private String menuId;

    @Comment("菜单ID")
    private Integer menuIdVer;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleIdVer() {
        return roleIdVer;
    }

    public void setRoleIdVer(Integer roleIdVer) {
        this.roleIdVer = roleIdVer;
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
