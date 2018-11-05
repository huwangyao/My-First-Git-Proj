package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.web.entity.Role;
import cn.flydiy.web.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Map> queryAllRole(String corpId) {

        List<Role> roles = roleRepository.queryAllRole(corpId);

        List<Map> mapList = BeanUtil.convertBeansToMaps(roles);

        return mapList;
    }

    @Override
    public List<Map> queryRoleTree(String corpId) {

        List<Role> roles = roleRepository.queryAllRole(corpId);

        List<Map> mapList = BeanUtil.convertBeansToMaps(roles);

        return mapList;
    }
}
