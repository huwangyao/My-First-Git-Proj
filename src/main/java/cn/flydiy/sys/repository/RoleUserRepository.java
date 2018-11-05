package cn.flydiy.sys.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.sys.entity.RoleUser;

import java.util.List;

/**
 * Created by flying on 16-12-26.
 */
public interface RoleUserRepository extends BaseRepository<RoleUser> {

    List<RoleUser> findRoleUsersByUserId(String userId);
}
