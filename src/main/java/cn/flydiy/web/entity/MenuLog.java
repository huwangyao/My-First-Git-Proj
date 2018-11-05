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
@Table(name = "sys_menu_log"
        , indexes = {@Index(columnList = "id"), @Index(columnList = "createTime")}
        )
@Comment("菜单日志表")
public class MenuLog extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 4425431208452318053L;

    @Column(length = ID_LEN)
    @Comment("菜单ID")
    private String menuId;

    @Comment("菜单ID")
    private Integer menuIdVer;

    @Column(length = ID_LEN)
    @Comment("用户ID")
    private String userId;

    @Comment("用户ID")
    private Integer userIdVer;


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
