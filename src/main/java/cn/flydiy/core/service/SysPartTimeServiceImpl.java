package cn.flydiy.core.service;

import cn.flydiy.core.repository.SysPartTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;


/**
* 工作兼任
* Created by 梁宇湘 on 2017-11-15 10:07:37.
*/
@org.springframework.stereotype.Service
public class SysPartTimeServiceImpl extends BaseServiceImpl<cn.flydiy.core.entity.SysPartTime> implements SysPartTimeService{

    @Autowired
    private SysPartTimeRepository sysPartTimeRepository;

    @Override
    public List<Map> queryMapByIds(String... ids){
        return sysPartTimeRepository.queryMapByIds(ids);
    }

    @Override
    public List<cn.flydiy.core.entity.SysPartTime> queryByParams(cn.flydiy.core.entity.SysPartTime sysPartTime){
        return sysPartTimeRepository.queryByParams(sysPartTime);
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.core.entity.SysPartTime sysPartTime){
        return sysPartTimeRepository.queryMapByParams(sysPartTime);
    }

    @Override
    public List<cn.flydiy.core.entity.SysPartTime> queryPageByParams(cn.flydiy.core.entity.SysPartTime sysPartTime){
        return sysPartTimeRepository.queryPageByParams(sysPartTime);
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.core.entity.SysPartTime sysPartTime){
        return sysPartTimeRepository.queryPageMapByParams(sysPartTime);
    }

    @Override
    public void saveEntity(cn.flydiy.core.entity.SysPartTime... sysPartTime) {
        checkSysPartTime(sysPartTime);
        save(sysPartTime);
        for(cn.flydiy.core.entity.SysPartTime entity:sysPartTime){
        }
    }

    @Override
    public void updateEntity(cn.flydiy.core.entity.SysPartTime... sysPartTime) {
        checkSysPartTime(sysPartTime);
        update(sysPartTime);
    }

    private void checkSysPartTime(@NotNull cn.flydiy.core.entity.SysPartTime... sysPartTime){
        if(sysPartTime.length==1){
        }else if(sysPartTime.length>1){
        }
    }



}
