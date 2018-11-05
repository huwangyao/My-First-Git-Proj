package cn.flydiy.hangj.repository;

import cn.flydiy.core.repository.BaseRepository;
import java.util.List;
import java.util.Map;


/**
* 分类管理
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/
public interface TypeRepository extends cn.flydiy.core.repository.BaseRepository<cn.flydiy.hangj.entity.Type>{

    /**
    * 根据传入的参数查询Map数组
    * @param ids
    */
    List<Map> queryMapByIds(String... ids);

    /**
    * 根据传入的参数查询实体数组
    * @param type 查询参数对象
    */
    List<cn.flydiy.hangj.entity.Type> queryByParams(cn.flydiy.hangj.entity.Type type);

    /**
    * 根据传入的参数查询Map数组
    * @param type 查询参数对象
    */
    List<Map> queryMapByParams(cn.flydiy.hangj.entity.Type type);

    /**
    * 根据传入的参数分页查询
    * @param type 查询参数对象
    */
    List<cn.flydiy.hangj.entity.Type> queryPageByParams(cn.flydiy.hangj.entity.Type type);

    /**
    * 根据传入的参数分页查询
    * @param type 查询参数对象
    */
    List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Type type);

    void updateByParam(cn.flydiy.hangj.entity.Type updateParam);




    void setIsNewToZero(String... id);

    public List<Map> dataTableForHangj_typeManager(Map param);
}
