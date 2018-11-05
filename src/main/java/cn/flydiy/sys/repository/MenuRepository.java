package cn.flydiy.sys.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.sys.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
public interface MenuRepository extends BaseRepository<Menu> {
    List<Map> queryByGroupId(String id);

}
