package cn.flydiy.web.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.web.entity.MenuGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
public interface MenuGroupRepository extends BaseRepository<MenuGroup> {
    List<Map> queryByParentId(String id);

    List<MenuGroup> findAllSort();

    List<MenuGroup> queryRootByTenantId(String parentId, String tenantId);
}
