package cn.flydiy.sys.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.sys.entity.RoleMenu;

import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
public interface RoleMenuRepository extends BaseRepository<RoleMenu> {
    List<RoleMenu> queryRoleMenuByRoleIds(String... roleIds);
}
