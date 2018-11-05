package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.web.entity.MenuUrl;
import cn.flydiy.web.repository.MenuUrlRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class MenuUrlServiceImpl extends BaseServiceImpl<MenuUrl> implements MenuUrlService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    MenuUrlRepository menuUrlRepository;

}
