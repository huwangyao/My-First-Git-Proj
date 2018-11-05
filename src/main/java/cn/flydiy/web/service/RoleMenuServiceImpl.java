package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.web.entity.RoleMenu;
import cn.flydiy.web.repository.RoleMenuRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu> implements RoleMenuService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    RoleMenuRepository roleMenuRepository;

}
