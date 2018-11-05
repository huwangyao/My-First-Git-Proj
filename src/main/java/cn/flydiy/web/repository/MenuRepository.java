package cn.flydiy.web.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.web.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
public interface MenuRepository extends BaseRepository<Menu> {
    List<Map> queryByGroupId(String id);

    List<Map> findAllSort();

    List<Menu> queryByMenuIds(String... menuIds);

    List<Menu> queryByName(String menuName);
}
