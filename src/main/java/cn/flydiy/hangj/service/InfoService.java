package cn.flydiy.hangj.service;

import cn.flydiy.core.service.BaseService;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;


/**
* 行家信息
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/
public interface InfoService extends  BaseService<cn.flydiy.hangj.entity.Info>{

    public List<Map> queryMapByIds(String... ids);

    /**
    * 根据传入的参数查询实体数组
    * @param info 查询参数对象
    */
    List<cn.flydiy.hangj.entity.Info> queryByParams(cn.flydiy.hangj.entity.Info info);

    /**
    * 根据传入的参数查询Map数组
    * @param info 查询参数对象
    */
    List<Map> queryMapByParams(cn.flydiy.hangj.entity.Info info);

    /**
    * 根据传入的参数分页查询
    * @param info 查询参数对象
    */
    List<cn.flydiy.hangj.entity.Info> queryPageByParams(cn.flydiy.hangj.entity.Info info);

    /**
    * 根据传入的参数分页查询
    * @param info 查询参数对象
    */
    List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Info info);

    void saveEntity(@NotNull cn.flydiy.hangj.entity.Info... info);

    void updateEntity(@NotNull cn.flydiy.hangj.entity.Info... info);

    void updateEntityNoSaveHistory(@NotNull cn.flydiy.hangj.entity.Info... info);

    void updateByParam(cn.flydiy.hangj.entity.Info updateParam);



    void setIsNewToZero(String... id);

public List<Map> queryHangjInfo2ForHangj_topicManager(Map param);
public List<Map> queryHangjInfo1ForHangj_topicManager(Map param);
    List<Map> dataTableForHangj_managerList(Map param);
}
