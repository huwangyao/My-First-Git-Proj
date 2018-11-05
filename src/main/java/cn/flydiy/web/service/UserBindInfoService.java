package cn.flydiy.web.service;

import cn.flydiy.core.service.BaseService;
import cn.flydiy.web.entity.UserBindInfo;

/**
 * Created by player on 2017/10/12.
 */
public interface UserBindInfoService extends BaseService<UserBindInfo>{
    UserBindInfo queryByBindIdAndType(String bindId, String type);

    void handleBind(UserBindInfo userBindInfo);
}
