package cn.flydiy.web.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.web.entity.Role;

import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
public interface RoleRepository extends BaseRepository<Role> {
    List<Role> queryAllRole(String corpId);
}
