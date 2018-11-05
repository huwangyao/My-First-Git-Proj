package cn.flydiy.web.dto;

import cn.flydiy.common.json.annotation.JsonIgnore;
import cn.flydiy.web.entity.Menu;
import cn.flydiy.web.entity.MenuGroup;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flying on 16-12-27.
 */
public class MenuDto extends MenuGroup {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 64996776851968357L;

    List<MenuDto> childList = new ArrayList<>();
    List<Menu> menuList = new ArrayList<>();

    public List<MenuDto> getChildList() {
        return childList;
    }

    public void setChildList(List<MenuDto> childList) {
        this.childList = childList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
