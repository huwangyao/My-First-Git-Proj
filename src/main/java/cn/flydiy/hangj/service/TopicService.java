package cn.flydiy.hangj.service;

import cn.flydiy.core.service.BaseService;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;


/**
* 行家话题
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/
public interface TopicService extends  BaseService<cn.flydiy.hangj.entity.Topic>{

    public List<Map> queryMapByIds(String... ids);

    /**
    * 根据传入的参数查询实体数组
    * @param topic 查询参数对象
    */
    List<cn.flydiy.hangj.entity.Topic> queryByParams(cn.flydiy.hangj.entity.Topic topic);

    /**
    * 根据传入的参数查询Map数组
    * @param topic 查询参数对象
    */
    List<Map> queryMapByParams(cn.flydiy.hangj.entity.Topic topic);

    /**
    * 根据传入的参数分页查询
    * @param topic 查询参数对象
    */
    List<cn.flydiy.hangj.entity.Topic> queryPageByParams(cn.flydiy.hangj.entity.Topic topic);

    /**
    * 根据传入的参数分页查询
    * @param topic 查询参数对象
    */
    List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Topic topic);

    void saveEntity(@NotNull cn.flydiy.hangj.entity.Topic... topic);

    void updateEntity(@NotNull cn.flydiy.hangj.entity.Topic... topic);

    void updateEntityNoSaveHistory(@NotNull cn.flydiy.hangj.entity.Topic... topic);

    void updateByParam(cn.flydiy.hangj.entity.Topic updateParam);


    //根据"行家关联id"字段查找
    List<Map> queryByInfoId(String... infoId);

    //根据"行家关联id"字段删除
    void deleteByInfoId(String... infoId);

    //更新从对象
    List<String> updateByInfoId(String mainId, List<cn.flydiy.hangj.entity.Topic> topics);

    //根据"行家关联id"字段查找
    List<Map> queryByInfoId(Integer version,String... infoId);

    //根据"行家关联id"字段删除
    void deleteByInfoId(Integer version,String... infoId);

    //更新从对象
    List<String> updateByInfoId(Integer version,String mainId, List<cn.flydiy.hangj.entity.Topic> topics);

    void setIsNewToZero(String... id);

        //TopicManager的手工代码
        List<Map> queryPageTopicMapByParams(Map param);
    List<Map> dataTableForHangj_topicManager(Map param);
}
