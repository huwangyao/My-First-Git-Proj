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
@Entity(name = "cn.flydiy.sys.entity.RoleUser")
@Table(name = "sys_role_user"
        , indexes = {@Index(columnList = "id")}
        )
@Comment("角色用户表")
public class RoleUser extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 4358505073133563217L;

    @Column(length = ID_LEN)
    @Comment("角色ID")
    private String roleId;

    @Comment("角色ID")
    private Integer roleIdVer;

    @Column(length = ID_LEN)
    @Comment("用户ID")
    private String userId;

    @Comment("用户ID")
    private Integer userIdVer;


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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserIdVer() {
        return userIdVer;
    }

    public void setUserIdVer(Integer userIdVer) {
        this.userIdVer = userIdVer;
    }
}
