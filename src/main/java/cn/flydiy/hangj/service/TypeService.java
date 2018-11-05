package cn.flydiy.hangj.service;

import cn.flydiy.core.service.BaseService;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;


/**
* 分类管理
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/
public interface TypeService extends  BaseService<cn.flydiy.hangj.entity.Type>{

    public List<Map> queryMapByIds(String... ids);

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

    void saveEntity(@NotNull cn.flydiy.hangj.entity.Type... type);

    void updateEntity(@NotNull cn.flydiy.hangj.entity.Type... type);

    void updateEntityNoSaveHistory(@NotNull cn.flydiy.hangj.entity.Type... type);

    void updateByParam(cn.flydiy.hangj.entity.Type updateParam);



    void setIsNewToZero(String... id);

    List<Map> dataTableForHangj_typeManager(Map param);
}
