
package cn.flydiy.hangj.service;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.hangj.service.BannerConfigService;
import cn.flydiy.serial.util.SerialNumberUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.dto.BannerCreateDto;

/**
* banner新增
* Modify by v_wyaohu(胡王耀) on 2018-6-4 21:11:41.
*/
@org.springframework.stereotype.Service
public class BannerCreateServiceImpl implements BannerCreateService {

    @Autowired
    private BannerConfigService bannerConfigService;



    @Override
    public void saveDto(BannerCreateDto dto) {
        cn.flydiy.hangj.entity.BannerConfig bannerConfig = dto.getBannerConfig();
        
        bannerConfigService.saveEntity(bannerConfig);
    }

    @Override
    public void commit(BannerCreateDto dto) {
        cn.flydiy.hangj.entity.BannerConfig bannerConfig = dto.getBannerConfig();
        if(cn.flydiy.common.util.StringUtil.isEmpty(bannerConfig.getId())){
            
            saveDto(dto);
        }else{
            
            updateDto(dto);
        }
    }


    @Override
    public Map queryById(String id) {
        Map result = new HashMap();
        Map map = new HashMap();
        cn.flydiy.hangj.entity.BannerConfig bannerConfig = bannerConfigService.findOne(id);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.BannerConfig.class);
        BeanUtil.copyPropertiesToMap(map,bannerConfig);
        result.put("bannerConfig",map);
        return result;
    }


    @Override
    public List<Map> queryByIds(String... ids) {
        List<Map> result = new ArrayList<>();
        List<cn.flydiy.hangj.entity.BannerConfig> bannerConfigs = bannerConfigService.findByIds(ids);

        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.BannerConfig.class);

        for (cn.flydiy.hangj.entity.BannerConfig bannerConfig : bannerConfigs) {
            Map temp = new HashMap();
            BeanUtil.copyPropertiesToMap(temp,bannerConfig);
            result.add(temp);
        }
        return result;
    }


    @Override
    public void updateDto(BannerCreateDto dto) {
        cn.flydiy.hangj.entity.BannerConfig bannerConfig = dto.getBannerConfig();

        
        bannerConfigService.updateEntity(bannerConfig);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.BannerConfig.class);
        if(_isMasterData){
            dto.setBannerConfig(bannerConfig);
            return ;
        }

    }


    @Override
    public void updateDtoNoSaveHistory(BannerCreateDto dto) {
        cn.flydiy.hangj.entity.BannerConfig bannerConfig = dto.getBannerConfig();

        
        bannerConfigService.updateEntityNoSaveHistory(bannerConfig);
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.BannerConfig.class);

    }


    @Override
    public void deleteById(String id) {
        boolean _isMasterData = cn.flydiy.core.common.db.DBUtils.isMasterData(cn.flydiy.hangj.entity.BannerConfig.class);
        cn.flydiy.hangj.entity.BannerConfig bannerConfig = bannerConfigService.findOne(id);
        bannerConfigService.delete(id);
    }

    @Override
    public void batchCommit(List<BannerCreateDto> dtos) {
        if(CollectionUtils.isNotEmpty(dtos)){
            for (BannerCreateDto dto : dtos) {
                commit(dto);
            }
        }
    }



}


