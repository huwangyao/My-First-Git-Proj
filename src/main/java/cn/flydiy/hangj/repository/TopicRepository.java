package cn.flydiy.hangj.repository;

import cn.flydiy.core.repository.BaseRepository;
import java.util.List;
import java.util.Map;


/**
* 行家话题
* Modify by v_sunlli on 2018-8-23 11:22:40.
*/
public interface TopicRepository extends cn.flydiy.core.repository.BaseRepository<cn.flydiy.hangj.entity.Topic>{

    /**
    * 根据传入的参数查询Map数组
    * @param ids
    */
    List<Map> queryMapByIds(String... ids);

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

    void updateByParam(cn.flydiy.hangj.entity.Topic updateParam);



    //根据"行家关联id"字段查找
    public List<Map> queryByInfoId(String... infoId);

    //根据"行家关联id"字段删除
    public void deleteByInfoId(String... infoId);

    //根据"行家关联id"字段查找
    public List<Map> queryByInfoId(Integer version,String... infoId);

    //根据"行家关联id"字段删除
    public void deleteByInfoId(Integer version,String... infoId);

    void setIsNewToZero(String... id);

        //TopicManager的手工代码
        List<Map> queryPageTopicMapByParams(Map param);
    public List<Map> dataTableForHangj_topicManager(Map param);
}
