package cn.flydiy.hangj.service;

import cn.flydiy.common.exception.BaseRunTimeException;
import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.service.BaseServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.collections4.MapUtils;
import cn.flydiy.hangj.repository.BannerConfigRepository;


/**
* banner配置信息
* Modify by v_wyaohu(胡王耀) on 2018-6-4 21:11:39.
*/
@org.springframework.stereotype.Service
public class BannerConfigServiceImpl extends cn.flydiy.core.service.BaseServiceImpl<cn.flydiy.hangj.entity.BannerConfig>   implements BannerConfigService {

    @Autowired
    private BannerConfigRepository bannerConfigRepository;

    @Override
    public List<Map> queryMapByIds(String... ids){
        return bannerConfigRepository.queryMapByIds(ids);
    }

    @Override
    public List<cn.flydiy.hangj.entity.BannerConfig> queryByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig){
        return bannerConfigRepository.queryByParams(bannerConfig);
    }

    @Override
    public List<Map> queryMapByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig){
        return bannerConfigRepository.queryMapByParams(bannerConfig);
    }

    @Override
    public List<cn.flydiy.hangj.entity.BannerConfig> queryPageByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig){
        return bannerConfigRepository.queryPageByParams(bannerConfig);
    }

    @Override
    public List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig){
        return bannerConfigRepository.queryPageMapByParams(bannerConfig);
    }

    @Override
    public void saveEntity(cn.flydiy.hangj.entity.BannerConfig... bannerConfig) {
        checkBannerConfig(bannerConfig);
        for(cn.flydiy.hangj.entity.BannerConfig entity:bannerConfig){
        }
        save(bannerConfig);
        for(cn.flydiy.hangj.entity.BannerConfig entity:bannerConfig){
        }
    }

    @Override
    public void updateEntity(cn.flydiy.hangj.entity.BannerConfig... bannerConfig) {
        checkBannerConfig(bannerConfig);
        update(bannerConfig);
    }

    @Override
    public void updateEntityNoSaveHistory(cn.flydiy.hangj.entity.BannerConfig... bannerConfig) {
        checkBannerConfig(bannerConfig);
        _updateAndNoSaveHistory(bannerConfig);
    }

    @Override
    public void updateByParam(cn.flydiy.hangj.entity.BannerConfig updateParam) {
        bannerConfigRepository.updateByParam(updateParam);
    }

    private void checkBannerConfig(@NotNull cn.flydiy.hangj.entity.BannerConfig... bannerConfig){
        if(bannerConfig.length==1){
        }else if(bannerConfig.length>1){
        }
    }



    public void setIsNewToZero(String... id) {
        bannerConfigRepository.setIsNewToZero(id);
    }

}
