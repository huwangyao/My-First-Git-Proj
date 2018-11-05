package cn.flydiy.web.repository;

import cn.flydiy.core.repository.BaseRepository;
import cn.flydiy.web.entity.UserBindInfo;
import java.util.List;

/**
 * Created by player on 2017/10/12.
 */
public interface UserBindInfoRepository extends BaseRepository<UserBindInfo>{
    UserBindInfo queryByBindIdAndType(String bindId, String type);

    List<UserBindInfo> queryByUserIdAndType(UserBindInfo userBindInfo);

    List<UserBindInfo> queryByAccountAndType(UserBindInfo userBindInfo);
}
