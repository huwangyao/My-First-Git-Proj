package cn.flydiy.hangj.service;

import cn.flydiy.core.service.BaseService;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;


/**
* 通知记录表
* Modify by v_wyaohu on 2018-8-31 17:10:58.
*/
public interface NoticeService extends  BaseService<cn.flydiy.hangj.entity.Notice>{

    public List<Map> queryMapByIds(String... ids);

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

    void saveEntity(@NotNull cn.flydiy.hangj.entity.Notice... notice);

    void updateEntity(@NotNull cn.flydiy.hangj.entity.Notice... notice);

    void updateEntityNoSaveHistory(@NotNull cn.flydiy.hangj.entity.Notice... notice);

    void updateByParam(cn.flydiy.hangj.entity.Notice updateParam);



    void setIsNewToZero(String... id);

}
