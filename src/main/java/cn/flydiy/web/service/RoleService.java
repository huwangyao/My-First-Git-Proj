package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.web.entity.Role;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Validated
public interface RoleService extends BaseService<Role> {
    List<Map> queryAllRole(String corpId);

    /**
     * 查询角色树
     *
     * @param corpId
     * @return
     */
    List<Map> queryRoleTree(String corpId);
}