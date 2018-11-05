package cn.flydiy.core.repository;

import java.util.List;
import java.util.Map;


/**
* 工作兼任
* Created by 梁宇湘 on 2017-11-15 10:07:37.
*/
public interface SysPartTimeRepository extends BaseRepository<cn.flydiy.core.entity.SysPartTime>{

    /**
    * 根据传入的参数查询Map数组
    * @param ids
    */
    List<Map> queryMapByIds(String... ids);

    /**
    * 根据传入的参数查询实体数组
    * @param sysPartTime 查询参数对象
    */
    List<cn.flydiy.core.entity.SysPartTime> queryByParams(cn.flydiy.core.entity.SysPartTime sysPartTime);

    /**
    * 根据传入的参数查询Map数组
    * @param sysPartTime 查询参数对象
    */
    List<Map> queryMapByParams(cn.flydiy.core.entity.SysPartTime sysPartTime);

    /**
    * 根据传入的参数分页查询
    * @param sysPartTime 查询参数对象
    */
    List<cn.flydiy.core.entity.SysPartTime> queryPageByParams(cn.flydiy.core.entity.SysPartTime sysPartTime);

    /**
    * 根据传入的参数分页查询
    * @param sysPartTime 查询参数对象
    */
    List<Map> queryPageMapByParams(cn.flydiy.core.entity.SysPartTime sysPartTime);





}
