package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.web.entity.MenuFavorite;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Validated
public interface MenuFavoriteService extends BaseService<MenuFavorite> {
    List<Map> queryByUserId(String id);

    void updateMenuFavorite(List<String> menuIds);

    void deleteByMenuId(String menuId);

    void addMenuFavorite(String menuId);
}