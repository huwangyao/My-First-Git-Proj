package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.web.entity.MenuGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Validated
public interface MenuGroupService extends BaseService<MenuGroup> {

    void deleteMenuGroupById(String id);

    List<Map> queryMenuTreeData();

    void updateEntity(MenuGroup menuGroup);

    void updateEntityForDiy(MenuGroup menuGroup,String langs);


    List<Map> queryGenMenuTreeData();

    List<MenuGroup> queryRootByTenantId(String parentId, String tenantId);
}