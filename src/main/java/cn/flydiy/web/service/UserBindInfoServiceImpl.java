package cn.flydiy.web.service;

import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.core.service.BaseServiceImpl;
import cn.flydiy.web.entity.UserBindInfo;
import cn.flydiy.web.repository.UserBindInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by player on 2017/10/12.
 */
@Service
public class UserBindInfoServiceImpl extends BaseServiceImpl<UserBindInfo> implements UserBindInfoService{

    @Autowired
    private UserBindInfoRepository userBindInfoRepository;

    @Override
    public UserBindInfo queryByBindIdAndType(String bindId, String type) {
        return userBindInfoRepository.queryByBindIdAndType(bindId,type);
    }

    @Override
    public void handleBind(UserBindInfo userBindInfo) {
        List<UserBindInfo> userBindInfos = userBindInfoRepository.queryByUserIdAndType(userBindInfo);
        if(CollectionUtils.isNotEmpty(userBindInfos)){
            throw new BaseRunTimeException("该用户已经被绑定,绑定失败,但是您可刷新页面后进入系统");
        }else {
            save(userBindInfo);
        }
    }
}
