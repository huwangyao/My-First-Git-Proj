package cn.flydiy.core.service;

import cn.flydiy.core.entity.Config;
import cn.flydiy.core.repository.ConfigRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by flying on 16-12-26.
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl<Config> implements ConfigService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    ConfigRepository configRepository;

}
