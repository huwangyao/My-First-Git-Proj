package cn.flydiy.hangj.controller;

import cn.flydiy.common.util.BeanUtil;
import cn.flydiy.core.controller.BaseController;
import cn.flydiy.core.web.ResponseData;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.flydiy.hangj.service.BannerConfigService;
import cn.flydiy.hangj.entity.BannerConfig;

/**
* banner配置信息控制器
* Modify by v_wyaohu(胡王耀) on 2018-6-4 21:11:39.
*/
@cn.flydiy.core.annotation.WebController
public class BannerConfigController extends BaseController {

    @Autowired
    private BannerConfigService bannerConfigService;

    //保存
    public void saveEntity() {
        BannerConfig bannerConfig = getParamObj(BannerConfig.class);
        bannerConfigService.saveEntity(bannerConfig);
        super.render(new ResponseData(bannerConfig));
    }

    //批量保存或者更新
    public void batchSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("bannerConfig");
        List<BannerConfig> bannerConfigs = BeanUtil.convertMapsToBeans(listMap, BannerConfig.class);
        List<BannerConfig> saveArr = new java.util.ArrayList<>();
        List<BannerConfig> updateArr = new java.util.ArrayList<>();
        for (BannerConfig bannerConfig : bannerConfigs) {
            if (bannerConfig.getId() == null) {
            
                saveArr.add(bannerConfig);
            }else {
                updateArr.add(bannerConfig);
            }
        }
        bannerConfigService.saveEntity(saveArr.toArray(new BannerConfig[]{}));
        bannerConfigService.updateEntity(updateArr.toArray(new BannerConfig[]{}));
        super.render(new ResponseData(bannerConfigs));
    }

    //批量整个保存
    public void batchWholeSave(){
        Map<String, Object> paramMap = getParamMap();
        List listMap = (List) paramMap.get("bannerConfig");
        List<BannerConfig> bannerConfigs = BeanUtil.convertMapsToBeans(listMap, BannerConfig.class);
        bannerConfigService.saveWhole(bannerConfigs.toArray(new BannerConfig[]{}));
        super.render(new ResponseData(bannerConfigs));
    }

    //根据id查询
    public void queryById() {
        Map map = getParamMap();
        BannerConfig result =  bannerConfigService.findOne(MapUtils.getString(map,"id"));
        super.render(new ResponseData(result));
    }

    //更新
    public void updateEntity() {
        BannerConfig bannerConfig = getParamObj(BannerConfig.class);
        bannerConfigService.updateEntity(bannerConfig);
        super.render(new ResponseData(bannerConfig));
    }

    public void saveOrUpdate(){
        BannerConfig bannerConfig = getParamObj(BannerConfig.class);
        if (bannerConfig.getId() == null) {
        
            bannerConfigService.save(bannerConfig);
        }else{
            bannerConfigService._updateEntityById(bannerConfig);
        }
        super.render(new ResponseData(bannerConfig));
    }

    //根据id删除
    public void deleteById() {
        Map map = getParamMap();
        bannerConfigService.delete(MapUtils.getString(map,"id"));
        super.render(new ResponseData());
    }

    //批量删除
    public void batchDelete(){
        Map map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        bannerConfigService.delete(ids.toArray(new String[]{}));
        super.render(new ResponseData());
    }

    public void queryByParam(){
        BannerConfig condition = getParamObj(BannerConfig.class);
        List<BannerConfig> result = bannerConfigService.queryByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryPageByParam(){
        BannerConfig condition = getParamObj(BannerConfig.class);
        List<BannerConfig> result = bannerConfigService.queryPageByParams(condition);
        super.render(new ResponseData(result));
    }

    public void queryByIds(){
        Map<String, Object> map = getParamMap();
        List<String> ids = (List<String>) map.get("ids");
        List<BannerConfig> result = bannerConfigService.findByIds(ids.toArray(new String[]{}));
        super.render(new ResponseData(result));
    }

    public void updateByParam(){
        BannerConfig updateParam = getParamObj(BannerConfig.class);
        bannerConfigService.updateByParam(updateParam);
        super.render(new ResponseData());
    }


}
