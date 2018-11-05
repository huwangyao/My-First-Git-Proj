package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.web.dto.RoleUserDto;
import cn.flydiy.web.entity.RoleUser;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Validated
public interface RoleUserService extends BaseService<RoleUser> {

    /**
     * 根据角色ID查询角色人员集合
     * @param roleIds
     * @return
     */
    List<Map> findRoleUserByRoleId(String... roleIds);

    /**
     * 根据角色ID分页查询角色的人员ID数组
     * @param roleIds
     * @return
     */
    List<String> findPageUserIdByRoleId(String... roleIds);

    /**
     * 根据角色ID查询角色的人员ID数组
     * @param roleIds
     * @return
     */
    List<String> findUserIdByRoleId(String... roleIds);

    /**
     * 保存角色人员
     *
     * @param roleUserDto
     */
    void saveRoleUser(RoleUserDto roleUserDto);

    /**
     * 根据角色ID删除角色中的所有人员
     *
     * @param roleId 角色ID
     */
    void deleteRoleUserByRoleId(String roleId);

    /**
     * 分页查询角色人员信息
     * @param roleId
     * @return
     */
    List<Map> queryPageRoleUserByRoleId(String... roleId);
}