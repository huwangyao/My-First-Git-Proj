package cn.flydiy.hangj.repository;

import cn.flydiy.core.repository.BaseRepository;
import java.util.List;
import java.util.Map;


/**
* banner配置信息
* Modify by v_wyaohu(胡王耀) on 2018-6-4 21:11:39.
*/
public interface BannerConfigRepository extends cn.flydiy.core.repository.BaseRepository<cn.flydiy.hangj.entity.BannerConfig>{

    /**
    * 根据传入的参数查询Map数组
    * @param ids
    */
    List<Map> queryMapByIds(String... ids);

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

    void updateByParam(cn.flydiy.hangj.entity.BannerConfig updateParam);




    void setIsNewToZero(String... id);

}
