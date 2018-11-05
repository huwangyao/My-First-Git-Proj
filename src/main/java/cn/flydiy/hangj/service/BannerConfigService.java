package cn.flydiy.hangj.service;

import cn.flydiy.core.service.BaseService;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;


/**
* banner配置信息
* Modify by v_wyaohu(胡王耀) on 2018-6-4 21:11:39.
*/
public interface BannerConfigService extends  BaseService<cn.flydiy.hangj.entity.BannerConfig>{

    public List<Map> queryMapByIds(String... ids);

    /**
    * 根据传入的参数查询实体数组
    * @param bannerConfig 查询参数对象
    */
    List<cn.flydiy.hangj.entity.BannerConfig> queryByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig);

    /**
    * 根据传入的参数查询Map数组
    * @param bannerConfig 查询参数对象
    */
    List<Map> queryMapByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig);

    /**
    * 根据传入的参数分页查询
    * @param bannerConfig 查询参数对象
    */
    List<cn.flydiy.hangj.entity.BannerConfig> queryPageByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig);

    /**
    * 根据传入的参数分页查询
    * @param bannerConfig 查询参数对象
    */
    List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.BannerConfig bannerConfig);

    void saveEntity(@NotNull cn.flydiy.hangj.entity.BannerConfig... bannerConfig);

    void updateEntity(@NotNull cn.flydiy.hangj.entity.BannerConfig... bannerConfig);

    void updateEntityNoSaveHistory(@NotNull cn.flydiy.hangj.entity.BannerConfig... bannerConfig);

    void updateByParam(cn.flydiy.hangj.entity.BannerConfig updateParam);



    void setIsNewToZero(String... id);

}
