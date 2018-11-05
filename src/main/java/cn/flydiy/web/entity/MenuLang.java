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
 * 菜单多语言翻译实体
 * Created by 张三 on 2017-8-22 14:13:04.
 */

@Entity
@Table(name = "management_sys_menu_lang"
        , indexes = {@Index(columnList = "id"), @Index(columnList = "createTime")}
        )
@Comment("菜单多语言翻译")
public class MenuLang extends BaseEntity {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 2699937498378693500L;

    @Column(name = "menuId",length=36)
    @Comment("菜单id")
    private String menuId;


    @Column(name = "menuIdVer",length=11)
    @Comment("菜单id版本号")
    private Integer menuIdVer;
    @javax.persistence.Lob

    @Column(name = "lang")
    @Comment("翻译内容")
    private String lang;

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
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
