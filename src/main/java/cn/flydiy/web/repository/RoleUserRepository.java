package cn.flydiy.web.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.web.entity.RoleUser;

import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
public interface RoleUserRepository extends BaseRepository<RoleUser> {


    List<RoleUser> findUserIdByRoleId(String... roleIds);


    /**
     * 根据角色ID删除角色中的所有人员
     *
     * @param roleId 角色ID
     */
    void deleteRoleUserByRoleId(String roleId);

    List<RoleUser> findPageUserIdByRoleId(String... roleIds);
}
