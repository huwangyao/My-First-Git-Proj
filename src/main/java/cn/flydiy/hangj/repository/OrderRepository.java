package cn.flydiy.hangj.repository;

import cn.flydiy.core.repository.BaseRepository;
import java.util.List;
import java.util.Map;


/**
* 行家预约单
* Modify by v_wyaohu on 2018-8-31 17:45:34.
*/
public interface OrderRepository extends cn.flydiy.core.repository.BaseRepository<cn.flydiy.hangj.entity.Order>{

    /**
    * 根据传入的参数查询Map数组
    * @param ids
    */
    List<Map> queryMapByIds(String... ids);

    /**
    * 根据传入的参数查询实体数组
    * @param order 查询参数对象
    */
    List<cn.flydiy.hangj.entity.Order> queryByParams(cn.flydiy.hangj.entity.Order order);

    /**
    * 根据传入的参数查询Map数组
    * @param order 查询参数对象
    */
    List<Map> queryMapByParams(cn.flydiy.hangj.entity.Order order);

    /**
    * 根据传入的参数分页查询
    * @param order 查询参数对象
    */
    List<cn.flydiy.hangj.entity.Order> queryPageByParams(cn.flydiy.hangj.entity.Order order);

    /**
    * 根据传入的参数分页查询
    * @param order 查询参数对象
    */
    List<Map> queryPageMapByParams(cn.flydiy.hangj.entity.Order order);

    void updateByParam(cn.flydiy.hangj.entity.Order updateParam);




    void setIsNewToZero(String... id);

        //HangjiaJob的手工代码
            List<Map> getOrderCountByExpertName(Map param);

    List<Map> queryOrderByConsultantIdOrConsultantName(cn.flydiy.hangj.entity.Order orderParam,List<String> status);

    List<Map> queryExpertOrder(cn.flydiy.hangj.entity.Order orderParam,List<String> status);
    public List<Map> dataTableForHangj_orderList(Map param);
public List<Map> exportOrderListOrder(Map param);
}
