package cn.flydiy.web.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.web.entity.MenuLang;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
public interface MenuLangRepository extends BaseRepository<MenuLang> {
    List<Map> queryByGroupId(String id);

    List<MenuLang> queryByMenuIdAndCurrentTenantId(String menuId);

    void deleteByMenuIdAndTenantId(String menuId);

    List<Map> queryAllByTenantId();
}
