package cn.flydiy.hangj.dto;

import cn.flydiy.core.dto.BaseDto;
import cn.flydiy.hangj.service.BannerConfigService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* banner配置列表数据转换参数
* Modify by v_wyaohu(胡王耀) on 2018-5-31 15:00:29.
*/
public class ConfigListDto extends BaseDto {


    private cn.flydiy.hangj.entity.BannerConfig bannerConfig = new cn.flydiy.hangj.entity.BannerConfig();



    public cn.flydiy.hangj.entity.BannerConfig getBannerConfig() {
        return this.bannerConfig;
    }
    public void setBannerConfig(cn.flydiy.hangj.entity.BannerConfig bannerConfig) {
        this.bannerConfig = bannerConfig;
    }


}
