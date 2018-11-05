package cn.flydiy.web.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.web.entity.MenuFavorite;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
public interface MenuFavoriteRepository extends BaseRepository<MenuFavorite> {
    List<MenuFavorite> queryByUserId(String userId);

    void deleteByUserId(String id);

    void deleteByMenuId(String menuId);

    List<Map> queryMapByUserId(String userId);
}
