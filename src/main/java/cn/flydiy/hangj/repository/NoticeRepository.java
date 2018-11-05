package cn.flydiy.hangj.repository;

import cn.flydiy.core.repository.BaseRepository;
import java.util.List;
import java.util.Map;


/**
* 通知记录表
* Modify by v_wyaohu on 2018-8-31 17:10:58.
*/
public interface NoticeRepository extends cn.flydiy.core.repository.BaseRepository<cn.flydiy.hangj.entity.Notice>{

    /**
    * 根据传入的参数查询Map数组
    * @param ids
    */
    List<Map> queryMapByIds(String... ids);

    /**
    * 根据传入的参数查询实体数组
    * @param notice 查询参数对象
    */
    List<cn.flydiy.hangj.entity.Notice> queryByParams(cn.flydiy.hangj.entity.Notice notice);

    /**
    * 根据传入的参数查询Map数组
    * @param notice 查询参数对象
    */
    List<Map> queryMapByParams(cn.flydiy.hangj.entity.Notice notice);

    /**
    * 根据传入的参数分页查询
    * @param notice 查询参数对象
    */
    List<cn.flydiy.hangj.entity.Notice> queryPageByParams(cn.flydiy.hangj.entity.Notice notice);

    /**
    * 根据传入的参数分页查询
    * @param notice 查询参数对象
    */
    List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Notice notice);

    void updateByParam(cn.flydiy.hangj.entity.Notice updateParam);




    void setIsNewToZero(String... id);

}
